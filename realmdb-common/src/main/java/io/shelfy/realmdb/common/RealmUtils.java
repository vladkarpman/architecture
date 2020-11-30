package io.shelfy.realmdb.common;

import android.os.Handler;
import android.os.Looper;

import java.util.Objects;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposables;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * set of utility methods
 */
public class RealmUtils {

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
        }).subscribeOn(Schedulers.newThread());
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
        }).subscribeOn(Schedulers.newThread());
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
        }).subscribeOn(Schedulers.newThread());
    }
}
