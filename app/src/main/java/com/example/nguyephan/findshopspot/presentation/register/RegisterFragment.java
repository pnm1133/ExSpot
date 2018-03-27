package com.example.nguyephan.findshopspot.presentation.register;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.nguyephan.findshopspot.R;
import com.example.nguyephan.findshopspot.data.pojo.User;
import com.example.nguyephan.findshopspot.presentation.base.BaseFragment;
import com.example.nguyephan.findshopspot.presentation.start.StartActivity;
import com.example.nguyephan.findshopspot.ultis.common.CommonFr;
import com.example.nguyephan.findshopspot.ultis.common.CommonUtils;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterFragment extends BaseFragment implements
        RegisterFragmentContact.View,
        View.OnClickListener,
        TextView.OnEditorActionListener {

    private static final String TAG = "RegisterFragment";
    private StartActivity mActivity;

    private OnFragmentInteractionListener mListener;
    private FirebaseUser mCurrentUser;
    private Uri avatarUri;

    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.edtu_username)
    TextInputEditText edtUserName;
    @BindView(R.id.edt_email)
    TextInputEditText edtEmail;
    @BindView(R.id.edt_password)
    TextInputEditText edtPassword;
    @BindView(R.id.edt_confirm)
    TextInputEditText edtConfirm;
    @BindView(R.id.cb_condition)
    CheckBox cbCondition;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.imgBtn_back)
    ImageButton btnBack;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    @Inject
    RegisterFragmentContact.Presenter<RegisterFragmentContact.View> presenter;

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_content, container, false);

        if (container.getContext() instanceof StartActivity) {
            ButterKnife.bind(this, view);
            presenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnRegister.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        edtConfirm.setOnEditorActionListener(this);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        if (context instanceof StartActivity) {
            mActivity = (StartActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                // kiem tra dk cac truong edt
                if (checkConditionEdit(edtUserName, edtEmail, edtPassword, edtConfirm)) {
                    if (isInternetConnection()) {
                        loadIndicatorConnection();
                        presenter.startRegister();
                    } else {
                        errorInternetConnection();
                    }
                }
                break;
            case R.id.imgBtn_back:
                FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
                transaction.remove(this);
                transaction.show(mActivity.getSupportFragmentManager().findFragmentByTag(CommonFr.LOGIN_PAGE_TAG));
                transaction.commit();
                break;
        }
    }

    private boolean checkConditionEdit(TextInputEditText... edts) {
        for (TextInputEditText editText : edts) {
            if (TextUtils.isEmpty(editText.getText())) {
                showError(editText.getHint() + " is empty", null);
                return false;
            }
        }

        if (!TextUtils.equals(edtPassword.getText(), edtConfirm.getText())) {
            showError("password confirm incorrect please try again", null);
            return false;
        }
        return true;
    }

    @Override
    public void replaceLogin() {
        FragmentTransaction fragmentTransaction = mActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.remove(this);
        fragmentTransaction.show(mActivity.getSupportFragmentManager().findFragmentByTag(CommonFr.LOGIN_PAGE_TAG));
        fragmentTransaction.commit();
    }

    @Override
    public void showError(String error, Task<AuthResult> task) {
        tvError.setText(error);
        tvError.setVisibility(View.VISIBLE);
    }

    @Override
    public void createNewAccount() {
        loadIndicatorConnection();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(mActivity, task -> {
                    if (task.isSuccessful()) {
                        Log.e(TAG, "is complete = true : " + Thread.currentThread());
                        mCurrentUser = mAuth.getCurrentUser();
                        updateUser();
                    } else {
                        mCurrentUser = null;
                        try {
                            if (TextUtils.equals(task.getException().getMessage(), CommonUtils.ERROR_EMAIL_FIREBASE)) {
                                showError("email khong dung dinh dang", null);
                            } else if (TextUtils.equals(task.getException().getMessage(), CommonUtils.ERROR_PASSWORD_FIREBASE)) {
                                showError("Password phai hon 6 ky tu", null);
                            }
                        } catch (NullPointerException e) {
                            Log.e(TAG, e.getMessage());
                        }
                        hideLoadIndicatorConnection();
                    }
                });
    }

    @Override
    public void updateUser() {
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setDisplayName(edtUserName.getText().toString())
                .setPhotoUri(avatarUri)
                .build();
        User user = new User();
        user.setId(mCurrentUser.getUid());
        user.setName(mCurrentUser.getDisplayName());

        mCurrentUser.updateProfile(profileChangeRequest)
                .addOnCompleteListener(task -> {
                    if (task.isComplete()) {
                        presenter.saveUserToCache(user);
                        hideLoadIndicatorConnection();
                        replaceLogin();
                        Log.e(TAG, "update profile user success");
                    } else {
                        Log.e(TAG, "update profile user Fail" + task.getException().getMessage());
                    }
                });
    }

    @Override
    public void hideKeyBoard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(),0);
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        hideKeyBoard(v);
        scrollView.smoothScrollBy(0,300);
        return true;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
