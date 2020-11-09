package io.shelfy.architecture.presentation.common.controller;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;


import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.shelfy.architecture.presentation.common.view.CommonView;
import io.shelfy.architecture.presentation.common.viewmodel.CommonViewModel;

public abstract class BaseController<V extends CommonView, VM extends CommonViewModel> implements CommonController {

    protected final CompositeDisposable onPauseDisposables = new CompositeDisposable();
    protected final CompositeDisposable onStopDisposables = new CompositeDisposable();
    protected final CompositeDisposable onDestroyDisposables = new CompositeDisposable();

    @NonNull
    protected final V view;

    @NonNull
    protected final VM viewModel;

    protected BaseController(
            @NonNull V view,
            @NonNull VM viewModel) {
        this.view = view;
        this.viewModel = viewModel;

        onDestroyDisposables.addAll(
                Disposables.fromAction(view::onDestroy),
                Disposables.fromAction(viewModel::onDestroy)
        );
    }

    protected BaseController(@NonNull LifecycleOwner lifecycleOwner,
                             @NonNull V view,
                             @NonNull VM viewModel) {
        this(view, viewModel);
        final ControllerLifecycleObserver lifecycleObserver = new ControllerLifecycleObserver(this);
        final Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(lifecycleObserver);
        onDestroyDisposables.add(Disposables.fromAction(() -> lifecycle.removeObserver(lifecycleObserver)));
    }

    @NonNull
    protected final V getView() {
        return view;
    }

    @NonNull
    protected final VM getViewModel() {
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
    public void onStop() {
        onStopDisposables.clear();
    }

    @CallSuper
    @Override
    public void onPause() {
        onPauseDisposables.clear();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        onDestroyDisposables.clear();
    }

    private static class ControllerLifecycleObserver implements DefaultLifecycleObserver {
        @NonNull
        private final CommonController controller;

        private ControllerLifecycleObserver(@NonNull CommonController controller) {
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
