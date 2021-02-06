package io.vladkarpman.presentation.common.view.factory;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.vladkarpman.presentation.common.view.CommonView;

public interface ViewFactory {

    @NonNull
    <T extends CommonView> T create(@NonNull Class<T> viewClass, @Nullable ViewGroup container);

    default <T extends CommonView> void clear(@NonNull Class<T> viewClass) {

    }

    default void clearAll() {

    }
}
