package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.module.PresentationModule;

public class BaseActivityComponent implements ActivityComponent {

    @NonNull
    private final ApplicationComponent applicationComponent;

    @NonNull
    private final PresentationModule presentationModule;

    public BaseActivityComponent(@NonNull ApplicationComponent applicationComponent,
                                 @NonNull PresentationModule presentationModule) {
        this.applicationComponent = applicationComponent;
        this.presentationModule = presentationModule;
    }

    @NonNull
    @Override
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    @NonNull
    @Override
    public PresentationModule getPresentationModule() {
        return presentationModule;
    }

    @Override
    public void destroy() {

    }
}
