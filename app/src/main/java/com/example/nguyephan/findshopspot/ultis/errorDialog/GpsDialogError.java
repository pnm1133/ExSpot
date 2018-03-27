package com.example.nguyephan.findshopspot.ultis.errorDialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyephan.findshopspot.R;

/**
 * Created by nguye phan on 3/24/2018.
 */

public class GpsDialogError extends DialogFragment {

    private static final String TITLE_DALOG = "gpsDialog";
    public static GpsDialogError newInstance(){
        GpsDialogError gpsDialogError = new GpsDialogError();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE_DALOG,"");
        gpsDialogError.setArguments(bundle);
        return gpsDialogError;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_gps_error,container,false);
    }
}
