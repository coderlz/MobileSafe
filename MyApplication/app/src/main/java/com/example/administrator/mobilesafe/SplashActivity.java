package com.example.administrator.mobilesafe;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    private TextView tv_splash_versionname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        //
        tv_splash_versionname = (TextView) findViewById(R.id.tv_splash_versionname);

        //
       tv_splash_versionname.setText("版本号:" + getVersionName());
    }

    private String getVersionName() {

        PackageManager pm = getPackageManager();
        try {
            String name = getPackageName();
            PackageInfo info = pm.getPackageInfo(getPackageName(),PackageManager.GET_ACTIVITIES);
            String versionname = info.versionName;
          return  versionname;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return  "";
    }

    private void  getVersion() {

        try {
            URL url = new URL("http://localhost:8080/LoginTest/version.json");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 200) {

                System.out.print("Connection Success!");

            } else  {

                System.out.print("Connection Failure!");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
