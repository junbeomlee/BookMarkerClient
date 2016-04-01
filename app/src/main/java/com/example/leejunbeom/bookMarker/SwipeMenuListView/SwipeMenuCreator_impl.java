package com.example.leejunbeom.bookMarker.SwipeMenuListView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.network.jericho.Jericho;
import com.example.leejunbeom.test.R;

import javax.inject.Inject;

/**
 * Created by noduritoto on 2016-03-31.
 */
public class SwipeMenuCreator_impl implements SwipeMenuCreator {
    private Context mContext;

    @Inject
    public SwipeMenuCreator_impl (Context context) {
        this.mContext = context;
    }

    @Override
    public void create(SwipeMenu menu) {
        // Create different menus depending on the view type
        switch (menu.getViewType()) {
            case 0:
                createMenu1(menu);
                break;
            case 1:
                createMenu2(menu);
                break;
            case 2:
                createMenu3(menu);
                break;
        }
    }


    public void create(SwipeMenu menu, Context context) {
        System.out.print("we dont use this method..");
    }

    private void createMenu1(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mContext);
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18,
                0x5E)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_favorite);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mContext);
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_good);
        menu.addMenuItem(item2);
    }

    private void createMenu2(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mContext);
        item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0,
                0x3F)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_important);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mContext);
        item2.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_discard);
        menu.addMenuItem(item2);
    }

    private void createMenu3(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mContext);
        item1.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1,
                0xF5)));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_about);
        menu.addMenuItem(item1);
        SwipeMenuItem item2 = new SwipeMenuItem(
                mContext);
        item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        item2.setWidth(dp2px(90));
        item2.setIcon(R.drawable.ic_action_share);
        menu.addMenuItem(item2);
    }


    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    mContext.getResources().getDisplayMetrics());
    }

    //void create(SwipeMenu menu, Context context);
}
