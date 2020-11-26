package io.shelfy.presentation.common.view.factory;

import androidx.annotation.NonNull;

import io.shelfy.presentation.common.view.CommonView;

public interface ViewFactory {

    @NonNull
    <T extends CommonView> T create(Class<T> viewClass);

    default <T extends CommonView> void clear(Class<T> viewClass) {

    }

    default void clearAll() {

    }
}
