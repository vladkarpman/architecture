package io.shelfy.architecture.presentation.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.shelfy.architecture.App;
import io.shelfy.architecture.common.component.AppComponent;
import io.shelfy.architecture.presentation.common.component.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AppComponent appComponent = ((App) getApplication()).getAppComponent();
        activityComponent = new ActivityComponent(appComponent, this);
    }
}
