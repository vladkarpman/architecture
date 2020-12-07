package io.shelfy.presentation;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.shelfy.presentation.common.component.ApplicationComponent;
import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.BaseActivityComponent;
import io.shelfy.presentation.common.module.BasePresentationModule;
import io.shelfy.presentation.screensnavigator.ScreensNavigator;

public abstract class BaseActivity extends CommonActivity {

    protected ScreensNavigator screensNavigator;

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screensNavigator = createScreenNavigator();
    }

    @NonNull
    public abstract ScreensNavigator createScreenNavigator();

    @NonNull
    public final ScreensNavigator getScreensNavigator() {
        if (screensNavigator == null) {
            throw new RuntimeException("Screen Navigator has to be initialized");
        }
        return screensNavigator;
    }

    @NonNull
    @Override
    protected ActivityComponent createActivityComponent(@NonNull ApplicationComponent applicationComponent) {
        return new BaseActivityComponent(
                applicationComponent,
                new BasePresentationModule(
                        this,
                        this,
                        new ViewModelFactory(applicationComponent.getDomainModule()),
                        new ViewFactoryImpl(getLayoutInflater())));
    }
}
