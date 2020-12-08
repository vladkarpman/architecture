package io.shelfy.presentation.common.module;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.view.factory.ViewFactory;
import io.shelfy.presentation.common.viewmodel.CommonViewModel;
import io.shelfy.presentation.common.viewmodel.factory.ViewModelFactory;

public class BasePresentationModule implements PresentationModule {

    @NonNull
    protected final LifecycleOwner lifecycleOwner;

    @NonNull
    protected final ViewModelStoreOwner storeOwner;

    private ViewModelProvider viewModelProvider;

    @NonNull
    final ViewModelFactory viewModelFactory;

    @NonNull
    final ViewFactory viewFactory;

    public BasePresentationModule(
            @NonNull LifecycleOwner lifecycleOwner,
            @NonNull ViewModelStoreOwner storeOwner,
            @NonNull ViewModelFactory viewModelFactory,
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
    public <VM extends CommonViewModel> VM provideViewModel(@NonNull Class<VM> viewModelClass) {
        return (VM) provideViewModelProvider().get(viewModelFactory.getImplementationClass(viewModelClass));
    }

    @Override
    @NonNull
    public <V extends CommonView> V provideView(@NonNull Class<V> viewClass, @Nullable ViewGroup container) {
        return viewFactory.create(viewClass, container);
    }

    @NonNull
    protected ViewModelProvider provideViewModelProvider() {
        if (viewModelProvider == null) {
            viewModelProvider = new ViewModelProvider(storeOwner, viewModelFactory);
        }
        return viewModelProvider;
    }
}
