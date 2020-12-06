package io.shelfy.presentation.common.view.factory;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

import io.shelfy.presentation.common.view.CommonView;

public abstract class BaseViewFactory implements ViewFactory {

    private final Map<Class<? extends CommonView>, CommonView> viewsStore = new HashMap<>();

    @NonNull
    @Override
    public final <T extends CommonView> T create(@NonNull Class<T> viewClass, @Nullable ViewGroup container) {
        return getOrCreate(viewClass, container);
    }

    protected abstract <T extends CommonView> T createInternal(@NonNull Class<T> viewClass, @Nullable ViewGroup container);

    @NonNull
    protected final <T extends CommonView> T getOrCreate(@NonNull Class<T> viewClass, @Nullable ViewGroup container) {
        final T cached = (T) viewsStore.get(viewClass);
        if (cached == null) {
            T created = createInternal(viewClass, container);
            viewsStore.put(viewClass, created);
            return created;
        }
        return cached;
    }

    @Override
    public <T extends CommonView> void clear(@NonNull Class<T> viewClass) {
        final CommonView view = viewsStore.get(viewClass);
        if (view != null) {
            view.onDestroy();
        }
        viewsStore.put(viewClass, null);
    }

    @Override
    public void clearAll() {
        for (CommonView view : viewsStore.values()) {
            view.onDestroy();
        }
        viewsStore.clear();
    }
}
