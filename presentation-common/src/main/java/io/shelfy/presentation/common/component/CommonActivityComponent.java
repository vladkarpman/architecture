package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.view.factory.ViewFactory;

public class CommonActivityComponent extends PresentationComponent {

    @NonNull
    private final DomainComponent domainComponent;

    public CommonActivityComponent(@NonNull DomainComponent domainComponent,
                                   @NonNull LifecycleOwner lifecycleOwner,
                                   @NonNull ViewModelStoreOwner storeOwner,
                                   @NonNull ViewModelProvider.Factory viewModelFactory,
                                   @NonNull ViewFactory viewFactory) {
        super(lifecycleOwner, storeOwner, viewModelFactory, viewFactory);
        this.domainComponent = domainComponent;
    }

    @NonNull
    public DomainComponent getDomainComponent() {
        return domainComponent;
    }
}
