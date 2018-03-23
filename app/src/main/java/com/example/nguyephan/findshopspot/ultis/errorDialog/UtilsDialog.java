package com.example.nguyephan.findshopspot.ultis.errorDialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyephan.findshopspot.R;

/**
 * Created by nguye phan on 3/21/2018.
 */

public class UtilsDialog {

    public static class DialogGpsError extends DialogFragment{
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return super.onCreateDialog(savedInstanceState);
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.dialog_gps_error,container,false);
        }
    }
}
