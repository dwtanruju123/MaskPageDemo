package com.sky.maskpagedemo.util;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.sky.maskpagedemo.R;


public class CustomDialog {

    private Dialog dialog;
    private boolean isOutside = true;
    private final WindowManager.LayoutParams lp;
    private final Window dialogWindow;

    public CustomDialog(Context context, View view, int gravity) {
        if (gravity == Gravity.BOTTOM)
            dialog = new Dialog(context, R.style.DialogStyleBottom);
        else if (gravity == Gravity.CENTER)
            dialog = new Dialog(context, R.style.DialogStyleCenter);
        dialog.setContentView(view);
        dialogWindow = dialog.getWindow();
        lp = dialogWindow.getAttributes();
        lp.gravity = gravity;
    }

    public CustomDialog setMax(boolean isMaxWidth, boolean isMaxHeight) {
        if (isMaxWidth)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        else
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        if (isMaxHeight)
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        else
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return this;
    }

    public CustomDialog setCanceledOnTouchOutside(boolean b) {
        isOutside = b;
        return this;
    }

    public CustomDialog setOnDismissListener(DialogInterface.OnDismissListener listener) {
        dialog.setOnDismissListener(listener);
        return this;
    }

    public CustomDialog setThemeStyle(int resid) {
        dialog.getContext().setTheme(resid);
        return this;
    }

    public CustomDialog build() {
        //设置点击外部是否关闭对话框
        dialog.setCanceledOnTouchOutside(isOutside);
        dialogWindow.setAttributes(lp);
        return this;
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }

    public void show() {
        dialog.show();
    }

    private static ProgressDialog progressDialog;

    public static void loadingShow(Context context) {
        loadingShow(context, "正在加载中……", false);
    }

    public static void loadingShow(Context context, String message, boolean cancelable) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void loadingMessage(String message) {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.setMessage(message);
    }

    public static void loadingDismiss() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
