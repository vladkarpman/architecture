package io.shelfy.presentation;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.shelfy.presentation.common.view.CommonView;
import io.shelfy.presentation.common.view.factory.BaseViewFactory;
import io.shelfy.presentation.movies.view.MoviesView;
import io.shelfy.presentation.movies.view.MoviesViewImpl;

public class ViewFactoryImpl extends BaseViewFactory {

    @NonNull
    private final LayoutInflater layoutInflater;

    public ViewFactoryImpl(@NonNull LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @Override
    protected <T extends CommonView> T createInternal(@NonNull Class<T> viewClass, @Nullable ViewGroup container) {
        if (viewClass.isAssignableFrom(MoviesView.class)) {
            return (T) new MoviesViewImpl(layoutInflater, container);
        }
        return null;
    }
}
