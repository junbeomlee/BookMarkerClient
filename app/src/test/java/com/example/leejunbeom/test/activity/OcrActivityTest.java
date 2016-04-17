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
    public static String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/SimpleAndroidOCR/";
    private static final String TAG = "OcrActivityTest.java";
    String[] paths;
    File dir;

    //클래스 전역변수
    private final String rootFolderName = "/BookMarkerOCR";
    private final String tessdataFolderName = "/tessdata";
    public static String root = null; //메모를 저장하는 폴더의 root dir
    public static String tessDataRoot = null; //메모를 저장하는 폴더 dir
    public String sdcard= Environment.getExternalStorageState();

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
    public void sdCardMountTest(){

        assertTrue(sdcard.equals(Environment.MEDIA_MOUNTED));

        if( ! sdcard.equals(Environment.MEDIA_MOUNTED) ) {
            //SD카드 UNMOUNTED
            Log.d("mstag","sdcard unmounted");
            root = "" + Environment.getRootDirectory().getAbsolutePath() + rootFolderName; //내부저장소의 주소를 얻어옴
        }

        else {
            //SD카드 MOUNT
            Log.d("mstag","sdcard mounted");
            root = "" + Environment.getExternalStorageDirectory().getAbsolutePath() + rootFolderName; //외부저장소의 주소를 얻어옴
        }

        Log.d("mstag","root dir is => "+root);


    }

    @Test
    public void mkdirTest(){
        /*
        //String[] paths = new String[] { DATA_PATH, DATA_PATH + "tessdata/" };
        File dir = new File(paths[0]);
        assertTrue(dir.mkdirs());

        dir = new File(paths[1]);
        assertTrue(dir.mkdirs());
        */

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

        //dir init
        String sdcard= Environment.getExternalStorageState();

        tessDataRoot = root + tessdataFolderName;
        File rootCheck = new File(root);
        assertNotNull(rootCheck);

        if( ! rootCheck.exists() ) { //최상위 루트폴더 미 존재시
            assertTrue(rootCheck.mkdirs());
            Log.d("mstag","root make");
            Log.d("mstag","check making root : " + rootCheck.exists());
        }
        rootCheck = new File(tessDataRoot);
        assertNotNull(rootCheck);

        if( ! rootCheck.exists() ) { //하위 메모저장폴더 미 존재시
            assertTrue(rootCheck.mkdirs());
            Log.d("mstag","root-son make");
            Log.d("mstag","check making root-son : " + rootCheck.exists());
        }

        DATA_PATH = new String(root + "/");
        Log.d("mstag",root);
        Log.d("mstag",DATA_PATH);


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
