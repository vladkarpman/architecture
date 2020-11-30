package io.shelfy.realmdb.common;


import android.os.HandlerThread;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
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
        return fetchAsync(realmConfiguration, realm ->
                Observable.fromIterable(realm.where(clazz).findAll())
                        .map(mapper::map)
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
}
