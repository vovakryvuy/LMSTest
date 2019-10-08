package com.lmstest.ui.base;

import android.widget.CheckBox;

public abstract class BindingAdapter {
	@androidx.databinding.BindingAdapter({"checkBackground"})
	public static void setBackgroundCheckBox(CheckBox checkBox, boolean isCheck) {

	}
}
