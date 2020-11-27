package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.module.ActivityModuleImpl;
import io.shelfy.presentation.common.module.FragmentModuleImpl;
import io.shelfy.presentation.common.module.PresentationModule;

public class BaseFragment extends CommonFragment {

    @NonNull
    @Override
    protected PresentationModule createFragmentModule(@NonNull ActivityModuleImpl activityComponent) {
        return new FragmentModuleImpl(activityComponent, this, this);
    }
}
