package io.vladkarpman.presentation;

import androidx.annotation.NonNull;

import io.vladkarpman.presentation.common.CommonActivity;
import io.vladkarpman.presentation.common.component.ActivityComponent;
import io.vladkarpman.presentation.common.component.ApplicationComponent;
import io.vladkarpman.presentation.common.component.BaseActivityComponent;
import io.vladkarpman.presentation.common.module.BaseActivityModule;
import io.vladkarpman.presentation.common.screensnavigator.ScreensNavigator;
import io.vladkarpman.presentation.common.screensnavigator.ScreensNavigatorImpl;

public abstract class BaseActivity extends CommonActivity {

    @NonNull
    public final ScreensNavigator getScreensNavigator() {
        return activityComponent.getPresentationModule().getScreensNavigator();
    }

    @NonNull
    @Override
    protected ActivityComponent createActivityComponent(@NonNull ApplicationComponent applicationComponent) {
        return new BaseActivityComponent(
                applicationComponent,
                new BaseActivityModule(
                        this,
                        new ScreensNavigatorImpl(this, getSupportFragmentManager(), getFragmentContainer()),
                        new ViewModelFactoryImpl(applicationComponent.getDomainModule()),
                        new ViewFactoryImpl(getLayoutInflater())));
    }
}
