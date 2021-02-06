package io.vladkarpman.presentation.common.component;

import androidx.annotation.NonNull;

import io.vladkarpman.presentation.common.module.ActivityModule;

public class BaseActivityComponent implements ActivityComponent {

    @NonNull
    private final ApplicationComponent applicationComponent;

    @NonNull
    private final ActivityModule presentationModule;

    public BaseActivityComponent(@NonNull ApplicationComponent applicationComponent,
                                 @NonNull ActivityModule presentationModule) {
        this.applicationComponent = applicationComponent;
        this.presentationModule = presentationModule;
    }

    @NonNull
    @Override
    public ApplicationComponent getParentComponent() {
        return applicationComponent;
    }

    @NonNull
    @Override
    public ActivityModule getPresentationModule() {
        return presentationModule;
    }

    @Override
    public void destroy() {

    }
}
