package io.vladkarpman.presentation.common.component;

import io.reactivex.annotations.NonNull;
import io.vladkarpman.domain.DomainModule;

public interface ApplicationComponent {

    @NonNull
    DomainModule getDomainModule();
}
