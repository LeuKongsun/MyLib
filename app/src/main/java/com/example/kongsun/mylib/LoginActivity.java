package com.example.kongsun.mylib;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements View.OnClickListener {
    private final String USERNAME = "Kongsun";
    private final String PASSWORD = "123";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnLogin = (Button) findViewById(R.id.btn_loggin);
        btnLogin.setOnClickListener(this);
    }
    public void onClick(View view){
        EditText etxUsername =(EditText) findViewById(R.id.etx_username);
        EditText etxPasswd = (EditText) findViewById(R.id.etx_passwd);
        String username = etxUsername.getText().toString();
        String passwd = etxPasswd.getText().toString();

        if(username.equals(USERNAME) && passwd.equals(PASSWORD)){
            Intent mainIntent = new Intent(this,MainActivity.class);
            startActivity(mainIntent);
        }else {
            Toast.makeText(this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();
        }
    }
}
