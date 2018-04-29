package com.makentoshe.stepicinternship.common;

import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.AutoCompleteModel;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.StepModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;
import com.makentoshe.stepicinternship.common.model.UserModel;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
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
                                      @Query("type") String type,
                                      @Query("page") int page);

    @GET
    Call<ResponseBody> getCover(@Url String url);

    @GET("/api/users/{USER_ID}")
    Call<UserModel> getUser(@Path("USER_ID") int id);

    @GET("api/courses/{ID}")
    Call<CourseModel> getCourseData(@Path("ID") int id);

    @GET("api/sections/{ID}")
    Call<SectionModel> getSectionData(@Path("ID") int id);

    @GET("api/units/{ID}")
    Call<UnitModel> getUnitData(@Path("ID") int id);

    @GET("api/lessons/{ID}")
    Call<LessonModel> getLessonData(@Path("ID") int id);

    @GET("api/steps/{ID}")
    Call<StepModel> getStepData(@Path("ID") int id);

    @Streaming
    @GET
    Call<ResponseBody> getVideo(@Url String url);
}
