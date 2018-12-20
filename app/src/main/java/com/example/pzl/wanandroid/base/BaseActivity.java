package com.example.pzl.wanandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.example.pzl.wanandroid.app.MyApp;

public class BaseActivity extends AppCompatActivity {

    public MyApp mMyApp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("-----className-----",this.getClass().getSimpleName());
        mMyApp = (MyApp) getApplication();
    }

    public void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
