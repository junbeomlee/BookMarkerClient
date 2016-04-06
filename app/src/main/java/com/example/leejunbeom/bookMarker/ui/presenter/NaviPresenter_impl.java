package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.model.BookController;
import com.example.leejunbeom.bookMarker.ui.activity.NaviActivity;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;

import javax.inject.Inject;

/**
 * Created by Jun on 16. 3. 28..
 */
public class NaviPresenter_impl implements NaviPresenter{

    private BookController bookController;

    @Inject
    public NaviPresenter_impl(BookController bookController) {
        this.bookController=bookController;
    }


    @Override
    public void onBookSearchButtonClick(NaviScreen naviScreen) {
        naviScreen.launchSearchActivity();
    }

    /**
     * Map의 책장에서 해당하는 symbolicRequest의 책장을 표시하고 그린다.
     * @param symbolicRequest
     */
    @Override
    public void getMap(String symbolicRequest) {

    }
}
