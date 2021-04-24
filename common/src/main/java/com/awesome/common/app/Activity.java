package com.awesome.common.app;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.awesome.common.constant.Constant;

/**
 * Author: JfangZ
 * Email:zhangjingfang@jeejio.com
 * Date: 2021/2/3 19:48
 * Description: Activity基类
 */
public class Activity extends AppCompatActivity {

    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }


    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constant.RECORD_AUDIO_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    doRecord();
                break;
        }
    }

    /**
     * Author: JfangZ
     * Email:zhangjingfang@jeejio.com
     * Date: 2021/2/3 19:52
     * Description:做录音操作
     */
    public void doRecord() {
    }
}
