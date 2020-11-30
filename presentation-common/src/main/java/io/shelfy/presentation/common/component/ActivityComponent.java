package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.module.PresentationModule;

public interface ActivityComponent {

    @NonNull
    ApplicationComponent getApplicationComponent();

    @NonNull
    PresentationModule getPresentationModule();

    void destroy();
}
