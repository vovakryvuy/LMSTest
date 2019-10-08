package com.lmstest.ui.base;

import java.lang.ref.WeakReference;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel<N> extends ViewModel {
	public MutableLiveData<Boolean> loading = new MutableLiveData<>();
	private WeakReference<N> mNavigator;

	public BaseViewModel() {

	}

	@Override
	protected void onCleared() {
		super.onCleared();
	}

	public MutableLiveData<Boolean> getLoading() {
		return loading;
	}

	public N getNavigator() {
		return mNavigator.get();
	}

	public void setNavigator(N navigator) {
		this.mNavigator = new WeakReference<>(navigator);
	}
}
