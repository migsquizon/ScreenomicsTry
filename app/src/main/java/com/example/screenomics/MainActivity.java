package com.example.screenomics;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SCREENSHOT=59706;
    private MediaProjectionManager mgr;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mgr=(MediaProjectionManager)getSystemService(MEDIA_PROJECTION_SERVICE);

        startActivityForResult(mgr.createScreenCaptureIntent(),
                REQUEST_SCREENSHOT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SCREENSHOT) {
            if (resultCode == RESULT_OK) {
                Intent i =
                        new Intent(this, ScreenshotService.class)
                                .putExtra(ScreenshotService.EXTRA_RESULT_CODE, resultCode)
                                .putExtra(ScreenshotService.EXTRA_RESULT_INTENT, data);

                startService(i);
            }
        }

        finish();
    }
}
