package io.shelfy.presentation.common.viewcontroller;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.view.DummyView;
import io.shelfy.presentation.common.viewmodel.CommonViewModel;
import io.shelfy.presentation.common.viewmodel.DummyViewModel;

interface CommonViewController<V extends CommonView, VM extends CommonViewModel> {

    @NonNull
    default V getView() {
        return (V) new DummyView();
    }

    @NonNull
    default VM getViewModel() {
        return (VM) new DummyViewModel();
    }

    default void onCreate() {

    }

    default void onStart() {

    }

    default void onResume() {

    }

    default void onPause() {

    }

    default void onStop() {

    }

    default void onDestroy() {

    }
}
