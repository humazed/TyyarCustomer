package com.tyyar.tyyarfooddelivery.screens;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.microsoft.windowsazure.notifications.NotificationsManager;
import com.tyyar.tyyarfooddelivery.R;
import com.tyyar.tyyarfooddelivery.notification.MyHandler;
import com.tyyar.tyyarfooddelivery.notification.NotificationSettings;
import com.tyyar.tyyarfooddelivery.notification.RegistrationIntentService;
import com.tyyar.tyyarfooddelivery.screens.home.HomeActivity;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckoutActivity extends AppCompatActivity {
    private static final String TAG = CheckoutActivity.class.getSimpleName();

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.checkout_error_textView) TextView mCheckoutErrorTextView;
    @BindView(R.id.address_title_textView) TextView mAddressTitleTextView;
    @BindView(R.id.address_details_textView) TextView mAddressDetailsTextView;
    @BindView(R.id.change_address_arrow) ImageView mChangeAddressArrow;
    @BindView(R.id.delivery_instructions_editText) EditText mDeliveryInstructionsEditText;
    @BindView(R.id.eta_content_textView) TextView mEtaContentTextView;
    @BindView(R.id.credit_card_type_textView) TextView mCreditCardTypeTextView;
    @BindView(R.id.last_four_digits_textView) TextView mLastFourDigitsTextView;
    @BindView(R.id.menu_options_header) TextView mMenuOptionsHeader;
    @BindView(R.id.menu_options_list) LinearLayout mMenuOptionsList;
    @BindView(R.id.menu_options_container) LinearLayout mMenuOptionsContainer;
    @BindView(R.id.driver_tip_title) TextView mDriverTipTitle;
    @BindView(R.id.driver_tip_textView) TextView mDriverTipTextView;
    @BindView(R.id.price_textView) TextView mPriceTextView;
    @BindView(R.id.place_order_button) RelativeLayout mPlaceOrderButton;
    @BindView(R.id.activity_checkout) LinearLayout mActivityCheckout;

    private double mTotalPrice;
    private double mFinalTotalPrice;


    public static CheckoutActivity mainActivity;
    public static Boolean isVisible = false;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    private String HubEndpoint = null;
    private String HubSasKeyName = null;
    private String HubSasKeyValue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setTitle(R.string.chat_activity_title);

        Intent intent = getIntent();
        mTotalPrice = intent.getDoubleExtra(CartActivity.KEY_TOTAL_PRICE, 0);

        mDriverTipTextView.setText(getString(R.string.common_price, calculateTip(.15)));

