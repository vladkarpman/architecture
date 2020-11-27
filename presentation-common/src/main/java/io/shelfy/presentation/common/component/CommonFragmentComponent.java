package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import io.shelfy.presentation.common.view.factory.ViewFactory;

public class CommonFragmentComponent extends PresentationComponent {

    @NonNull
    private final CommonActivityComponent activityComponent;

    public CommonFragmentComponent(@NonNull CommonActivityComponent activityComponent,
                                   @NonNull LifecycleOwner lifecycleOwner,
                                   @NonNull ViewModelStoreOwner storeOwner,
                                   @NonNull ViewModelProvider.Factory viewModelFactory,
                                   @NonNull ViewFactory viewFactory) {
        super(lifecycleOwner, storeOwner, viewModelFactory, viewFactory);
        this.activityComponent = activityComponent;
    }

    public CommonFragmentComponent(@NonNull CommonActivityComponent activityComponent,
                                   @NonNull LifecycleOwner lifecycleOwner,
                                   @NonNull ViewModelStoreOwner storeOwner) {
        super(lifecycleOwner, storeOwner, activityComponent.viewModelFactory, activityComponent.viewFactory);
        this.activityComponent = activityComponent;
    }

    @NonNull
    protected CommonActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
