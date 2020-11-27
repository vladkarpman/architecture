package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.CommonActivityComponent;
import io.shelfy.presentation.common.component.CommonFragmentComponent;
import io.shelfy.presentation.common.component.PresentationComponent;

public class BaseFragment extends CommonFragment {

    @NonNull
    @Override
    protected PresentationComponent createFragmentComponent(@NonNull CommonActivityComponent activityComponent) {
        return new CommonFragmentComponent(activityComponent, this, this);
    }
}
