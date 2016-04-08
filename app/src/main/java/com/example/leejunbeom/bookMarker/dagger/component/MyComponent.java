package com.example.leejunbeom.bookMarker.dagger.component;

import com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity;
import com.example.leejunbeom.bookMarker.ui.activity.BookInfoActivity;
import com.example.leejunbeom.bookMarker.ui.activity.MainActivity;

/**
 * Created by Jun on 16. 3. 28..
 */
public interface MyComponent {
    void inject(MainActivity mainActivity);
    void inject(BookAddActivity bookAddActivity);
    void inject(BookInfoActivity bookInfoActivity);
}
