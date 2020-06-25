package com.smart.ticketing.facereco.mobitravel;

import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.backendless.Backendless;
import com.backendless.IDataStore;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.smart.ticketing.R;
import com.smart.ticketing.Utils;
import com.smart.ticketing.facereco.Api;
import com.smart.ticketing.facereco.DataParser;
import com.smart.ticketing.facereco.LocationHandler;
import com.smart.ticketing.facereco.data.DistanceResponse;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String TAG = "MapsActivity";
    List<String> locationlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobitravel_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationlist = getIntent().getStringArrayListExtra("location");
//        initLocation();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(12.9249364, 77.4971921);

//        addLine();
        drawMap();
    }

    public void initLocation() {
        LocationHandler handler = new LocationHandler(MapsActivity.this);
        handler.initLocation(new LocationHandler.OnLocationChanged() {
            @Override
            public void onLocationAvailable(final Location location) {
                Utils.showToast(getApplicationContext(), "Found current location");
                updateBusLocation(location);

            }
        });
    }

    Marker currentLocationmarker, busLocationMarker;


    public void updateBusLocation(final Location location) {
        Log.i(TAG, "updating bus location");
        IDataStore<com.smart.ticketing.facereco.Location> querydata = Backendless.Data.of(com.smart.ticketing.facereco.Location.class);
        DataQueryBuilder query = DataQueryBuilder.create();
        query.setWhereClause("busId='KA02JA7665'");

        querydata.find(query, new AsyncCallback<List<com.smart.ticketing.facereco.Location>>() {
            @Override
            public void handleResponse(List<com.smart.ticketing.facereco.Location> response) {
                Log.i(TAG, "Size of bus: " + response.size());
                if (response.size() > 0) {
                    getETA(response.get(0).getLocation(), location.getLatitude() + "," + location.getLongitude());
                    LatLng latLng1 = new LatLng(location.getLatitude(), location.getLongitude());
                    String[] temp = response.get(0).getLocation().split("\\,");
                    LatLng latLng2 = new LatLng(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]));
                    drawMap(latLng1, latLng2);
                } else {
                    Utils.showToast(getApplicationContext(), "bus not found");
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Log.i(TAG, "fault: " + fault);
            }
        });
    }

    public void getETA(final String origin, String dest) {
        Api.ApiInterface apiInterface = Api.getClient(MapsActivity.this).create(Api.ApiInterface.class);

        Call<DistanceResponse> call = apiInterface.reco(origin, dest, "driving", "best_guess", "now", "AIzaSyAzjLiffpke-H4Z0UsmA1UzhFHWO3YNiKU");
        call.enqueue(new Callback<DistanceResponse>() {
            @Override
            public void onResponse(Call<DistanceResponse> call, Response<DistanceResponse> response) {
                String[] temp = origin.split("\\,");

//                updateBusLocationonMap(Double.parseDouble(temp[0]), Double.parseDouble(temp[1]),  "ETA: " + response.body().getRows().get(0).getElements().get(0).getDuration().getText() , response.body().getRows().get(0).getElements().get(0).getDistance().getText());
            }

            @Override
            public void onFailure(Call<DistanceResponse> call, Throwable t) {

            }
        });
    }


    public void drawMap(LatLng origin, LatLng dest) {
        String url = getUrl(origin, dest);
        Log.d("onMapClick", url.toString());
        FetchUrl FetchUrl = new FetchUrl();

        // Start downloading json data from Google Directions API
        FetchUrl.execute(url);
    }

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;


        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }

    /**
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


    // Fetches data from url passed
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /**
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask", jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask", "Executing routes");
                Log.d("ParserTask", routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask", e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute", "onPostExecute lineoptions decoded");

            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                mMap.addPolyline(lineOptions);
            } else {
                Log.d("onPostExecute", "without Polylines drawn");
            }
        }
    }


    public void addLine() {


        /*Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(-35.016, 143.321),
                        new LatLng(-34.747, 145.592),
                        new LatLng(-34.364, 147.891),
                        new LatLng(-33.501, 150.217),
                        new LatLng(-32.306, 149.248),
                        new LatLng(-32.491, 147.309))
                .color(Color.RED));*/


        Polyline polyline1 = mMap.addPolyline(new PolylineOptions()
                .clickable(true)
                .add(
                        new LatLng(12.9701604,77.5376551),
                        new LatLng(13.0031302,77.528346),
                        new LatLng(12.9989795,77.5300342),
                        new LatLng(12.9987938,77.5309137),
                        new LatLng(12.9987938,77.5309137),
                        new LatLng(12.9983364,77.530458))
                .color(Color.RED));

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-23.684, 133.903), 4));


        /*PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.add(new LatLng(13.0013378,77.5392284));
        lineOptions.add(new LatLng(13.0023076,77.5348583));

        lineOptions.width(10);
        lineOptions.color(Color.RED);

        mMap.addPolyline(lineOptions);*/

    }

    public void drawMap() {


        PolylineOptions polylineOptions = new PolylineOptions();
        LatLng latLng = null;
        List<LatLng> latLngs = new ArrayList<LatLng>() {
            {
                add(new LatLng(12.9701604,77.5376551));
                add(new LatLng(13.0031302,77.528346));
                add(new LatLng(12.9989795,77.5300342));
                add(new LatLng(12.9987938,77.5309137));
                add(new LatLng(12.9987938,77.5309137));
                add(new LatLng(12.9983364,77.530458));
            }
        };

        for (String location : locationlist) {
            String[] loc = location.split("\\,");
            latLng = new LatLng(Double.parseDouble(loc[0]), Double.parseDouble(loc[1]));
//            latLng = location;
            polylineOptions.add(latLng);
        }

//        polylineOptions.add(new LatLng(13.0031302,77.528346));
        /*for (LatLng location : latLngs) {
            latLng = location;
            polylineOptions.add(latLng);
        }*/

        polylineOptions.color(Color.RED);
        Polyline polyline1 = mMap.addPolyline(polylineOptions);

        // Position the map's camera near Alice Springs in the center of Australia,
        // and set the zoom factor so most of Australia shows on the screen.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 45));

    }
}
