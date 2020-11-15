package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.viewmodel.ViewModelFactory;

abstract class PresentationComponent {

    @NonNull
    protected final DomainComponent domainComponent;

    @NonNull
    protected final ViewModelStoreOwner storeOwner;

    private ViewModelProvider viewModelProvider;

    private ViewModelProvider.Factory viewModelFactory;

    PresentationComponent(@NonNull DomainComponent domainComponent, @NonNull ViewModelStoreOwner storeOwner) {
        this.domainComponent = domainComponent;
        this.storeOwner = storeOwner;
    }

    public <VM extends ViewModel> VM provideViewModel(Class<VM> viewModelClass) {
        return provideViewModelProvider().get(viewModelClass);
    }

    @NonNull
    protected ViewModelProvider provideViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(storeOwner, provideViewModelFactory());
        }
        return viewModelProvider;
    }

    protected ViewModelProvider.Factory provideViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(
                    domainComponent.provideGetPopularMoviesUseCase(),
                    domainComponent.provideGetMoviesByQueryUseCase(),
                    domainComponent.provideGetMovieTrailerUseCase(),
                    domainComponent.provideGetMovieByIdUseCase()
            );
        }
        return viewModelFactory;
    }
}
