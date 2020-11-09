package io.shelfy.architecture.presentation.common.viewmodel;

import androidx.annotation.CallSuper;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel implements CommonViewModel {

    protected final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @Override
    protected final void onCleared() {
        super.onCleared();
        onDestroy();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        onDestroyDisposables.clear();
    }
}
