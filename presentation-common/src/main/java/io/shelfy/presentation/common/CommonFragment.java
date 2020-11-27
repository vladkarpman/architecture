package io.shelfy.presentation.common;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import io.shelfy.presentation.common.component.ActivityModuleImpl;
import io.shelfy.presentation.common.component.PresentationModule;

public abstract class CommonFragment extends Fragment {

    protected PresentationModule fragmentComponent;

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
    protected abstract PresentationModule createFragmentModule(@NonNull ActivityModuleImpl activityComponent);

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return Objects.requireNonNull(getView()).findViewById(id);
    }
}
