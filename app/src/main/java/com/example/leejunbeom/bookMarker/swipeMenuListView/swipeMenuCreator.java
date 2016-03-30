package com.example.leejunbeom.bookMarker.swipeMenuListView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.example.leejunbeom.test.R;

import java.net.ContentHandler;

/**
 * Created by noduritoto on 2016-03-29.
 */
public class swipeMenuCreator implements SwipeMenuCreator {

    Context context;

    @Override
    public void create(SwipeMenu menu) {
        System.out.print("we dont use this method..");
    }

    @Override
    public void create(SwipeMenu menu, Context context){
        this.context = context;
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }


}
