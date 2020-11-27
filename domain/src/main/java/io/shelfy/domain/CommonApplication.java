package io.shelfy.domain;

import io.reactivex.annotations.NonNull;

public interface CommonApplication {

    @NonNull
    DomainModule getDomainModule();
}
