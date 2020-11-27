package io.shelfy.presentation.common;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import io.shelfy.presentation.common.module.ActivityModule;
import io.shelfy.presentation.common.module.FragmentModule;

public abstract class CommonFragment extends Fragment {

    protected FragmentModule fragmentComponent;

    public CommonFragment() {
    }

    public CommonFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CommonActivity commonActivity = (CommonActivity) requireActivity();
        fragmentComponent = createFragmentModule(commonActivity.activityComponent);
    }

    @NonNull
    protected abstract FragmentModule createFragmentModule(@NonNull ActivityModule activityComponent);

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return Objects.requireNonNull(getView()).findViewById(id);
    }
}
