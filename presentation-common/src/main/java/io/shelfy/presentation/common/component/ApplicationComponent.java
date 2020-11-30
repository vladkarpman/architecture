package io.shelfy.presentation.common.component;

import io.reactivex.annotations.NonNull;
import io.shelfy.domain.DomainModule;

public interface ApplicationComponent {

    @NonNull
    DomainModule getDomainModule();
}
