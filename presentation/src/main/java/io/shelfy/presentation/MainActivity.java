package io.shelfy.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;

import io.shelfy.presentation.common.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
