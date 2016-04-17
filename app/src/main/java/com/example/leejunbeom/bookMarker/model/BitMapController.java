package com.example.leejunbeom.bookMarker.model;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jun on 16. 4. 18..
 */
public class BitMapController {
    Context context;
    Map<String,Bitmap> bitMapList = new HashMap<String,Bitmap>();

    public BitMapController(Context context){
        this.context=context;
    }
}
