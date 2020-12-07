package io.shelfy.app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.shelfy.presentation.BaseActivity;
import io.shelfy.presentation.screensnavigator.ScreensNavigator;

public class AppActivity extends BaseActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            screensNavigator.showMovies();
        }
    }

    @NonNull
    @Override
    public ScreensNavigator createScreenNavigator() {
        return new ScreensNavigatorImpl(this, getSupportFragmentManager());
    }
}
