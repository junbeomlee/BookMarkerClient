package com.example.leejunbeom.bookMarker.ui.preview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by noduritoto on 2016. 6. 4..
 * */


public class OCRScanLineView extends View {

    public static int cropSize;

    public OCRScanLineView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);                    // Cyan
        paint.setStrokeWidth(10);                     // 크기 10

        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        int rectWidth = getWidth() /4;
        cropSize = rectWidth*5/6;

        paint.setColor(Color.CYAN);                        // 펜색깔
        paint.setStyle(Paint.Style.STROKE);                 // STROKE 빈화면
        canvas.drawRect(centerX - rectWidth, centerY - rectWidth, centerX + rectWidth, centerY + rectWidth, paint);   // 사각형

        paint.setColor(Color.RED);                        // 펜색깔
        paint.setStyle(Paint.Style.STROKE);                 // STROKE 빈화면
        paint.setStrokeWidth(5);
        canvas.drawRect(centerX - cropSize, centerY - cropSize, centerX + cropSize, centerY + cropSize, paint);   // 사각형

        super.onDraw(canvas);
    }

}


