package io.shelfy.presentation.common.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public interface ViewModelFactory extends ViewModelProvider.Factory {
    @NonNull
    <T extends ViewModel> Class<T> getImplementationClass(Class<?> modelClass);
}
