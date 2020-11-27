package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.presentation.common.view.factory.ViewFactory;

public class FragmentModuleImpl extends PresentationModuleImpl implements FragmentModule {

    @NonNull
    private final ActivityModule activityComponent;

    public FragmentModuleImpl(@NonNull ActivityModule activityComponent,
                              @NonNull LifecycleOwner lifecycleOwner,
                              @NonNull ViewModelStoreOwner storeOwner,
                              @NonNull ViewModelProvider.Factory viewModelFactory,
                              @NonNull ViewFactory viewFactory) {
        super(lifecycleOwner, storeOwner, viewModelFactory, viewFactory);
        this.activityComponent = activityComponent;
    }

    public FragmentModuleImpl(@NonNull ActivityModuleImpl activityComponent,
                              @NonNull LifecycleOwner lifecycleOwner,
                              @NonNull ViewModelStoreOwner storeOwner) {
        super(lifecycleOwner, storeOwner, activityComponent.viewModelFactory, activityComponent.viewFactory);
        this.activityComponent = activityComponent;
    }

    @Override
    @NonNull
    public ActivityModule getActivityComponent() {
        return activityComponent;
    }
}
