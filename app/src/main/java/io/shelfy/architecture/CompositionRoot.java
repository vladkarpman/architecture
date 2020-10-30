package io.shelfy.architecture;

import android.app.Application;

import androidx.annotation.NonNull;

public class CompositionRoot {

    @NonNull
    private final Application application;

    public CompositionRoot(@NonNull Application application) {
        this.application = application;
    }
}
