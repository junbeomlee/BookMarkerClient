package com.example.leejunbeom.bookMarker.ui;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.leejunbeom.test.R;

import butterknife.Bind;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    //@Inject
    //SIFT SIFT;

    @Bind(R.id.bookAddButton)
    Button bookAddButton;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
   // private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        //startActivityForResult(intent, 2000);
        //injector.get().inject(this);
        //IntentIntegrator integrator = new IntentIntegrator(this);
        //integrator.setOrientationLocked(true);
        //integrator.initiateScan();
        //ButterKnife.bind(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    /* @Override
     public void onActivityResult(int requestCode, int resultCode, Intent intent) {
         IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

         if ((scanResult != null) && (scanResult.getContents() != null)) {
             String data = scanResult.getContents();

             Toast.makeText(this, data,
                         Toast.LENGTH_LONG).show();

         }
     }*/
    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick(R.id.bookAddButton)
    public void onCallClick(){

    }
    /*@OnClick(R.id.button)
    public void onCallClick() {
        Log.d(BMLogger.LOG_TAG, "onCall 54");
        JsonBuilder jsonBuilder = new JsonBuilder_impl();
        StringEntity entity = null;
        try {
            JSONObject storeData = new JSONObject();
            storeData.put("store_number", 10011);

            JSONObject finalReqData = jsonBuilder.buildRequestData(storeData, "ST00101");
            entity = new StringEntity(finalReqData.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.d(BMLogger.LOG_TAG, Boolean.toString(Network_impl.getInstance().isOnline(this)));

        if (!Network_impl.getInstance().isOnline(this)) {
            Log.d(BMLogger.LOG_TAG, "NetworkError");

        }
        Network_impl.getInstance().post(this.getApplicationContext(), "/Store/GetList", entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Root JSON in response is an dictionary i.e { "data : [ ... ] }
                // Handle resulting parsed JSON response here
                Log.d(BMLogger.LOG_TAG, response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d(BMLogger.LOG_TAG, res.toString());

            }

        });
    }*/

}
