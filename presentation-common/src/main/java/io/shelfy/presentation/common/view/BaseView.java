package io.shelfy.presentation.common.view;

import android.content.Context;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseView implements CommonView {

    private View rootView;

    protected final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @NonNull
    public View getRootView() {
        if (rootView == null) {
            throw new IllegalStateException("You muse initialize root view before!");
        }
        return rootView;
    }

    protected void setRootView(View rootView) {
        this.rootView = rootView;
    }

    protected <T extends View> T findViewById(int id) {
        return getRootView().findViewById(id);
    }

    protected Context getContext() {
        return getRootView().getContext();
    }

    protected String getString(@StringRes int id) {
        return getContext().getString(id);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        onDestroyDisposables.clear();
    }
}
