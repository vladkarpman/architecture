package io.shelfy.presentation.common;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.FragmentComponent;

public abstract class BaseFragment extends Fragment {

    protected FragmentComponent fragmentComponent;

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return Objects.requireNonNull(getView()).findViewById(id);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BaseActivity baseActivity = (BaseActivity) requireActivity();
        final ActivityComponent activityComponent = baseActivity.activityComponent;
        fragmentComponent = new FragmentComponent(activityComponent, this);
    }
}
