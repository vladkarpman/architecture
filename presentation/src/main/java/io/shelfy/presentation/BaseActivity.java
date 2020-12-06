package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.component.ApplicationComponent;
import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.BaseActivityComponent;
import io.shelfy.presentation.common.module.BasePresentationModule;

public class BaseActivity extends CommonActivity {

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
