package com.lmstest.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lmstest.BR;
import com.lmstest.R;
import com.lmstest.databinding.ActivityLoginBinding;
import com.lmstest.ui.base.BaseActivity;
import com.lmstest.ui.user_list.UserListActivity;
import com.lmstest.utils.UIUtils;

import androidx.annotation.Nullable;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginVM> implements LoginNavigator {

	public static void startActivity(Activity activity) {
		Intent intent = new Intent(activity, LoginActivity.class);
		activity.startActivity(intent);
	}

	@Override
	public int getBindingVariable() {
		return BR.viewModel;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_login;
	}

	@Override
	public LoginVM getViewModel() {
		return new LoginVM();
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewModel.checkIsLoginUser();
		mViewModel.setNavigator(this);
		initUI();
	}

	private void initUI() {
		initFocusFields();
		initObserveViewModel();
	}

	private void initFocusFields() {
		mBinding.edUserName.setOnFocusChangeListener((view, focus) ->
				mViewModel.isSelectNameField.set(focus));
		mBinding.edPassword.setOnFocusChangeListener((view, focus) ->
				mViewModel.isSelectPasswordField.set(focus));
		mBinding.cbRemember.setOnCheckedChangeListener((compoundButton, b) ->
				mViewModel.changeCheckRememberMe(b));
	}

	private void initObserveViewModel() {
		mViewModel.getLoading().observe(this, this::showLoading);
	}

	private void showLoading(boolean isShow) {
		if (isShow){
			showLoading();
		}else {
			hideLoading();
		}
	}


	@Override
	public void clickLogin() {
		mViewModel.checkEnterData(mBinding.edUserName.getText().toString(),
				mBinding.edPassword.getText().toString());
	}

	@Override
	public void showWarningMessage(int errorMessageRes) {
		UIUtils.showErrorAlertDialogByRes(R.string.warning, errorMessageRes, this);
	}

	@Override
	public void showErrorResponseMessage(String errorResponseMessage) {
		UIUtils.showErrorAlertDialog(getString(R.string.error), errorResponseMessage, this);
	}

	@Override
	public void successLogin() {
		UserListActivity.startActivity(this);
		finish();
	}
}
