package ass1.bcit.ca;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ca.bcit.assignment1.R;

public class CountryInfo extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private ListView lv;
    private WebView wv;

    private String name;
    private String capital;
    private String region;
    private String population;
    private String area;
    private String borders;
    private String flag;

    private static String SERVICE_URL;

    private JSONArray countryJsonArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);

        String message = getIntent().getStringExtra("message_key");

        new CountryInfo.GetContacts().execute();

        //SERVICE_URL = "https://restcountries.eu/rest/v2/region/" + message.toLowerCase();
        SERVICE_URL = "https://restcountries.eu/rest/v2/name/" + message.toLowerCase() + "?fields=name;capital;region;population;area;borders;flag;";

        wv = findViewById(R.id.countryFlag);
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(CountryInfo.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    countryJsonArray = new JSONArray(jsonStr);

                    // Getting JSON Array node
                    JSONObject countryJsonObject = countryJsonArray.getJSONObject(0);


                    name = countryJsonObject.getString("name");
                    capital = countryJsonObject.getString("capital");
                    region = countryJsonObject.getString("region");
                    population = countryJsonObject.getString("population");
                    area = countryJsonObject.getString("area");
                    borders = countryJsonObject.getString("borders");
                    flag = countryJsonObject.getString("flag");



                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            TextView countryName = (TextView) findViewById(R.id.name);
            TextView countryCapital = (TextView) findViewById(R.id.capital);
            TextView countryRegion = (TextView) findViewById(R.id.region);
            TextView countryPopulation = (TextView) findViewById(R.id.population);
            TextView countryArea = (TextView) findViewById(R.id.area);
            TextView countryBorders = (TextView) findViewById(R.id.borders);

            countryName.setText(name);
            countryCapital.setText(capital);
            countryRegion.setText(region);
            countryPopulation.setText(population);
            countryArea.setText(area + " km");
            countryBorders.setText(borders);


            wv.loadUrl(flag);
        }
    }




}
