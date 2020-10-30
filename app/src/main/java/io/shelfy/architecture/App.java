package io.shelfy.architecture;

import android.app.Application;

import androidx.annotation.NonNull;


public class App extends Application {

    private CompositionRoot compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        compositionRoot = new CompositionRoot(this);
    }

    @NonNull
    public CompositionRoot getCompositionRoot() {
        if (compositionRoot == null) {
            throw new IllegalStateException("This function shouldn't be called if root is not initialized yet");
        }
        return compositionRoot;
    }
}
