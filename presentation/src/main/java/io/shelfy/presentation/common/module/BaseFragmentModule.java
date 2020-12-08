package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.shelfy.presentation.common.view.factory.ViewFactory;
import io.shelfy.presentation.common.viewmodel.factory.ViewModelFactory;

public class BaseFragmentModule extends BasePresentationModule implements FragmentModule {

    public BaseFragmentModule(@NonNull Fragment fragment,
                              @NonNull ViewModelFactory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(fragment, fragment, viewModelFactory, viewFactory);
    }
}
