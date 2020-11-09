package io.shelfy.architecture.presentation.common.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.architecture.common.component.AppComponent;
import io.shelfy.architecture.presentation.common.viewmodel.ViewModelFactory;

abstract class PresentationComponent {

    @NonNull
    protected final AppComponent appComponent;

    @NonNull
    protected final ViewModelStoreOwner storeOwner;

    private ViewModelProvider viewModelProvider;

    private ViewModelProvider.Factory viewModelFactory;

    PresentationComponent(@NonNull AppComponent appComponent, @NonNull ViewModelStoreOwner storeOwner) {
        this.appComponent = appComponent;
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
                    appComponent.provideGetPopularMoviesUseCase(),
                    appComponent.provideGetMoviesByQueryUseCase(),
                    appComponent.provideGetMovieTrailerUseCase(),
                    appComponent.provideGetMovieByIdUseCase()
            );
        }
        return viewModelFactory;
    }
}
