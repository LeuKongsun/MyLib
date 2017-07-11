package com.example.kongsun.mylib.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kongsun.mylib.R;
import com.example.kongsun.mylib.db.DbLibrary;
import com.example.kongsun.mylib.db.User;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends Activity implements View.OnClickListener, OnCompleteListener {
    public static final String PREFERENCE_NAME = "Kongsun";
    public static final String KEY_USERNAME = "E-library";
    private CallbackManager callbackManager;
    private CheckBox checkBox;
    private FirebaseAuth firebaseAuth;
    //ProfileTracker profileTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btn_loggin);
        btnLogin.setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.chk_login);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME,null);
        if(username!=null){
            startMainActivity();
            return;

        }

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            Myapp.getInstance(this).setLoginMethod(Myapp.LOGIN_METHOD_FIREBASE);
            startMainActivity();
            return;
        }
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton)findViewById(R.id.btn_fb_login);
        loginButton.setReadPermissions("email");
        loginButton.registerCallback(callbackManager,callback);
        /*LoginManager loginManager = LoginManager.getInstance();
        loginManager.registerCallback(callbackManager);
        loginManager.logInWithReadPermissions(this,"e-mail");*/
    }

    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            /*Log.d("e-library", "Facebook login success");
            Myapp.getInstance(LoginActivity.this).setLoginMethod(Myapp.LOGIN_METHOD_FACEBOOK);
            if(Profile.getCurrentProfile() != null){
                Log.d("e-library", "Profile is generated");
                startMainActivity();
            }else{
                profileTracker = new ProfileTracker() {
                    @Override
                    protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                        Log.d("e-library", "onCurrentProfileChanged");
                        profileTracker.stopTracking();
                        startMainActivity();
                    }
                };
            }*/
            AuthCredential credential = FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken());
            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(LoginActivity.this);
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
    public void onClick(View view){
        if (view.getId()==R.id.btn_loggin){
            DbLibrary dbLibrary = new DbLibrary(this);
            EditText etxUsername =(EditText) findViewById(R.id.etx_username);
            EditText etxPassword = (EditText) findViewById(R.id.etx_passwd);
            String username = etxUsername.getText().toString();
            String password = etxPassword.getText().toString();
            User user = dbLibrary.login(username,password);
            if(user!=null){
                Myapp.getInstance(this).setCurrentUser(user);
                Myapp.getInstance(this).setLoginMethod(Myapp.LOGIN_METHOD_USERNAME_PASSWORD);
                startMainActivity();
                if(checkBox.isChecked()){
                    SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KEY_USERNAME, username);
                    editor.commit();
                }
            }else{
                Log.d("e-library", "Login fail");
                Toast.makeText(this, "Login fail. Incorrect username or password", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onComplete(@NonNull Task task) {
        Log.d("e-library", "onAuthStateChanged");
        if(task.isSuccessful()){
            Log.d("e-library", "Firebase login success: " + firebaseAuth.getCurrentUser().getDisplayName());
            Myapp.getInstance(this).setLoginMethod(Myapp.LOGIN_METHOD_FIREBASE);
            startMainActivity();
        }else{
            Log.d("e-library", "Firebase login fail: " + task.getException());
        }
    }
    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
