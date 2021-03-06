package com.tyyar.tyyarfooddelivery.screens.home;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PharmaciesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PharmaciesFragment extends Fragment {
    private static final String TAG = PharmaciesFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.merchants_recycler) RecyclerView mMerchantsRecycler;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    private MerchantsAdapter mAdapter;

    private String mParam1;


    public PharmaciesFragment() { /* Required empty public constructor */ }

    public static PharmaciesFragment newInstance(String param1) {
        PharmaciesFragment fragment = new PharmaciesFragment();
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
        View view = inflater.inflate(R.layout.fragment_pharmacies, container, false);
        unbinder = ButterKnife.bind(this, view);

        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
//        mAdapter = new MerchantsAdapter(DataServer.getMerchants());
//        mMerchantsRecycler.setAdapter(mAdapter);

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
