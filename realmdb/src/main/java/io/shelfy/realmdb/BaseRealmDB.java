package io.shelfy.realmdb;


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

    @NonNull
    static Scheduler realmScheduler() {
        return Schedulers.io();
    }

    @NonNull
    private final RealmConfiguration realmConfiguration;

    BaseRealmDB(@NonNull RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public <T> Single<T> get(@NonNull Function<Realm, T> query) {
        return get(realmConfiguration, query::apply);
    }

    public <T extends RealmModel, E> Single<List<E>> get(@NonNull Class<T> clazz, @NonNull Mapper<T, E> mapper) {
        return get(realmConfiguration, realm ->
                Observable.fromIterable(realm.where(clazz).findAll())
                        .map(mapper::map)
                        .toList()
                        .blockingGet());
    }

    public <T extends RealmModel, E> Single<List<E>> get(
            @NonNull Class<T> clazz,
            @NonNull Mapper<RealmQuery<T>, RealmQuery<T>> realmQueryMapper,
            @NonNull Mapper<T, E> objectsMapper) {
        return get(realmConfiguration, realm ->
                Observable.fromIterable(realmQueryMapper.map(realm.where(clazz)).findAll())
                        .map(objectsMapper::map)
                        .toList()
                        .blockingGet());
    }

    public <T extends RealmModel> Completable insert(Collection<T> objects) {
        return execute(realmConfiguration, realm -> realm.insert(objects))
                .subscribeOn(realmScheduler());
    }

    public <E, T extends RealmModel> Completable insert(Collection<E> objects, @NonNull Mapper<E, T> mapper) {
        return Observable.fromIterable(objects)
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::insert)
                .subscribeOn(realmScheduler());
    }

    public Completable execute(@NonNull Consumer<Realm> executeFunction) {
        return execute(realmConfiguration, executeFunction::accept)
                .subscribeOn(realmScheduler());
    }

    public <T extends RealmModel> Completable insertOrUpdate(
            @NonNull Class<T> clazz,
            @NonNull Function<RealmQuery<T>, RealmQuery<T>> query) {
        return execute(realmConfiguration, realm -> {
            final RealmResults<T> results = query.apply(realm.where(clazz)).findAll();
            realm.insertOrUpdate(results);
        });
    }

    public <T extends RealmModel> Completable insertOrUpdate(T object) {
        return execute(realmConfiguration, realm -> realm.insertOrUpdate(Collections.singleton(object)))
                .subscribeOn(realmScheduler());
    }

    public <T extends RealmModel, E> Completable insertOrUpdate(E object, @NonNull Mapper<E, T> mapper) {
        return insertOrUpdate(Collections.singleton(mapper.map(object)))
                .subscribeOn(realmScheduler());
    }

    public <T extends RealmModel> Completable insertOrUpdate(@NonNull Collection<T> objects) {
        return execute(realmConfiguration, realm -> realm.insertOrUpdate(objects))
                .subscribeOn(realmScheduler());
    }

    public <From, To extends RealmModel> Completable insertOrUpdate(@NonNull Collection<From> objects,
                                                                    @NonNull Mapper<From, To> mapper) {
        return Observable.fromIterable(objects)
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::insertOrUpdate);
    }

    private static <T> Single<T> get(
            @NonNull RealmConfiguration realmConfiguration,
            @NonNull io.reactivex.functions.Function<Realm, T> reader) {
        return Single.<T>create(emitter -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                final T result = reader.apply(realm);
                emitter.onSuccess(result);
            } catch (Exception error) {
                emitter.onError(error);
            }
        }).subscribeOn(realmScheduler());
    }

    private static Completable execute(@NonNull RealmConfiguration realmConfiguration,
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

    private static <T> Single<T> executeAndGet(
            @NonNull RealmConfiguration realmConfiguration,
            @NonNull Function<Realm, T> query) {
        return Single.<T>create(emitter -> {
            try (Realm realm = Realm.getInstance(realmConfiguration)) {
                realm.executeTransaction(realmInstance -> {
                    try {
                        emitter.onSuccess(query.apply(realmInstance));
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
