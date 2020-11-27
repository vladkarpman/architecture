package io.shelfy.presentation.common;

import android.os.Bundle;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Supplier;

import io.shelfy.domain.CommonApplication;
import io.shelfy.domain.DomainModule;
import io.shelfy.presentation.common.module.ActivityModuleImpl;

public abstract class CommonActivity extends AppCompatActivity {

    protected ActivityModuleImpl activityComponent;

    @Override
    @CallSuper
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DomainModule domainModule;
        try {
            domainModule = ((CommonApplication) getApplication()).getDomainModule();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Application class must provide domain component!");
        }
        activityComponent = createActivityModule(domainModule);
    }

    @NonNull
    protected abstract ActivityModuleImpl createActivityModule(@NonNull DomainModule domainModule);
}
