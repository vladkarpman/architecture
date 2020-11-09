package io.shelfy.architecture;

import android.app.Application;

import androidx.annotation.NonNull;

import io.shelfy.architecture.common.component.AppComponent;


public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = new AppComponent(this);
    }

    @NonNull
    public AppComponent getAppComponent() {
        if (appComponent == null) {
            throw new IllegalStateException("This function shouldn't be called if root is not initialized yet");
        }
        return appComponent;
    }
}
