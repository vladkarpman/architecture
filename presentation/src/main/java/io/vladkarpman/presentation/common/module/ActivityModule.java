package io.vladkarpman.presentation.common.module;

import androidx.annotation.NonNull;

import io.vladkarpman.presentation.common.screensnavigator.ScreensNavigator;

public interface ActivityModule extends PresentationModule {

    @NonNull
    ScreensNavigator getScreensNavigator();
}
