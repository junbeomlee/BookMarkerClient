// Generated code from Butter Knife. Do not modify!
package com.example.leejunbeom.bookMarker.ui.activity;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class BookAddActivity$$ViewBinder<T extends com.example.leejunbeom.bookMarker.ui.activity.BookAddActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558495, "field 'confirmButton' and method 'onCallBack'");
    target.confirmButton = finder.castView(view, 2131558495, "field 'confirmButton'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onCallBack();
        }
      });
    view = finder.findRequiredView(source, 2131558496, "field 'bookTitieView'");
    target.bookTitieView = finder.castView(view, 2131558496, "field 'bookTitieView'");
  }

  @Override public void unbind(T target) {
    target.confirmButton = null;
    target.bookTitieView = null;
  }
}
