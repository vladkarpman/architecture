package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.domain.DomainModule;
import io.shelfy.presentation.common.view.factory.ViewFactory;

public class ActivityModuleImpl extends PresentationModuleImpl implements ActivityModule {

    @NonNull
    private final DomainModule domainModule;

    public ActivityModuleImpl(@NonNull DomainModule domainModule,
                              @NonNull LifecycleOwner lifecycleOwner,
                              @NonNull ViewModelStoreOwner storeOwner,
                              @NonNull ViewModelProvider.Factory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(lifecycleOwner, storeOwner, viewModelFactory, viewFactory);
        this.domainModule = domainModule;
    }

    @Override
    @NonNull
    public DomainModule getDomainModule() {
        return domainModule;
    }
}
