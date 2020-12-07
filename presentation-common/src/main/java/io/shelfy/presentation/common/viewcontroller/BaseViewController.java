package io.shelfy.presentation.common.viewcontroller;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposables;
import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.viewmodel.CommonViewModel;

public abstract class BaseViewController<V extends CommonView, VM extends CommonViewModel>
        implements CommonViewController<V, VM> {

    protected final CompositeDisposable onPauseDisposables = new CompositeDisposable();
    protected final CompositeDisposable onStopDisposables = new CompositeDisposable();
    protected final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @NonNull
    protected final V view;

    @NonNull
    protected final VM viewModel;

    protected BaseViewController(@NonNull V view, @NonNull VM viewModel) {
        this.view = view;
        this.viewModel = viewModel;
        onDestroyDisposables.addAll(
                Disposables.fromAction(view::onDestroy),
                Disposables.fromAction(viewModel::onDestroy)
        );
    }

    @CallSuper
    public void attachToLifecycle(@NonNull LifecycleOwner lifecycleOwner) {
        final ControllerLifecycleObserver lifecycleObserver = new ControllerLifecycleObserver(this);
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(lifecycleObserver);
        onDestroyDisposables.add(Disposables.fromAction(() -> lifecycle.removeObserver(lifecycleObserver)));
    }

    @NonNull
    public final V getView() {
        return view;
    }

    @NonNull
    public final VM getViewModel() {
        return viewModel;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @CallSuper
    @Override
    public void onPause() {
        onPauseDisposables.clear();
    }

    @CallSuper
    @Override
    public void onStop() {
        onStopDisposables.clear();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        onDestroyDisposables.clear();
    }

    private static class ControllerLifecycleObserver implements DefaultLifecycleObserver {

        @NonNull
        private final CommonViewController<?, ?> controller;

        private ControllerLifecycleObserver(@NonNull CommonViewController<?, ?> controller) {
            this.controller = controller;
        }

        @Override
        public void onCreate(@NonNull LifecycleOwner owner) {
            controller.onCreate();
        }

        @Override
        public void onStart(@NonNull LifecycleOwner owner) {
            controller.onStart();
        }

        @Override
        public void onResume(@NonNull LifecycleOwner owner) {
            controller.onResume();
        }

        @Override
        public void onPause(@NonNull LifecycleOwner owner) {
            controller.onPause();
        }

        @Override
        public void onStop(@NonNull LifecycleOwner owner) {
            controller.onStop();
        }

        @Override
        public void onDestroy(@NonNull LifecycleOwner owner) {
            controller.onDestroy();
        }
    }
}
