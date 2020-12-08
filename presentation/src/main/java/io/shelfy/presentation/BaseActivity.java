package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.ApplicationComponent;
import io.shelfy.presentation.common.component.BaseActivityComponent;
import io.shelfy.presentation.common.module.BaseActivityModule;
import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;
import io.shelfy.presentation.common.screensnavigator.ScreensNavigatorImpl;

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
