package io.shelfy.realmdb;


import android.os.HandlerThread;

import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;
import androidx.core.util.Supplier;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.internal.async.RealmThreadPoolExecutor;
import io.shelfy.utils.Mapper;
import io.shelfy.utils.Suppliers;


class BaseRealmDB {

    private static final Supplier<Scheduler> REALM_SCHEDULER_SUPPLIER = Suppliers.memoize(() ->
            Schedulers.from(RealmThreadPoolExecutor.newDefaultExecutor()));

    @NonNull
    public static Scheduler realmScheduler() {
        return REALM_SCHEDULER_SUPPLIER.get();
    }

    @NonNull
    private final RealmConfiguration realmConfiguration;

    BaseRealmDB(@NonNull RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public <T> Single<T> read(@NonNull Function<Realm, T> reader) {
        return fetchAsync(realmConfiguration, reader::apply);
    }

    public <T extends RealmModel, E> Single<List<E>> read(@NonNull Class<T> clazz, @NonNull Mapper<T, E> mapper) {
        return fetchAsync(realmConfiguration, realm ->
                Observable.fromIterable(realm.where(clazz).findAll())
                        .map(mapper::map)
                        .toList()
                        .blockingGet());
    }

    public <T extends RealmModel, E> Single<List<E>> read(
            @NonNull Class<T> clazz,
            @NonNull Mapper<RealmQuery<T>, RealmQuery<T>> realmQueryMapper,
            @NonNull Mapper<T, E> objectsMapper) {
        return fetchAsync(realmConfiguration, realm ->
                Observable.fromIterable(realmQueryMapper.map(realm.where(clazz)).findAll())
                        .map(objectsMapper::map)
                        .toList()
                        .blockingGet());
    }

    public <T extends RealmModel> Completable create(Collection<T> objects) {
        return executeAsync(realmConfiguration, realm -> realm.insert(objects));
    }

    public <E, T extends RealmModel> Completable create(Collection<E> objects, @NonNull Mapper<E, T> mapper) {
        return Observable.fromIterable(objects)
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::create);
    }

    public Completable execute(@NonNull Consumer<Realm> executeFunction) {
        return executeAsync(realmConfiguration, executeFunction::accept);
    }

    public <T extends RealmModel> Completable update(
            @NonNull Class<T> clazz,
            @NonNull Function<RealmQuery<T>, RealmQuery<T>> fetchFunction) {
        return executeAsync(realmConfiguration, realm -> {
            final RealmResults<T> results = fetchFunction.apply(realm.where(clazz)).findAll();
            realm.insertOrUpdate(results);
        });
    }

    public <T extends RealmModel> Completable update(T object) {
        return executeAsync(realmConfiguration, realm -> realm.insertOrUpdate(Collections.singleton(object)));
    }

    public <T extends RealmModel, E> Completable update(E object, @NonNull Mapper<E, T> mapper) {
        return update(Collections.singleton(mapper.map(object)));
    }

    public <T extends RealmModel> Completable update(@NonNull Collection<T> objects) {
        return executeAsync(realmConfiguration, realm -> realm.insertOrUpdate(objects));
    }

    public <From, To extends RealmModel> Completable update(@NonNull Collection<From> objects,
                                                            @NonNull Mapper<From, To> mapper) {
        return Observable.fromIterable(objects)
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::update);
    }

    public static <T> Single<T> fetchAsync(
            @NonNull RealmConfiguration realmConfiguration,
            @NonNull io.reactivex.functions.Function<Realm, T> fetcher) {
        return Single.<T>create(emitter -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                final T result = fetcher.apply(realm);
                emitter.onSuccess(result);
            } catch (Exception error) {
                emitter.onError(error);
            }
        }).subscribeOn(realmScheduler());
    }

    public static Completable executeAsync(@NonNull RealmConfiguration realmConfiguration,
                                           @NonNull Realm.Transaction transaction) {
        return Completable.create(emitter -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                realm.executeTransaction(transaction);
                emitter.onComplete();
            } catch (Exception error) {
                emitter.onError(error);
            }
        }).subscribeOn(realmScheduler());
    }

    public static <T> Single<T> executeAndFetchAsync(
            @NonNull RealmConfiguration realmConfiguration,
            @NonNull io.reactivex.functions.Function<Realm, T> fetcher) {
        return Single.<T>create(emitter -> {
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
        }).subscribeOn(realmScheduler());
    }
}
