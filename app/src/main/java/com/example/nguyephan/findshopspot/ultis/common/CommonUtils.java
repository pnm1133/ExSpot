package com.example.nguyephan.findshopspot.ultis.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.nguyephan.findshopspot.R;

/**
 * Created by nguye phan on 3/22/2018.
 */

public class CommonUtils {

    public static final String PRE_NAME = "findshop_pre";

    public static final String STATUS_RESPONSE_OK = "OK";

    public static final String STATUS_RESPONSE_FAULT = "FAULT";

    public static final String ERROR_PASSWORD_FIREBASE = "The given password is invalid. [ Password should be at least 6 characters ]";

    public static final String ERROR_EMAIL_FIREBASE = "The email address is badly formatted.";

    public static ProgressDialog showLoadingDialog(Context context) {
        Log.e("dialog","show" );
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

}
