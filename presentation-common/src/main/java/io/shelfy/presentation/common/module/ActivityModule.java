package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;

public interface ActivityModule {
    @NonNull
    DomainModule getDomainModule();
}
