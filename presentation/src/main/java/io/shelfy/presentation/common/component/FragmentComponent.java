package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.BaseFragment;

public class FragmentComponent extends PresentationComponent {

    @NonNull
    private final ActivityComponent activityComponent;

    @NonNull
    private final BaseFragment baseFragment;

    public FragmentComponent(@NonNull ActivityComponent activityComponent, @NonNull BaseFragment baseFragment) {
        super(activityComponent.domainComponent, baseFragment);
        this.activityComponent = activityComponent;
        this.baseFragment = baseFragment;
    }

    @NonNull
    public ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
