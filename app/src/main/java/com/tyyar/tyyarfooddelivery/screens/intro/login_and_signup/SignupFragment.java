package com.tyyar.tyyarfooddelivery.screens.intro.login_and_signup;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.appspot.tayyar_trial.restaurantAPI.RestaurantAPI;
import com.appspot.tayyar_trial.restaurantAPI.model.Customer;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.screens.home.HomeActivity;
import com.tyyar.tyyarfooddelivery.utils.okhttp_transport.OkHttpTransport;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.tyyar.tyyarfooddelivery.utils.Constants.ROOT_URL;

public class SignupFragment extends Fragment {
    private static final String TAG = SignupFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    public static final String KEY_FROM_SIGNUP = "from_signup";


    Unbinder unbinder;
    @BindView(R.id.facebook_login_button) LinearLayout mFacebookLoginButton;
    @BindView(R.id.continue_with_email_text) TextView mContinueWithEmailText;
    @BindView(R.id.first_name_editText) EditText mFirstNameEditText;
    @BindView(R.id.last_name_editText) EditText mLastNameEditText;
    @BindView(R.id.email_editText) EditText mEmailEditText;
    @BindView(R.id.phone_number_editText) EditText mPhoneNumberEditText;
    @BindView(R.id.password_editText) EditText mPasswordEditText;
    @BindView(R.id.register_button) Button mRegisterButton;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

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

        mRegisterButton.setOnClickListener(v -> {
            mProgressBar.setVisibility(View.VISIBLE);
            Single.fromCallable(this::createCustomer)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(customer -> {
                                Log.d(TAG, "customer = " + customer);
                                Intent intent = new Intent(getActivity(), HomeActivity.class);
                                intent.putExtra(KEY_FROM_SIGNUP, true);
                                startActivity(intent);
                                mProgressBar.setVisibility(View.INVISIBLE);
                            }
                            , throwable -> {
                                Log.e(TAG, "onCreate: ", throwable);
                                mProgressBar.setVisibility(View.INVISIBLE);
                            });

        });

        return view;
    }

    private Customer createCustomer() throws IOException {
        RestaurantAPI restaurantAPI = new RestaurantAPI.Builder(new OkHttpTransport(), new AndroidJsonFactory(), null)
                .setGoogleClientRequestInitializer(clientRequest -> clientRequest.setDisableGZipContent(true))
                .setRootUrl(ROOT_URL)
//                .setRootUrl(LOCAl_HOST_URL)
                .build();


        return restaurantAPI.createCustomer(
                mFirstNameEditText.getText().toString(),
                mPasswordEditText.getText().toString(),
                mEmailEditText.getText().toString(),
                "add",
                mPhoneNumberEditText.getText().toString()
        ).execute();
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
