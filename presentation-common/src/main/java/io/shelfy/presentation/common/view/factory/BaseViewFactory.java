package io.shelfy.presentation.common.view.factory;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import io.shelfy.presentation.common.view.CommonView;

public abstract class BaseViewFactory implements ViewFactory {

    private final Map<Class<? extends CommonView>, CommonView> viewsStore = new HashMap<>();

    @NonNull
    @Override
    public final <T extends CommonView> T create(Class<T> viewClass) {
        return getOrCreate(viewClass);
    }

    protected abstract <T extends CommonView> T createInternal(Class<T> viewClass);

    @NonNull
    protected final <T extends CommonView> T getOrCreate(Class<T> viewClass) {
        final T cached = (T) viewsStore.get(viewClass);
        if (cached == null) {
            T created = createInternal(viewClass);
            viewsStore.put(viewClass, created);
            return created;
        }
        return cached;
    }

    @Override
    public <T extends CommonView> void clear(Class<T> viewClass) {
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
