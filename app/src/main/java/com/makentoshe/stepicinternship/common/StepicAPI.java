package com.makentoshe.stepicinternship.common;

import com.makentoshe.stepicinternship.common.model.AutoCompleteModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Api for Stepic platform.
 */
public interface StepicAPI {

    static String getBaseUrl(){
        return "https://stepik.org";
    }

    @GET("/api/queries")
    Call<AutoCompleteModel> getAutocompleteResult(@Query("query") String autocomplete);

    @GET("/api/search-results")
    Call<SearchModel> getSearchResult(@Query("is_popular") boolean is_popular,
                                      @Query("is_public") boolean is_public,
                                      @Query("language") String language,
                                      @Query("query") String query,
                                      @Query("type") String type);

    @GET
    Call<ResponseBody> getCover(@Url String url);
}
