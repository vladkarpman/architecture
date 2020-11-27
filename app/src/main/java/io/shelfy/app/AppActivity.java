package io.shelfy.app;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.shelfy.presentation.BaseActivity;
import io.shelfy.presentation.movies.MoviesFragment;

public class AppActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new MoviesFragment())
                    .commit();
        }
    }
}
