package io.shelfy.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.domain.DomainComponent;
import io.shelfy.presentation.common.BaseActivity;

public class ActivityComponent extends PresentationComponent {

    @NonNull
    private final BaseActivity baseActivity;

    public ActivityComponent(@NonNull DomainComponent domainComponent, @NonNull BaseActivity baseActivity) {
        super(domainComponent, baseActivity);
        this.baseActivity = baseActivity;
    }
}
