package io.shelfy.presentation.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.FragmentComponent;

public abstract class CommonFragment extends Fragment {

    protected FragmentComponent fragmentComponent;

    public CommonFragment() {
    }

    public CommonFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        final CommonActivity commonActivity = (CommonActivity) requireActivity();
        fragmentComponent = createFragmentComponent(commonActivity.activityComponent);
    }

    @NonNull
    protected abstract FragmentComponent createFragmentComponent(@NonNull ActivityComponent activityComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        fragmentComponent.destroy();
        fragmentComponent = null;
    }

    @Nullable
    public final <T extends View> T findViewById(@IdRes int id) {
        return Objects.requireNonNull(getView()).findViewById(id);
    }
}
