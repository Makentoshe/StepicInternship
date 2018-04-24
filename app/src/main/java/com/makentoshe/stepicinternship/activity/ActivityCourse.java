package com.makentoshe.stepicinternship.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            loadCourseData(course.getCourse());
            createToolbar(course.getCourseTitle());
        }

        private void createToolbar(String courseTitle) {
            TextView title = findViewById(R.id.ActivityCourse_Toolbar_TextView);
            title.setText(courseTitle);
        }

        private void createTitleList(CourseModel.Course mCourse) {
            final int[] progress = {0};
            ProgressBar progressBar = findViewById(R.id.ActivityCourse_ProgressBar);
            progressBar.setMax(mCourse.getSections().size());
            progressBar.setProgress(progress[0]);
            progressBar.setScaleY(2f);
            progressBar.setIndeterminate(true);

            ExpandableListView titleList = findViewById(R.id.ActivityCourse_ExpListView);
            // коллекция для групп
            ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
            // список атрибутов групп для чтения
            String groupFrom[] = new String[]{"groupName"};
            // список ID view-элементов, в которые будет помещены атрибуты групп
            int groupTo[] = new int[]{android.R.id.text1};
            // создаем общую коллекцию для коллекций элементов
            ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();
            // список атрибутов элементов для чтения
            String childFrom[] = new String[]{"monthName"};
            // список ID view-элементов, в которые будет помещены атрибуты
            // элементов
            int childTo[] = new int[]{android.R.id.text1};

            // загружаем названия групп
            for (Integer sectionID : mCourse.getSections()) {
                Call<SectionModel> call = StepicInternship.getApi().getSectionData(sectionID);
                call.enqueue(new Callback<SectionModel>() {
                    @Override
                    public void onResponse(Call<SectionModel> call, Response<SectionModel> response) {
                        Map<String, String> map;
                        //заполняем названия групп названиями секций
                        for (SectionModel.Section section : response.body().getSections()) {
                            map = new HashMap<>();
                            // Название секции
                            map.put("groupName", section.getTitle());
                            // Заполняем
                            groupDataList.add(map);
                            //// Загружаем дочерние элементы
                            //// У каждой секции есть массив юнитов. У каждого юнита есть урок.
                            // Коллекция элементов
                            ArrayList<Map<String, String>> сhildDataItem = new ArrayList<>();
                            // Для каждого юнита
                            for (Integer unitID : section.getUnits()) {
                                // загружаем его
                                Call<UnitModel> unitCALL = StepicInternship.getApi().getUnitData(unitID);
                                unitCALL.enqueue(new Callback<UnitModel>() {
                                    @Override
                                    public void onResponse(Call<UnitModel> call, Response<UnitModel> response) {
                                        // Загрузили...
                                        UnitModel.Unit unit = response.body().getUnits().get(0);
                                        // Теперь загружаем урок
                                        Call<LessonModel> LessonCALL = StepicInternship.getApi().getLessonData(unit.getLesson());
                                        LessonCALL.enqueue(new Callback<LessonModel>() {
                                            @Override
                                            public void onResponse(Call<LessonModel> call, Response<LessonModel> response) {
                                                Map<String, String> map = new HashMap<>();
                                                // название урока
                                                map.put("monthName", response.body().getLessons().get(0).getTitle());
                                                сhildDataItem.add(map);
                                                progressBar.setIndeterminate(false);
                                                progressBar.setProgress(++progress[0]);
                                                if (progress[0] == progressBar.getMax()){
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<LessonModel> call, Throwable t) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onFailure(Call<UnitModel> call, Throwable t) {

                                    }
                                });
                            }
                            // добавляем в коллекцию коллекций
                            сhildDataList.add(сhildDataItem);
                        }

                        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                                ActivityCourse.this, groupDataList,
                                android.R.layout.simple_expandable_list_item_1, groupFrom,
                                groupTo, сhildDataList, android.R.layout.simple_list_item_1,
                                childFrom, childTo);

                        titleList.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<SectionModel> call, Throwable t) {

                    }
                });
            }

        }

        private void loadCourseData(int id) {
            Callback<CourseModel> callback = new Callback<CourseModel>() {
                @Override
                public void onResponse(Call<CourseModel> call, Response<CourseModel> response) {
                    mCourse = response.body().getCourses().get(0);
                    createTitleList(mCourse);
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
