package com.smart.ticketing.facereco;

/**
 * Created by dayanand on 27/08/17.
 */

import android.content.Context;

import com.smart.ticketing.facereco.data.DistanceResponse;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;


public class Api {

    public static String BASE_URL = "https://maps.googleapis.com/";
    public static final String RECO_END_URL = "maps/api/distancematrix/json";
    public static Retrofit retrofit = null;


    public static Retrofit getClient(Context context) {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }


    public static Retrofit getNoConverterClient() {
        if (retrofit == null) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);


            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    public interface ApiInterface {


        @GET(RECO_END_URL)
        Call<DistanceResponse> reco(@Query("origins") String origin, @Query("destinations") String dest, @Query("mode") String mode,
                                    @Query("traffic_model") String traffic, @Query("departure_time") String time, @Query("key") String key);
    }

}


