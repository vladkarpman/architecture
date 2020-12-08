package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.presentation.common.view.factory.ViewFactory;

public class BaseActivityModule extends BasePresentationModule {

    public BaseActivityModule(@NonNull AppCompatActivity activity,
                              @NonNull ViewModelProvider.Factory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(activity, activity, viewModelFactory, viewFactory);
    }
}
