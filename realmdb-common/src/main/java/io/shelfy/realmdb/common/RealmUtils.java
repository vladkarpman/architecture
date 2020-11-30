package io.shelfy.realmdb.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.core.util.Supplier;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Completable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.shelfy.utils.Suppliers;

/**
 * set of utility methods
 */
public class RealmUtils {

    private static final Supplier<Scheduler> REALM_SCHEDULER_SUPPLIER = Suppliers.memoize(() -> Schedulers.from(
            Executors.newCachedThreadPool(new RealmThreadFactory("Realm Thread"))));

    @NonNull
    static Scheduler realmScheduler() {
        return REALM_SCHEDULER_SUPPLIER.get();
    }

    static <T> Single<T> fetchAsync(
            @NonNull RealmConfiguration realmConfiguration,
            @NonNull io.reactivex.functions.Function<Realm, T> fetcher) {
        return Single.<T>create(emitter -> {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            final Looper looper = Objects.requireNonNull(Looper.myLooper());
            final Handler handler = new Handler(looper);
            emitter.setDisposable(Disposables.fromAction(() -> {
                handler.removeCallbacksAndMessages(null);
                looper.quitSafely();
            }));
            handler.post(() -> {
                try (Realm realm = Realm.getInstance(realmConfiguration)) {
                    final T result = fetcher.apply(realm);
                    emitter.onSuccess(result);
                } catch (Exception error) {
                    emitter.onError(error);
                }
            });
            Looper.loop();
        }).subscribeOn(realmScheduler());
    }

    static Completable executeAsync(@NonNull RealmConfiguration realmConfiguration,
                                    @NonNull Realm.Transaction transaction) {
        return Completable.create(emitter -> {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            final Looper looper = Objects.requireNonNull(Looper.myLooper());
            final Handler handler = new Handler(looper);
            emitter.setDisposable(Disposables.fromAction(() -> {
                handler.removeCallbacksAndMessages(null);
                looper.quitSafely();
            }));
            handler.post(() -> {
                try (Realm realm = Realm.getInstance(realmConfiguration)) {
                    realm.executeTransaction(transaction);
                    emitter.onComplete();
                } catch (Exception error) {
                    emitter.onError(error);
                }
            });
            Looper.loop();
        }).subscribeOn(realmScheduler());
    }

    static <T> Single<T> executeAndFetchAsync(
            @NonNull RealmConfiguration realmConfiguration,
            io.reactivex.functions.Function<Realm, T> fetcher) {
        return Single.<T>create(emitter -> {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            final Looper looper = Objects.requireNonNull(Looper.myLooper());
            final Handler handler = new Handler(looper);
            emitter.setDisposable(Disposables.fromAction(() -> {
                handler.removeCallbacksAndMessages(null);
                looper.quitSafely();
            }));
            handler.post(() -> {
                try (Realm realm = Realm.getInstance(realmConfiguration)) {
                    realm.executeTransaction(realmInstance -> {
                        try {
                            emitter.onSuccess(fetcher.apply(realmInstance));
                        } catch (Exception e) {
                            emitter.onError(e);
                        }
                    });
                } catch (Exception error) {
                    emitter.onError(error);
                }
            });
            Looper.loop();
        }).subscribeOn(realmScheduler());
    }

    private static class RealmThreadFactory implements ThreadFactory {

        private final AtomicInteger threadCount;

        @NonNull
        private final String identification;

        public RealmThreadFactory(@NonNull String identification) {
            this.identification = identification;
            this.threadCount = new AtomicInteger();
        }

        @Override
        public Thread newThread(Runnable r) {
            return new HandlerThread(getName());
        }

        @NonNull
        String getName() {
            return identification + threadCount.getAndIncrement();
        }
    }
}
