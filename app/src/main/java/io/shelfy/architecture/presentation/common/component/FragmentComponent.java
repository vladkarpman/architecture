package io.shelfy.architecture.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.architecture.presentation.common.BaseFragment;

public class FragmentComponent extends PresentationComponent {

    @NonNull
    private final ActivityComponent activityComponent;

    @NonNull
    private final BaseFragment baseFragment;

    public FragmentComponent(@NonNull ActivityComponent activityComponent, @NonNull BaseFragment baseFragment) {
        super(activityComponent.appComponent, baseFragment);
        this.activityComponent = activityComponent;
        this.baseFragment = baseFragment;
    }

    @NonNull
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
