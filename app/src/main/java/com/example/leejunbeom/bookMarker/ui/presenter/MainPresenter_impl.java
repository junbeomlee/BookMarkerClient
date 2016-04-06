package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.Mainscreen;

/**
 * Created by Jun on 16. 3. 24..
 */
public class MainPresenter_impl implements MainPresenter {

    @Override
    public void onBookAddButtonClick(Mainscreen mainscreen) {
        mainscreen.launchAddBookActivity();
    }
}
