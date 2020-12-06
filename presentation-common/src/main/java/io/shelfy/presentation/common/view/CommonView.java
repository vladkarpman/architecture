package io.shelfy.presentation.common.view;

import android.view.View;

import androidx.annotation.NonNull;

public interface CommonView {

    @NonNull
    View getRootView();

    void onDestroy();
}
