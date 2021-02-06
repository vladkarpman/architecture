package io.vladkarpman.utils;

import androidx.core.util.Supplier;

import java.util.concurrent.atomic.AtomicReference;

public class Suppliers {

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        return new Supplier<T>() {

            final AtomicReference<T> cached = new AtomicReference<>();

            @Override
            public T get() {
                if (cached.get() == null) {
                    cached.set(supplier.get());
                }
                return cached.get();
            }
        };
    }
}
