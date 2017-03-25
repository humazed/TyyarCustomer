package com.tyyar.tyyarfooddelivery.screens.intro.login_and_signup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tyyar.tyyarfooddelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SignupFragment extends Fragment {
    private static final String TAG = SignupFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";


    @BindView(R.id.facebook_login_button) LinearLayout mFacebookLoginButton;
    @BindView(R.id.continue_with_email_text) TextView mContinueWithEmailText;
    @BindView(R.id.first_name_editText) EditText mFirstNameEditText;
    @BindView(R.id.last_name_editText) EditText mLastNameEditText;
    @BindView(R.id.email_editText) EditText mEmailEditText;
    @BindView(R.id.phone_number_editText) EditText mPhoneNumberEditText;
    @BindView(R.id.password_editText) EditText mPasswordEditText;
    @BindView(R.id.register_button) Button mRegisterButton;

    Unbinder unbinder;

    private String mParam1;

    private OnFragmentInteractionListener mListener;

    public SignupFragment() { /*Required empty public constructor*/ }

    public static SignupFragment newInstance(String param1) {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        unbinder = ButterKnife.bind(this, view);

        mFacebookLoginButton.setOnClickListener(v -> {

        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
