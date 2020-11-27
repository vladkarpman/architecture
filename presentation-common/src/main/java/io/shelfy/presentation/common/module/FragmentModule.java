package io.shelfy.presentation.common.module;

import androidx.annotation.NonNull;

public interface FragmentModule extends PresentationModule {
    @NonNull
    ActivityModule getActivityComponent();
}
