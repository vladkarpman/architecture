package io.shelfy.presentation.common;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Supplier;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.component.PresentationComponent;

public abstract class CommonActivity extends AppCompatActivity {

    protected PresentationComponent activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DomainComponent domainComponent;
        try {
            domainComponent = ((Supplier<DomainComponent>) getApplication()).get();
        } catch (ClassCastException e) {
            throw new IllegalStateException("Application class must provide domain component!");
        }
        activityComponent = createActivityComponent(domainComponent);
    }

    @NonNull
    protected abstract PresentationComponent createActivityComponent(@NonNull DomainComponent domainComponent);
}
