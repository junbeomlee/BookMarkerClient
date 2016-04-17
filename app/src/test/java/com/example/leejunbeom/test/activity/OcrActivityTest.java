package com.example.leejunbeom.test.activity;

import android.os.Environment;
import android.util.Log;

import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.bookMarker.ui.activity.OcrActivity;
import com.example.leejunbeom.test.BuildConfig;
import com.example.leejunbeom.test.dagger.TestApplication;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by noduritoto on 2016. 4. 17..
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21,application = TestApplication.class)
public class OcrActivityTest {

    OcrActivity ocrActivity;
    public static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/SimpleAndroidOCR/";
    private static final String TAG = "OcrActivityTest.java";
    String[] paths;
    File dir;

    @Before
    public void setUp(){
        ocrActivity = Robolectric.setupActivity(OcrActivity.class);
        paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };
    }

    @After
    public void tearDown(){}

    @Test
    public void checkInternalPath(){
        final String DATA_PATH_INTERNAL = ocrActivity.getFilesDir().toString();
        Log.d("Internal", DATA_PATH_INTERNAL);

    }

    @Test
    public void mkdirTest(){
        //String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };
        File dir = new File(paths[0]);
        assertFalse(dir.mkdirs());

        dir = new File(paths[1]);
        assertFalse(dir.mkdirs());

        /*

        for (String path : paths) {
            File dir = new File(path);
            assertTrue(dir.mkdirs());
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    Log.v(TAG, "ERROR: Creation of directory " + path + " on sdcard failed");

                    return;
                } else {
                    Log.v(TAG, "Created directory " + path + " on sdcard");
                }
            }

        }
        */

    }

    @Test
    public void dirNameAndExistTest(){
        assertNotNull(DATA_PATH);
        for (String path : paths){
            File dir = new File(path);
            assertTrue(dir.exists());
        }
    }
}
