package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.ActivityModuleImpl;
import io.shelfy.presentation.common.component.FragmentModuleImpl;
import io.shelfy.presentation.common.component.PresentationModule;

public class BaseFragment extends CommonFragment {

    @NonNull
    @Override
    protected PresentationModule createFragmentModule(@NonNull ActivityModuleImpl activityComponent) {
        return new FragmentModuleImpl(activityComponent, this, this);
    }
}
