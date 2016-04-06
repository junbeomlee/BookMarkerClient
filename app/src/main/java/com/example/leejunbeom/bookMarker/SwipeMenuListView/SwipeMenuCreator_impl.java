package com.example.leejunbeom.bookMarker.SwipeMenuListView;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

    public SwipeMenuCreator_impl (Context context) { this.mContext = context; }

    @Override
    public void create(SwipeMenu menu) {
        createMenu(menu);
    }

    private void createMenu(SwipeMenu menu) {
        SwipeMenuItem item1 = new SwipeMenuItem(
                mContext);
        item1.setBackground(new ColorDrawable(Color.RED));
        item1.setWidth(dp2px(90));
        item1.setIcon(R.drawable.ic_action_discard);
        menu.addMenuItem(item1);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                mContext.getResources().getDisplayMetrics());
    }
}
