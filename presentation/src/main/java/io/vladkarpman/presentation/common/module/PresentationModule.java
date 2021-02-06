package io.vladkarpman.presentation.common.module;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.vladkarpman.presentation.common.view.CommonView;
import io.vladkarpman.presentation.common.viewmodel.CommonViewModel;

public interface PresentationModule {
    @NonNull
    <VM extends CommonViewModel> VM provideViewModel(@NonNull Class<VM> viewModelClass);

    @NonNull
    <V extends CommonView> V provideView(@NonNull Class<V> viewClass, @Nullable ViewGroup container);
}
