package io.shelfy.presentation.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Supplier;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.component.ActivityComponent;

public abstract class BaseActivity extends AppCompatActivity {

    protected ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DomainComponent appComponent = ((Supplier<DomainComponent>) getApplication()).get();
        activityComponent = new ActivityComponent(appComponent, this);
    }
}
