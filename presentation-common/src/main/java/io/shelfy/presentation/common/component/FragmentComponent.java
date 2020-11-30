package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.module.PresentationModule;

public interface FragmentComponent {
    @NonNull
    ActivityComponent getActivityComponent();

    @NonNull
    PresentationModule getPresentationModule();

    void destroy();
}
