package io.vladkarpman.presentation.common.viewmodel;

import androidx.lifecycle.ViewModel;

public class DummyViewModel extends ViewModel implements CommonViewModel {

    @Override
    public void onDestroy() {
        // dummy
    }
}
