package io.shelfy.realmdb.common;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;
import io.shelfy.utils.Mapper;

import static io.shelfy.realmdb.common.RealmUtils.executeAsync;
import static io.shelfy.realmdb.common.RealmUtils.fetchAsync;

public class RealmDao {

    @NonNull
    protected final RealmConfiguration realmConfiguration;

    public RealmDao(@NonNull RealmConfiguration realmConfiguration) {
        this.realmConfiguration = realmConfiguration;
    }

    public <T extends RealmModel, E> Single<List<E>> read(@NonNull Class<T> clazz, @NonNull Mapper<T, E> mapper) {
        return read(clazz)
                .subscribeOn(Schedulers.computation())
                .flattenAsObservable(Functions.identity())
                .map(mapper::map)
                .toList();
    }

    protected <T extends RealmModel> Single<RealmResults<T>> read(@NonNull Class<T> clazz) {
        return fetchAsync(realmConfiguration, realm -> realm.where(clazz).findAll());
    }

    public <T extends RealmModel> Completable create(Collection<T> objects) {
        return executeAsync(realmConfiguration, realm -> realm.insert(objects));
    }

    public <E, T extends RealmModel> Completable create(Collection<E> objects, @NonNull Mapper<E, T> mapper) {
        return Observable.fromIterable(objects)
                .subscribeOn(Schedulers.computation())
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::create);
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
                .subscribeOn(Schedulers.computation())
                .map(mapper::map)
                .toList()
                .flatMapCompletable(this::update);
    }
}
