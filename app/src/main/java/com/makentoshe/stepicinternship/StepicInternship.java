package com.makentoshe.stepicinternship;

import android.app.Application;

import com.makentoshe.stepicinternship.common.StepicAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StepicInternship extends Application {

    private static StepicAPI stepicAPI;
    private Retrofit mRetrofit;

    //settings
    /**
     * Data will be download and delete after using. If you need this data again,
     * you need download it's again(true).
     * Otherwise the data will be storage in memory and access will be instant(false).
     */
    public static final boolean save_memory_mode = false;

    @Override
    public void onCreate() {
        super.onCreate();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(StepicAPI.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        stepicAPI = mRetrofit.create(StepicAPI.class);
    }

    public static StepicAPI getApi() {
        return stepicAPI;
    }
}
