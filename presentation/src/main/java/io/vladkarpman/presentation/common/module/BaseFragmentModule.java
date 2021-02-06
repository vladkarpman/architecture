package io.vladkarpman.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import io.vladkarpman.presentation.common.view.factory.ViewFactory;
import io.vladkarpman.presentation.common.viewmodel.factory.ViewModelFactory;

public class BaseFragmentModule extends BasePresentationModule implements FragmentModule {

    public BaseFragmentModule(@NonNull Fragment fragment,
                              @NonNull ViewModelFactory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(fragment, fragment, viewModelFactory, viewFactory);
    }
}
