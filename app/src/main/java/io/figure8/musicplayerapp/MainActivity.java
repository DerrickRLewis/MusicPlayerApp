package io.figure8.musicplayerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private static final String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    private static final int REQUEST_PERMISSIONS = 12345;
    private static final int PERMISSION_COUNT =1;

    @SuppressLint("NewApi")
    private boolean arePermissionsDenied(){
        for (int i = 0; i < PERMISSION_COUNT; i++){
            if (checkSelfPermission(PERMISSIONS[i]) != PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }
    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions ,int[] grantResults ){
        super.onRequestPermissionsResult(requestCode ,permissions,grantResults);
       if (arePermissionsDenied()){
           ((ActivityManager) (this.getSystemService(ACTIVITY_SERVICE))).clearApplicationUserData();
           recreate();
       }else{
           onResume();
       }
    }

    @Override
    protected void  onResume(){
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && arePermissionsDenied()){
            requestPermissions(PERMISSIONS,REQUEST_PERMISSIONS);
            return;
        }

    }
}
