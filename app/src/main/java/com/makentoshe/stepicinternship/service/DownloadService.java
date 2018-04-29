package com.makentoshe.stepicinternship.service;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.Favorites;
import com.makentoshe.stepicinternship.common.Loader;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.StepModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 27.04.2018.
 */

public class DownloadService extends Service {

    public static final String COURSE_EXTRA = "RawCourse";

    public DownloadService() {
        super();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        if (intent != null) {
            SearchModel.SearchResult rawCourse = (SearchModel.SearchResult)intent.getSerializableExtra(COURSE_EXTRA);
            new Favorites().add(getApplicationContext(), rawCourse, this);
        }
        return Service.START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
