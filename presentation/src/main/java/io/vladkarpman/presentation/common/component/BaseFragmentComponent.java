package io.vladkarpman.presentation.common.component;

import androidx.annotation.NonNull;

import io.vladkarpman.presentation.common.module.FragmentModule;

public class BaseFragmentComponent implements FragmentComponent {

    @NonNull
    private final ActivityComponent activityComponent;

    @NonNull
    private final FragmentModule presentationModule;

    public BaseFragmentComponent(@NonNull ActivityComponent activityComponent,
                                 @NonNull FragmentModule presentationModule) {
        this.activityComponent = activityComponent;
        this.presentationModule = presentationModule;
    }

    @NonNull
    @Override
    public ActivityComponent getParentComponent() {
        return activityComponent;
    }

    @NonNull
    @Override
    public FragmentModule getPresentationModule() {
        return presentationModule;
    }

    @Override
    public void destroy() {

    }
}
