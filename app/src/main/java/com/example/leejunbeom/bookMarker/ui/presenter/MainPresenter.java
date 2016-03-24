package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;

/**
 * Created by Jun on 16. 3. 24..
 */
public class MainPresenter {

    public void onBookAddButtonClick(MainActivity mainActivity) {
        mainActivity.launchAddBookActivity();
    }
}
