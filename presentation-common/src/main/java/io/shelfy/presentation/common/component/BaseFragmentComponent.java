package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.module.PresentationModule;

public class BaseFragmentComponent implements FragmentComponent {

    @NonNull
    private final ActivityComponent activityComponent;

    @NonNull
    private final PresentationModule presentationModule;

    public BaseFragmentComponent(@NonNull ActivityComponent activityComponent,
                                 @NonNull PresentationModule presentationModule) {
        this.activityComponent = activityComponent;
        this.presentationModule = presentationModule;
    }

    @Override
    @NonNull
    public ActivityComponent getActivityComponent() {
        return activityComponent;
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
