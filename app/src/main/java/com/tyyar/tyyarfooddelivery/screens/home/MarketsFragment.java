package com.tyyar.tyyarfooddelivery.screens.home;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.squareup.okhttp.OkHttpClient;
import com.tyyar.tyyarfooddelivery.DataServer;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.adapters.MerchantsAdapter;
import com.tyyar.tyyarfooddelivery.model.Marhants;
import com.tyyar.tyyarfooddelivery.model.Merchant;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.val;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MarketsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MarketsFragment extends Fragment {
    private static final String TAG = MarketsFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";

    @BindView(R.id.merchants_recycler) RecyclerView mMerchantsRecycler;

    private Unbinder unbinder;
    private OnFragmentInteractionListener mListener;

    private String mParam1;

    private MobileServiceClient mClient;

    /**
     * Mobile Service Table used to access data
     */
    private MobileServiceTable<Marhants> mMerchantsTable;

    private MerchantsAdapter mAdapter;


    public MarketsFragment() {
        // Required empty public constructor
    }

    public static MarketsFragment newInstance(String param1) {
        MarketsFragment fragment = new MarketsFragment();
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
        View view = inflater.inflate(R.layout.fragment_markets, container, false);
        unbinder = ButterKnife.bind(this, view);

        mMerchantsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MerchantsAdapter(DataServer.getMerchants());
        mMerchantsRecycler.setAdapter(mAdapter);

        try {
            // Create the Mobile Service Client instance, using the provided

            // Mobile Service URL and key
            mClient = new MobileServiceClient("https://server001.azurewebsites.net",
                    getActivity());
//                    .withFilter(new ProgressFilter());

            // Extend timeout from default of 10s to 20s
            mClient.setAndroidHttpClientFactory(() -> {
                OkHttpClient client = new OkHttpClient();
                client.setReadTimeout(20, TimeUnit.SECONDS);
                client.setWriteTimeout(20, TimeUnit.SECONDS);
                return client;
            });

            // Get the Mobile Service Table instance to use
            mMerchantsTable = mClient.getTable(Marhants.class);

            // Load the items from the Mobile Service
            refreshItemsFromTable();

        } catch (MalformedURLException e) {
            Log.e(TAG, "onCreateView: ", e);
        } catch (Exception e) {
            Log.e(TAG, "onCreateView: ", e);
        }


        return view;
    }

    /**
     * Refresh the list with the items in the Table
     */
    private void refreshItemsFromTable() {

        // Get the items that weren't marked as completed and add them in the
        // adapter

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {

                Log.d(TAG, "doInBackground out side");

                try {
                    final List<Marhants> results = mMerchantsTable
                            .where()
                            .field("type").eq(val("Market"))
                            .execute().get();

                    Log.d(TAG, "doInBackground " + results);
                    Log.d(TAG, "doInBackground ");

                    ArrayList<Merchant> merchants = DataServer.getMerchants(results);

                    getActivity().runOnUiThread(() -> {
                        mAdapter = new MerchantsAdapter(merchants);
                        mMerchantsRecycler.setAdapter(mAdapter);
                    });
                } catch (final Exception e) {
                    Log.e(TAG, "doInBackground: ", e);
                }

                return null;
            }
        };

        runAsyncTask(task);
    }

    /**
     * Run an ASync task on the corresponding executor
     *
     * @param task
     * @return
     */
    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
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
