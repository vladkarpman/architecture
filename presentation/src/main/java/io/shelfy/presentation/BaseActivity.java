package io.shelfy.presentation;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.component.PresentationComponent;

class BaseActivity extends CommonActivity {

    @NonNull
    @Override
    protected PresentationComponent createActivityComponent(@NonNull DomainComponent domainComponent) {
        return null;
    }
}
