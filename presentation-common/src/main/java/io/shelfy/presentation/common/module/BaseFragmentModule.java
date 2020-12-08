package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.presentation.common.view.factory.ViewFactory;

public class BaseFragmentModule extends BasePresentationModule {

    public BaseFragmentModule(@NonNull Fragment fragment,
                              @NonNull ViewModelProvider.Factory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(fragment, fragment, viewModelFactory, viewFactory);
    }
}
