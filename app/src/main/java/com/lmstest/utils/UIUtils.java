package com.lmstest.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.lmstest.R;

public class UIUtils {
	public static void showErrorAlertDialogByRes(int resIdTitle, int resIdMessage, Context context) {
		showErrorAlertDialog(context.getResources().getString(resIdTitle),
				context.getResources().getString(resIdMessage), context);
	}

	public static void showErrorAlertDialog(String title, String message, Context context) {
		AlertDialog dialog = new AlertDialog.Builder(context)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton(context.getResources().getString(R.string.ok), null).show();

		dialog.getButton(AlertDialog.BUTTON_POSITIVE)
				.setTextColor(context.getResources().getColor(R.color.orange_color));
	}

	public static ProgressDialog showProgressDialog(Context context) {
		ProgressDialog dialog = new ProgressDialog(context);
		dialog.show();
		if (dialog.getWindow() != null)
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

		dialog.setContentView(R.layout.dialog_loading);
		dialog.setIndeterminate(true);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		return dialog;
	}
}
