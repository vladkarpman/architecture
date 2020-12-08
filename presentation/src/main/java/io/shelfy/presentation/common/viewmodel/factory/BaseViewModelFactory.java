package io.shelfy.presentation.common.viewmodel.factory;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;

public abstract class BaseViewModelFactory implements ViewModelFactory {

    @NonNull
    protected final DomainModule domainModule;

    public BaseViewModelFactory(@NonNull DomainModule domainModule) {
        this.domainModule = domainModule;
    }

    @NonNull
    public DomainModule getDomainModule() {
        return domainModule;
    }
}
