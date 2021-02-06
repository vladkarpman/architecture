package io.vladkarpman.presentation.common.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseView implements CommonView {

    private final View rootView;

    protected final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    public BaseView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        this.rootView = layoutInflater.inflate(getLayoutId(), parent, false);
    }

    public BaseView(@NonNull View rootView) {
        this.rootView = rootView;
    }

    @LayoutRes
    protected int getLayoutId() {
        throw new RuntimeException("You have to implement this method!");
    }

    @Override
    @NonNull
    public View getRootView() {
        if (rootView == null) {
            throw new IllegalStateException("You must initialize root view before!");
        }
        return rootView;
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
