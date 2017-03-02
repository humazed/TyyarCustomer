package com.tyyar.tyyarfooddelivery.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.appspot.amiable_aquifer_157201.helloworld.Helloworld;
import com.tyyar.tyyarfooddelivery.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GAETestActivity extends AppCompatActivity {

    @BindView(R.id.textView) TextView mTextView;
    @BindView(R.id.button) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaetest);
        ButterKnife.bind(this);



    }
}
