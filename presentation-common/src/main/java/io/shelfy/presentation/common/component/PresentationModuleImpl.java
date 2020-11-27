package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.view.DummyView;
import io.shelfy.presentation.common.view.factory.ViewFactory;
import io.shelfy.presentation.common.viewmodel.DummyViewModel;

public class PresentationModuleImpl implements PresentationModule {

    @NonNull
    protected final LifecycleOwner lifecycleOwner;

    @NonNull
    protected final ViewModelStoreOwner storeOwner;

    private ViewModelProvider viewModelProvider;

    @NonNull
    final ViewModelProvider.Factory viewModelFactory;

    @NonNull
    final ViewFactory viewFactory;

    public PresentationModuleImpl(
            @NonNull LifecycleOwner lifecycleOwner,
            @NonNull ViewModelStoreOwner storeOwner,
            @NonNull ViewModelProvider.Factory viewModelFactory,
            @NonNull ViewFactory viewFactory) {
        this.lifecycleOwner = lifecycleOwner;
        this.storeOwner = storeOwner;
        this.viewModelFactory = viewModelFactory;
        this.viewFactory = viewFactory;

        // clear view factory
        lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() {

            @Override
            public void onDestroy(@NonNull LifecycleOwner owner) {
                owner.getLifecycle().removeObserver(this);
                viewFactory.clearAll();
            }
        });
    }

    @Override
    @NonNull
    public <VM extends ViewModel> VM provideViewModel(@NonNull Class<VM> viewModelClass) {
        try {
            return provideViewModelProvider().get(viewModelClass);
        } catch (Exception e) {
            return (VM) new DummyViewModel();
        }
    }

    @Override
    @NonNull
    public <V extends CommonView> V provideView(@NonNull Class<V> viewClass) {
        try {
            return viewFactory.create(viewClass);
        } catch (Exception e) {
            return (V) new DummyView();
        }
    }

    @NonNull
    protected ViewModelProvider provideViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(storeOwner, viewModelFactory);
        }
        return viewModelProvider;
    }
}
