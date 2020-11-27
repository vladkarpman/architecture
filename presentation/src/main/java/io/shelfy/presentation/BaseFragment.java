package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.module.ActivityModule;
import io.shelfy.presentation.common.module.ActivityModuleImpl;
import io.shelfy.presentation.common.module.FragmentModule;
import io.shelfy.presentation.common.module.FragmentModuleImpl;
import io.shelfy.presentation.common.module.PresentationModule;

public class BaseFragment extends CommonFragment {

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @NonNull
    @Override
    protected FragmentModule createFragmentModule(@NonNull ActivityModule activityComponent) {
        return new FragmentModuleImpl(activityComponent, this, this);
    }
}
