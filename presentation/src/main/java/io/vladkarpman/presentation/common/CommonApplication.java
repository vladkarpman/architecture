package io.vladkarpman.presentation.common;

import android.app.Application;

import androidx.annotation.CallSuper;

import io.reactivex.annotations.NonNull;
import io.vladkarpman.presentation.common.component.ApplicationComponent;

public abstract class CommonApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    @CallSuper
    public void onCreate() {
        super.onCreate();

        applicationComponent = createApplicationComponent();
    }

    @NonNull
    public abstract ApplicationComponent createApplicationComponent();

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
