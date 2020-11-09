package io.shelfy.architecture.presentation.common;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import io.shelfy.architecture.presentation.common.component.ActivityComponent;
import io.shelfy.architecture.presentation.common.component.FragmentComponent;

public abstract class BaseFragment extends Fragment {

    protected FragmentComponent fragmentComponent;

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BaseActivity baseActivity = (BaseActivity) requireActivity();
        final ActivityComponent activityComponent = baseActivity.activityComponent;
        fragmentComponent = new FragmentComponent(activityComponent, this);
    }
}
