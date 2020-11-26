package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.PresentationComponent;

class BaseFragment extends CommonFragment {


    @NonNull
    @Override
    protected PresentationComponent createFragmentComponent(@NonNull PresentationComponent activityComponent) {
        return null;
    }
}
