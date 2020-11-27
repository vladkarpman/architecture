package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;
import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.module.ActivityModuleImpl;

class BaseActivity extends CommonActivity {

    @NonNull
    @Override
    protected ActivityModuleImpl createActivityModule(@NonNull DomainModule domainModule) {
        return new ActivityModuleImpl(domainModule,
                this,
                this,
                new ViewModelFactory(domainModule),
                new ViewFactoryImpl());
    }
}
