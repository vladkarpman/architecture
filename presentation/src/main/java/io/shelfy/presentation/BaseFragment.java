package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.FragmentComponent;
import io.shelfy.presentation.common.component.BaseFragmentComponent;
import io.shelfy.presentation.common.module.BasePresentationModule;

public class BaseFragment extends CommonFragment {

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @NonNull
    @Override
    protected FragmentComponent createFragmentComponent(@NonNull ActivityComponent activityComponent) {
        return new BaseFragmentComponent(
                activityComponent,
                new BasePresentationModule(
                        this,
                        this,
                        new ViewModelFactory(activityComponent.getApplicationComponent().getDomainModule()),
                        new ViewFactoryImpl()));
    }
}
