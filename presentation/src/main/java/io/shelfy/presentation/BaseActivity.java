package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;
import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.module.ActivityModule;
import io.shelfy.presentation.common.module.ActivityModuleImpl;

public class BaseActivity extends CommonActivity {

    @NonNull
    @Override
    protected ActivityModule createActivityModule(@NonNull DomainModule domainModule) {
        return new ActivityModuleImpl(domainModule,
                this,
                this,
                new ViewModelFactory(domainModule),
                new ViewFactoryImpl());
    }
}
