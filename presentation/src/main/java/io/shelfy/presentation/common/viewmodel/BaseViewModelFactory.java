package io.shelfy.presentation.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

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
