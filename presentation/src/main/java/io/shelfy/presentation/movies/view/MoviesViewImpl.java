package io.shelfy.presentation.movies.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.shelfy.presentation.R;
import io.shelfy.presentation.common.view.BaseView;

public class MoviesViewImpl extends BaseView implements MoviesView {

    public MoviesViewImpl(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup parent) {
        super(layoutInflater, parent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movies_list;
    }
}
