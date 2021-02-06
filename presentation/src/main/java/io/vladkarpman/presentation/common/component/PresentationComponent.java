package io.vladkarpman.presentation.common.component;

import androidx.annotation.NonNull;

import io.vladkarpman.presentation.common.module.PresentationModule;

interface PresentationComponent<Module extends PresentationModule, ParentComponent> {

    @NonNull
    ParentComponent getParentComponent();

    @NonNull
    Module getPresentationModule();

    void destroy();
}
