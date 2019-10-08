package com.lmstest.ui.user_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lmstest.BR;
import com.lmstest.R;
import com.lmstest.data.view_model.UserModel;
import com.lmstest.databinding.ActivityMainBinding;
import com.lmstest.ui.adapter.UserAdapter;
import com.lmstest.ui.base.BaseActivity;
import com.lmstest.ui.login.LoginActivity;
import com.lmstest.utils.UIUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.lmstest.utils.Constant.LIMIT_COUNT_PAGINATION;

public class MainActivity extends BaseActivity<ActivityMainBinding, UserListVM> implements UserListNavigator {
	private UserAdapter userAdapter;
	private LinearLayoutManager linearLayoutManager;
	private boolean isWaitForPagination = false;
	private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
		@Override
		public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
			super.onScrollStateChanged(recyclerView, newState);
		}

		@Override
		public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
			super.onScrolled(recyclerView, dx, dy);
			checkPagination();
		}
	};

	public static void startActivity(Activity activity) {
		Intent intent = new Intent(activity, MainActivity.class);
		activity.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewModel.setNavigator(this);
		initUI();
	}

	@Override
	public int getBindingVariable() {
		return BR.viewModel;
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public UserListVM getViewModel() {
		return new UserListVM();
	}

	private void initUI() {
		initRecycleView();
		initObserveViewModel();
		mViewModel.loadListUser();
	}

	private void initObserveViewModel() {
		mViewModel.getUserList().observe(this, this::showUserList);
		mViewModel.getLoading().observe(this, this::showLoading);
	}

	private void showLoading(boolean isShow) {
		if (isShow){
			showLoading();
		}else {
			hideLoading();
		}
	}

	private void initRecycleView() {
		userAdapter = new UserAdapter();
		linearLayoutManager = new LinearLayoutManager(this);
		mBinding.rvUserList.setLayoutManager(linearLayoutManager);
		mBinding.rvUserList.setAdapter(userAdapter);
		mBinding.rvUserList.setHasFixedSize(true);
		mBinding.rvUserList.addOnScrollListener(onScrollListener);
	}

	private void showUserList(List<UserModel> list) {
		if (list != null && !list.isEmpty() && userAdapter != null) {
			if (isWaitForPagination){
				userAdapter.removeLoading();
			}
			userAdapter.addToList(list);
		}
		isWaitForPagination = false;
	}

	private void checkPagination() {
		if (linearLayoutManager != null && userAdapter != null) {
			int visibleItemCount = linearLayoutManager.getChildCount();
			int totalItemCount = linearLayoutManager.getItemCount();
			int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
			if (mViewModel != null
					&& !isWaitForPagination
					&& (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
					&& firstVisibleItemPosition >= 0 && totalItemCount >= LIMIT_COUNT_PAGINATION
					&& !mViewModel.isPaginationFinish()) {
				isWaitForPagination = true;
				if (userAdapter != null){
					userAdapter.addLoading();
				}
				mViewModel.loadListUser();
			}
		}
	}

	@Override
	public void showErrorResponseMessage(String errorResponseMessage) {
		UIUtils.showErrorAlertDialog(getString(R.string.error), errorResponseMessage, this);
	}

	@Override
	public void logOut() {
		LoginActivity.startActivity(this);
		finish();
	}
}
