package com.tyyar.tyyarfooddelivery.screens.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.screens.home.HomeActivity;
import com.tyyar.tyyarfooddelivery.screens.intro.login_and_signup.LoginAndSignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroActivity extends AppCompatActivity {
    private static final String TAG = IntroActivity.class.getSimpleName();

    @BindView(R.id.logo_imageView) ImageView mLogoImageView;
    @BindView(R.id.pager) ViewPager mPager;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;
    @BindView(R.id.login_button) Button mLoginButton;
    @BindView(R.id.get_started_button) Button mGetStartedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(v -> startActivity(new Intent(this, LoginAndSignupActivity.class)));
        mGetStartedButton.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));


    }
}
