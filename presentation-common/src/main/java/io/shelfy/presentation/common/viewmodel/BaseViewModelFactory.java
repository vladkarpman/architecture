package io.shelfy.presentation.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import io.shelfy.domain.DomainComponent;

public abstract class BaseViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    protected final DomainComponent domainComponent;

    public BaseViewModelFactory(@NonNull DomainComponent domainComponent) {
        this.domainComponent = domainComponent;
    }

    @NonNull
    public DomainComponent getDomainComponent() {
        return domainComponent;
    }
}
