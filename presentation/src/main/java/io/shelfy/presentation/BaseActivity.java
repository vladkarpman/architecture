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

public abstract class BaseActivity extends CommonActivity {

    protected ScreenNavigator screenNavigator;

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenNavigator = createScreenNavigator();
    }

    @NonNull
    public abstract ScreenNavigator createScreenNavigator();

    @NonNull
    public final ScreenNavigator getScreenNavigator() {
        if (screenNavigator == null) {
            throw new RuntimeException("Screen Navigator has to be initialized");
        }
        return screenNavigator;
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
