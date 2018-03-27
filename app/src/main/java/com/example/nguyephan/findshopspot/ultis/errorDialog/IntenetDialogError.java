package com.example.nguyephan.findshopspot.ultis.errorDialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.nguyephan.findshopspot.R;

/**
 * Created by nguye phan on 3/24/2018.
 */

public class IntenetDialogError extends android.support.v4.app.DialogFragment {

    public static IntenetDialogError newInstance(String title){
        IntenetDialogError intenetDialogError = new IntenetDialogError();
        Bundle bundle = new Bundle();
        bundle.putString("title",title);
        intenetDialogError.setArguments(bundle);
        return intenetDialogError;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_netword_error,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
