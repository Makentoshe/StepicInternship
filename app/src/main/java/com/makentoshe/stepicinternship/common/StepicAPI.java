package com.makentoshe.stepicinternship.common;

import com.makentoshe.stepicinternship.common.model.AutoCompleteModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Api for Stepic platform.
 */
public interface StepicAPI {

    static String getBaseUrl(){
        return "https://stepik.org";
    }

    @GET("/api/queries")
    Call<AutoCompleteModel> getAutocompleteData(@Query("query") String autocomplete);
}
