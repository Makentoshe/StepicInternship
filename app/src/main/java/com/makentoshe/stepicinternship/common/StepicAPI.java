package com.makentoshe.stepicinternship.common;

import com.makentoshe.stepicinternship.common.model.AutoCompleteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public interface StepicAPI {

    static String getBaseUrl(){
        return "https://stepik.org";
    }

    @GET("/api/queries")
    Call<AutoCompleteModel> getAutocompleteData(@Query("query") String autocomplete);
}
