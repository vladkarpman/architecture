package io.shelfy.presentation;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.FragmentComponent;
import io.shelfy.presentation.common.component.BaseFragmentComponent;
import io.shelfy.presentation.common.module.BaseFragmentModule;
import io.shelfy.presentation.common.screensnavigator.ScreensNavigator;

public abstract class BaseFragment extends CommonFragment {

    protected ScreensNavigator screensNavigator;

    public BaseFragment() {
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        final BaseActivity baseActivity = (BaseActivity) requireActivity();
        screensNavigator = baseActivity.getScreensNavigator();
    }

    @NonNull
    @Override
    protected FragmentComponent createFragmentComponent(@NonNull ActivityComponent activityComponent) {
        return new BaseFragmentComponent(
                activityComponent,
                new BaseFragmentModule(
                        this,
                        new ViewModelFactoryImpl(activityComponent.getParentComponent().getDomainModule()),
                        new ViewFactoryImpl(getLayoutInflater())));
    }
}
