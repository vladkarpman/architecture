package io.shelfy.architecture.presentation.common.component;

import androidx.annotation.NonNull;

import io.shelfy.architecture.common.component.AppComponent;
import io.shelfy.architecture.presentation.common.BaseActivity;

public class ActivityComponent extends PresentationComponent {

    @NonNull
    private final BaseActivity baseActivity;

    public ActivityComponent(@NonNull AppComponent appComponent, @NonNull BaseActivity baseActivity) {
        super(appComponent, baseActivity);
        this.baseActivity = baseActivity;
    }
}
