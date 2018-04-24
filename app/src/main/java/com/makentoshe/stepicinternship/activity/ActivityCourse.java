package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class ActivityCourse extends Activity<ActivityCourse.ActivityCourseLogic> {

    public static final String EXTRA_COURSE = "CourseData";
    private CourseModel.Course mCourse;

    @Override
    public ActivityCourseLogic newLogicInstance() {
        return new ActivityCourseLogic();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getLogic().onCreate(savedInstanceState);
    }

    class ActivityCourseLogic extends DefaultActivityLogic {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            SearchModel.SearchResult course = (SearchModel.SearchResult) getIntent().getSerializableExtra(EXTRA_COURSE);
            loadCourseData(course.getId());
            createToolbar(course.getCourseTitle());
            createTitleList();
        }

        private void createToolbar(String courseTitle){
            TextView title = findViewById(R.id.ActivityCourse_Toolbar_TextView);
            title.setText(courseTitle);
        }

        private void createTitleList() {
            ListView titleList = findViewById(R.id.ActivityCourse_TitleList);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    ActivityCourse.this,
                    android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.activity_course_title_list)
            );
            titleList.setAdapter(adapter);

            titleList.setOnItemClickListener((parent, view, position, id) ->
                    System.out.println(position)
            );
        }

        private void loadCourseData(int id){
            Callback<CourseModel> callback = new Callback<CourseModel>() {
                @Override
                public void onResponse(Call<CourseModel> call, Response<CourseModel> response) {
                    mCourse = response.body().getCourses().get(0);
                }

                @Override
                public void onFailure(Call<CourseModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Unable to load course data", Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            };
            Call<CourseModel> call = StepicInternship.getApi().getCourseData(id);
            call.enqueue(callback);
        }
    }

}
