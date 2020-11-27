package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainModule;

interface ActivityModule {
    @NonNull
    DomainModule getDomainModule();
}
