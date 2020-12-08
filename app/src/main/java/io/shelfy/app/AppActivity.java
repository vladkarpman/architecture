package io.shelfy.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.shelfy.presentation.BaseActivity;

public class AppActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getScreensNavigator().showMovies();
        }
    }

    @Override
    protected int getFragmentContainer() {
        return R.id.fragment_container;
    }
}
