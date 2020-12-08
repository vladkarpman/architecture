package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;
import io.shelfy.presentation.common.view.factory.ViewFactory;
import io.shelfy.presentation.common.viewmodel.ViewModelFactory;

public class BaseActivityModule extends BasePresentationModule implements ActivityModule {

    @NonNull
    private final ScreensNavigator screensNavigator;

    public BaseActivityModule(@NonNull AppCompatActivity activity,
                              @NonNull ScreensNavigator screensNavigator,
                              @NonNull ViewModelFactory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(activity, activity, viewModelFactory, viewFactory);
        this.screensNavigator = screensNavigator;
    }

    @io.reactivex.annotations.NonNull
    @Override
    public ScreensNavigator getScreensNavigator() {
        return screensNavigator;
    }
}
