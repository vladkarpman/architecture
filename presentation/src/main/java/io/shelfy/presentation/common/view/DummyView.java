package io.shelfy.presentation.common.view;

import android.view.View;

import androidx.annotation.NonNull;

public class DummyView implements CommonView {

    @NonNull
    @Override
    public View getRootView() {
        throw new RuntimeException("Dummy View doesn't have root View");
    }

    @Override
    public void onDestroy() {
        // dummy
    }
}
