package io.shelfy.presentation.common;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.ApplicationComponent;
import io.shelfy.presentation.common.module.PresentationModule;

public abstract class CommonActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ApplicationComponent applicationComponent;
        try {
            applicationComponent = ((CommonApplication) getApplication()).getApplicationComponent();
        } catch (Exception e) {
            throw new IllegalStateException("Application must return Application Component");
        }
        activityComponent = createActivityComponent(applicationComponent);
    }

    @IdRes
    protected abstract int getFragmentContainer();

    @NonNull
    protected abstract ActivityComponent createActivityComponent(@NonNull ApplicationComponent applicationComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityComponent.destroy();
        activityComponent = null;
    }
}
