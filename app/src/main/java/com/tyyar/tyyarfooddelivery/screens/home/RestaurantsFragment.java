package com.tyyar.tyyarfooddelivery.screens.home;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.appspot.tayyar_trial.restaurantAPI.RestaurantAPI;
import com.appspot.tayyar_trial.restaurantAPI.model.RestaurantView;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.MerchantView;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestaurantsFragment extends Fragment {
    private static final String TAG = RestaurantsFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    public static final String ROOT_URL = "https://tayyar-trial.appspot.com/_ah/api/";

    @BindView(R.id.merchants_recycler) RecyclerView mMerchantsRecycler;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    private MerchantsAdapter mAdapter;

    private String mParam1;


    public RestaurantsFragment() { /* Required empty public constructor */ }

    public static RestaurantsFragment newInstance(String param1) {
        RestaurantsFragment fragment = new RestaurantsFragment();
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
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        unbinder = ButterKnife.bind(this, view);

        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter = new MerchantsAdapter(DataServer.getMerchantsView());
//        mMerchantsRecycler.setAdapter(mAdapter);

        Observable.fromCallable(this::getAllRestaurants)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(5)
                .flatMap(Observable::fromIterable)
                .map(rv -> MerchantView.create("rv.getRestaurantID()", rv.getName(), 3, rv.getImageURL(), "", 3, "2.22", "25"))
                .toList()
                .subscribe(merchantViewList -> {
                            Log.d(TAG, "merchantView = " + merchantViewList);
                            mProgressBar.setVisibility(View.GONE);
                            mAdapter = new MerchantsAdapter(merchantViewList);
                            mMerchantsRecycler.setAdapter(mAdapter);
                        }
                        , throwable -> Log.e(TAG, "onCreate: ", throwable));

        return view;
    }

    private List<RestaurantView> getAllRestaurants() throws IOException {
        RestaurantAPI restaurantAPI = new RestaurantAPI.Builder(new NetHttpTransport(), new AndroidJsonFactory(), null)
                .setGoogleClientRequestInitializer(clientRequest -> clientRequest.setDisableGZipContent(true))
                .setRootUrl(ROOT_URL)
                .build();

        return restaurantAPI.getAllRestaurantsOrderedByPricing(0, 10)
                .execute()
                .getItems();
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
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
