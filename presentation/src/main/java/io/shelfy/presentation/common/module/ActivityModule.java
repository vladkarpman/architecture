package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;

public interface ActivityModule extends PresentationModule {

    @NonNull
    ScreensNavigator getScreensNavigator();
}
