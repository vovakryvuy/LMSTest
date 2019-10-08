package com.lmstest.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.lmstest.utils.UIUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
		extends AppCompatActivity {

	protected T mBinding;
	protected V mViewModel;
	private ProgressDialog mProgressDialog;

	public static void startActivity(Activity from, Class to) {
		Intent intent = new Intent(from, to);
		from.startActivity(intent);
		from.finish();
	}

	public abstract int getBindingVariable();

	public abstract
	@LayoutRes
	int getLayoutId();

	public abstract V getViewModel();

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		performDataBinding();
	}

	private void performDataBinding() {
		mBinding = DataBindingUtil.setContentView(this, getLayoutId());
		this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
		mBinding.setVariable(getBindingVariable(), mViewModel);
		mBinding.executePendingBindings();
	}

	public void showLoading() {
		hideLoading();
		mProgressDialog = UIUtils.showProgressDialog(this);
	}

	public void hideLoading() {
		if (mProgressDialog != null && mProgressDialog.isShowing())
			mProgressDialog.cancel();
	}

}
