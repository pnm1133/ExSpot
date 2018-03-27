package com.example.nguyephan.findshopspot.presentation.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.di.component.ActivityComponent;
import com.example.nguyephan.findshopspot.presentation.base.BaseFragment;
import com.example.nguyephan.findshopspot.presentation.register.RegisterFragment;
import com.example.nguyephan.findshopspot.presentation.start.StartActivity;
import com.example.nguyephan.findshopspot.presentation.start.StartFragment;
import com.example.nguyephan.findshopspot.ultis.common.CommonFr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginFragment extends BaseFragment implements LoginContact.View,
        View.OnClickListener {

    private static final String TAG = "LoginFragment";

    private OnFragmentInteractionListener mListener;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private StartActivity mStartActivity;

    @Inject
    LoginContact.Presenter<LoginContact.View> presenter;

    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_new_account)
    TextView tvNewAccount;
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.container)
    NestedScrollView container;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        if(context instanceof StartActivity){
            mStartActivity = (StartActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get auth of current user
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ActivityComponent component = getActivityComponent();
        if(component != null){
            component.inject(this);
            initPresenter();
        }
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(presenter.isAttachView()){
            presenter.userCacheLogin();
        }else {
            loadIndicatorConnection();
        }
        ivClose.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvNewAccount.setOnClickListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = mStartActivity.getSupportFragmentManager().beginTransaction();
        switch (v.getId()){
            case R.id.ivClose:
                break;
            case R.id.btn_login:
                break;
            case R.id.tv_new_account:
                transaction.add(R.id.container,RegisterFragment.newInstance(), CommonFr.REGISTER_PAGE_TAG);
                transaction.hide(this);
                transaction.commit();
                break;
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void showEmailAndPassWordCache() {

    }

    @Override
    public void showNothingUser() {
        Toast.makeText(getContext(),"You haven't Logging",Toast.LENGTH_SHORT).show();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void initPresenter(){
        presenter.onAttach(this);
    }
}
