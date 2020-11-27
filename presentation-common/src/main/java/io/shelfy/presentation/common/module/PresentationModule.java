package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import io.shelfy.presentation.common.view.CommonView;

public interface PresentationModule {
    @NonNull
    <VM extends ViewModel> VM provideViewModel(@NonNull Class<VM> viewModelClass);

    @NonNull
    <V extends CommonView> V provideView(@NonNull Class<V> viewClass);
}
