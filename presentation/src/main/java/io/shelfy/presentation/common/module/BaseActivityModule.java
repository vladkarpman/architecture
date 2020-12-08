package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;
import io.shelfy.presentation.common.view.factory.ViewFactory;

public class BaseActivityModule extends BasePresentationModule implements ActivityModule {

    @NonNull
    private final ScreensNavigator screensNavigator;

    public BaseActivityModule(@NonNull AppCompatActivity activity,
                              @NonNull ScreensNavigator screensNavigator,
                              @NonNull ViewModelProvider.Factory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(activity, activity, viewModelFactory, viewFactory);
        this.screensNavigator = screensNavigator;
    }

    @Override
    public ScreensNavigator getScreensNavigator() {
        return screensNavigator;
    }
}
