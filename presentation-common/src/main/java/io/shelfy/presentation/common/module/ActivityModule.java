package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;

interface ActivityModule {
    @NonNull
    DomainModule getDomainModule();
}
