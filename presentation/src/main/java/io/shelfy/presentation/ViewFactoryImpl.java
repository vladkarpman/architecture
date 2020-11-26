package io.shelfy.presentation;

import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.view.factory.BaseViewFactory;

public class ViewFactoryImpl extends BaseViewFactory {

    @Override
    protected <T extends CommonView> T createInternal(Class<T> viewClass) {
        return null;
    }
}
