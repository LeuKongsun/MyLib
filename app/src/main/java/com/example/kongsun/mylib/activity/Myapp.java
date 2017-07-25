package com.example.kongsun.mylib.activity;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.kongsun.mylib.db.User;

/**
 * Created by kongsun on 6/30/17.
 */

public class Myapp {
    public static final int LOGIN_METHOD_USERNAME_PASSWORD=0;
    public static final int LOGIN_METHOD_FIREBASE = 1;

    private static Myapp instance;

    private User currentUser;
    private int loginMethod;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private Myapp(Context context){
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            @Override
            public Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

            }
        });
    }
    public static Myapp getInstance(Context context){
        if(instance == null){
            instance = new Myapp(context);
        }
        return instance;
    }

    public int getLoginMethod() {
        return loginMethod;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(RequestQueue requestQueue) {
        this.requestQueue = requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void setImageLoader(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }
    public void setLoginMethod(int loginMethod){
        this.loginMethod = loginMethod;
    }

    public void addRequest(Request request) {
        requestQueue.add(request);
    }
}
