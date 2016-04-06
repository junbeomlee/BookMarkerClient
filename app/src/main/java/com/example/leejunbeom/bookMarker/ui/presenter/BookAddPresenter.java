package com.example.leejunbeom.bookMarker.ui.presenter;

import com.example.leejunbeom.bookMarker.ui.screen_contracts.BookAddScreen;
import com.example.leejunbeom.bookMarker.ui.screen_contracts.NaviScreen;

/**
 * Created by Jun on 16. 3. 30..
 */
public interface BookAddPresenter {
    void getBookData(String url);
    void finishActivity(BookAddScreen bookAddScreen);
}
