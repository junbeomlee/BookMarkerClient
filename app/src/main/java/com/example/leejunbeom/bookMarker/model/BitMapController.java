package com.example.leejunbeom.bookMarker.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.leejunbeom.bookMarker.model.pojo.Book;
import com.example.leejunbeom.test.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jun on 16. 4. 18..
 */
public class BitMapController {

    private Context context;
    private Map<String,Bitmap> bitmapMap= new HashMap<String,Bitmap>();
    //다 합쳐진 bitmap
    private Bitmap composedBitMap;
    //기본 파일
    private Bitmap baseLibraryBitMap;
    // 합쳐진 bitmap key값
    private final String COMPOSED_LIBRARY_BIT_MAP = "0";

    public BitMapController(Context context){
        this.context=context;
        /**
         * 기본으로 계속 사용할 bit map
         */
        baseLibraryBitMap = BitmapFactory.decodeResource(context.getResources(), R.drawable.non10);

        /**
         * 합쳐진 bit map
         */
        composedBitMap = BitmapFactory.decodeResource(context.getResources(),R.drawable.non10);

        this.bitmapMap.put(COMPOSED_LIBRARY_BIT_MAP, composedBitMap);
    }

    public void setBitMap(BookController bookController){
        for(int i=0;i<bookController.size();i++){
            Book book=bookController.getItem(i);
            Bitmap bookBitMap=book.getBookBitMap();
            /**
             * 1. bitMap + base push
             * 2. bitMap + compute push;
             */
            
        }
    }

    public Bitmap compute(){

    }


}
