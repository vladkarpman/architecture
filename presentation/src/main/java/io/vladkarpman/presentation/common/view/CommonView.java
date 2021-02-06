package io.vladkarpman.presentation.common.view;

import android.view.View;

import androidx.annotation.NonNull;

public interface CommonView {

    @NonNull
    View getRootView();

    void onDestroy();
}
