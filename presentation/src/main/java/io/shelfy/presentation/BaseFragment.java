package io.shelfy.presentation;

import android.content.Context;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import io.shelfy.presentation.common.CommonActivity;
import io.shelfy.presentation.common.CommonFragment;
import io.shelfy.presentation.common.component.ActivityComponent;
import io.shelfy.presentation.common.component.FragmentComponent;
import io.shelfy.presentation.common.component.BaseFragmentComponent;
import io.shelfy.presentation.common.module.BasePresentationModule;

public abstract class BaseFragment extends CommonFragment {

    protected ScreenNavigator screenNavigator;

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
        screenNavigator = baseActivity.getScreenNavigator();
    }

    @NonNull
    @Override
    protected FragmentComponent createFragmentComponent(@NonNull ActivityComponent activityComponent) {
        return new BaseFragmentComponent(
                activityComponent,
                new BasePresentationModule(
                        this,
                        this,
                        new ViewModelFactory(activityComponent.getApplicationComponent().getDomainModule()),
                        new ViewFactoryImpl(getLayoutInflater())));
    }
}