//        mPlaceOrderButton.setOnClickListener(v -> startActivity(new Intent(this, HomeActivity.class)));
        mPlaceOrderButton.setOnClickListener(this::sendNotificationButtonOnClick);

    }

    private double calculateTip(double tipPercentage) {
        double tip = mTotalPrice * tipPercentage;
        mDriverTipTextView.setText(getString(R.string.common_price, tip));
        mFinalTotalPrice = mTotalPrice + tip;
        mPriceTextView.setText(getString(R.string.common_price, mFinalTotalPrice));
        return tip;
    }

    public void onTipRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.tip_10: if (checked)
                calculateTip(.10);
                break;
            case R.id.tip_15: if (checked)
                calculateTip(.15);
                break;
            case R.id.tip_20: if (checked)
                calculateTip(.20);
                break;
            case R.id.tip_other: if (checked)
                calculateTip(0);
                break;
        }
    }

    /**
     * Example code from http://msdn.microsoft.com/library/azure/dn495627.aspx
     * to parse the connection string so a SaS authentication token can be
     * constructed.
     *
     * @param connectionString This must be the DefaultFullSharedAccess connection
     *                         string for this example.
     */
    private void ParseConnectionString(String connectionString) {
        String[] parts = connectionString.split(";");
        if (parts.length != 3)
            throw new RuntimeException("Error parsing connection string: "
                    + connectionString);

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].startsWith("Endpoint")) {
                this.HubEndpoint = "https" + parts[i].substring(11);
            } else if (parts[i].startsWith("SharedAccessKeyName")) {
                this.HubSasKeyName = parts[i].substring(20);
            } else if (parts[i].startsWith("SharedAccessKey")) {
                this.HubSasKeyValue = parts[i].substring(16);
            }
        }
    }

    /**
     * Example code from http://msdn.microsoft.com/library/azure/dn495627.aspx to
     * construct a SaS token from the access key to authenticate a request.
     *
     * @param uri The unencoded resource URI string for this operation. The resource
     *            URI is the full URI of the Service Bus resource to which access is
     *            claimed. For example,
     *            "http://<namespace>.servicebus.windows.net/<hubName>"
     */
    private String generateSasToken(String uri) {

        String targetUri;
        String token = null;
        try {
            targetUri = URLEncoder
                    .encode(uri.toString().toLowerCase(), "UTF-8")
                    .toLowerCase();

            long expiresOnDate = System.currentTimeMillis();
            int expiresInMins = 60; // 1 hour
            expiresOnDate += expiresInMins * 60 * 1000;
            long expires = expiresOnDate / 1000;
            String toSign = targetUri + "\n" + expires;

            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = HubSasKeyValue.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(toSign.getBytes("UTF-8"));

            // Using android.util.Base64 for Android Studio instead of
            // Apache commons codec
            String signature = URLEncoder.encode(
                    Base64.encodeToString(rawHmac, Base64.NO_WRAP).toString(), "UTF-8");

            // Construct authorization string
            token = "SharedAccessSignature sr=" + targetUri + "&sig="
                    + signature + "&se=" + expires + "&skn=" + HubSasKeyName;
        } catch (Exception e) {
            if (isVisible) {
                ToastNotify("Exception Generating SaS : " + e.getMessage().toString());
            }
        }

        return token;
    }

    /**
     * Send Notification button click handler. This method parses the
     * DefaultFullSharedAccess connection string and generates a SaS token. The
     * token is added to the Authorization header on the POST request to the
     * notification hub. The text in the editTextNotificationMessage control
     * is added as the JSON body for the request to add a GCM message to the hub.
     *
     * @param v
     */
    public void sendNotificationButtonOnClick(View v) {
        final String json = "{\"data\":{\"message\":\"" + "from Tyyar delivery" + "\"}}";

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    // Based on reference documentation...
                    // http://msdn.microsoft.com/library/azure/dn223273.aspx
                    ParseConnectionString(NotificationSettings.HubFullAccess);
                    URL url = new URL(HubEndpoint + NotificationSettings.HubName +
                            "/messages/?api-version=2015-01");

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    try {
                        // POST request
                        urlConnection.setDoOutput(true);

                        // Authenticate the POST request with the SaS token
                        urlConnection.setRequestProperty("Authorization",
                                generateSasToken(url.toString()));

                        // Notification format should be GCM
                        urlConnection.setRequestProperty("ServiceBusNotification-Format", "gcm");

                        // Include any tags
                        // Example below targets 3 specific tags
                        // Refer to : https://azure.microsoft.com/en-us/documentation/articles/notification-hubs-routing-tag-expressions/
                         urlConnection.setRequestProperty("ServiceBusNotification-Tags",
                                "merchant");

                        // Send notification message
                        urlConnection.setFixedLengthStreamingMode(json.length());
                        OutputStream bodyStream = new BufferedOutputStream(urlConnection.getOutputStream());
                        bodyStream.write(json.getBytes());
                        bodyStream.close();

                        // Get reponse
                        urlConnection.connect();
                        int responseCode = urlConnection.getResponseCode();
                        if ((responseCode != 200) && (responseCode != 201)) {
                            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
                            String line;
                            StringBuilder builder = new StringBuilder("Send Notification returned " +
                                    responseCode + " : ");
                            while ((line = br.readLine()) != null) {
                                builder.append(line);
                            }

                            ToastNotify(builder.toString());
                        }
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    if (isVisible) {
                        ToastNotify("Exception Sending Notification : " + e.getMessage().toString());
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                startActivity(new Intent(CheckoutActivity.this, HomeActivity.class));
            }
        };
        runAsyncTask(task);

/*
        new Thread() {
            public void run() {
                try {
                    // Based on reference documentation...
                    // http://msdn.microsoft.com/library/azure/dn223273.aspx
                    ParseConnectionString(NotificationSettings.HubFullAccess);
                    URL url = new URL(HubEndpoint + NotificationSettings.HubName +
                            "/messages/?api-version=2015-01");

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    try {
                        // POST request
                        urlConnection.setDoOutput(true);

                        // Authenticate the POST request with the SaS token
                        urlConnection.setRequestProperty("Authorization",
                                generateSasToken(url.toString()));

                        // Notification format should be GCM
                        urlConnection.setRequestProperty("ServiceBusNotification-Format", "gcm");

                        // Include any tags
                        // Example below targets 3 specific tags
                        // Refer to : https://azure.microsoft.com/en-us/documentation/articles/notification-hubs-routing-tag-expressions/
                        // urlConnection.setRequestProperty("ServiceBusNotification-Tags",
                        //        "tag1 || tag2 || tag3");

                        // Send notification message
                        urlConnection.setFixedLengthStreamingMode(json.length());
                        OutputStream bodyStream = new BufferedOutputStream(urlConnection.getOutputStream());
                        bodyStream.write(json.getBytes());
                        bodyStream.close();

                        // Get reponse
                        urlConnection.connect();
                        int responseCode = urlConnection.getResponseCode();
                        if ((responseCode != 200) && (responseCode != 201)) {
                            BufferedReader br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
                            String line;
                            StringBuilder builder = new StringBuilder("Send Notification returned " +
                                    responseCode + " : ");
                            while ((line = br.readLine()) != null) {
                                builder.append(line);
                            }

                            ToastNotify(builder.toString());
                        }
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (Exception e) {
                    if (isVisible) {
                        ToastNotify("Exception Sending Notification : " + e.getMessage().toString());
                    }
                }
            }
        }.start();
*/
    }

    private AsyncTask<Void, Void, Void> runAsyncTask(AsyncTask<Void, Void, Void> task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else {
            return task.execute();
        }
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported by Google Play Services.");
                ToastNotify("This device is not supported by Google Play Services.");
                finish();
            }
            return false;
        }
        return true;
    }

    public void registerWithNotificationHubs() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with FCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    public void ToastNotify(final String notificationMessage) {
        runOnUiThread(() -> {
            Toast.makeText(CheckoutActivity.this, notificationMessage, Toast.LENGTH_LONG).show();
//            TextView helloText = (TextView) findViewById(R.id.text_hello);
//            helloText.setText(notificationMessage);
            Toast.makeText(CheckoutActivity.this, "notificationMessage: " + notificationMessage, Toast.LENGTH_LONG).show();
            Log.d(TAG, "ToastNotify " + "notificationMessage: " + notificationMessage);
        });

    }

    private void setupNotification() {
        mainActivity = this;
        NotificationsManager.handleNotifications(this, NotificationSettings.SenderId, MyHandler.class);
        registerWithNotificationHubs();
    }

    @Override
    protected void onStart() {
        super.onStart();
        isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isVisible = false;
    }

}