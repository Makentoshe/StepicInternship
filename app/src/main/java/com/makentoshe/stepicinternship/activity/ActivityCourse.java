package com.makentoshe.stepicinternship.activity;

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
import java.util.Collections;
import java.util.Comparator;
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
    private boolean loadingWait = true;

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

        private void loadTitleData(ProgressBar progressBar) {
            List<SectionModel.Section> sectionList = new ArrayList<>();
            List<List<LessonModel.Lesson>> lessonsList = new ArrayList<>();
            for (int i = 0; i < mCourse.getSections().size(); i++) {
                lessonsList.add(null);
            }
            final int[] sectionCount = {0};
            // загружаем названия групп
            for (Integer sectionID : mCourse.getSections()) {
                Call<SectionModel> call = StepicInternship.getApi().getSectionData(sectionID);
                call.enqueue(new Callback<SectionModel>() {

                    @Override
                    public void onResponse(Call<SectionModel> call, Response<SectionModel> response) {
                        SectionModel.Section section = response.body().getSections().get(0);
                        List<LessonModel.Lesson> lessons = new ArrayList<>();

                        for (int i = 0; i < section.getUnits().size(); i++) {
                            lessons.add(null);
                        }

                        sectionList.add(section);
                        final int[] lessonCount = {0};
                        for (Integer unitID : section.getUnits()) {
                            Call<UnitModel> ucall = StepicInternship.getApi().getUnitData(unitID);
                            ucall.enqueue(new Callback<UnitModel>() {

                                @Override
                                public void onResponse(Call<UnitModel> call, Response<UnitModel> response) {
                                    UnitModel.Unit unit = response.body().getUnits().get(0);
                                    Call<LessonModel> lcall = StepicInternship.getApi().getLessonData(unit.getLesson());
                                    lcall.enqueue(new Callback<LessonModel>() {

                                        @Override
                                        public void onResponse(Call<LessonModel> call, Response<LessonModel> response) {
                                            lessonCount[0]++;
                                            lessons.set(unit.getPosition() - 1, response.body().getLessons().get(0));
                                            if (lessonCount[0] == section.getUnits().size()) {
                                                sectionCount[0]++;
                                                lessonsList.set(section.getPosition() - 1, lessons);
                                                progressBar.setIndeterminate(false);
                                                progressBar.setProgress(sectionCount[0]);

                                                if (sectionCount[0] == mCourse.getSections().size()) {
                                                    inflateTitleList(sectionList, lessonsList);
                                                }
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
                    }

                    @Override
                    public void onFailure(Call<SectionModel> call, Throwable t) {

                    }
                });
            }

        }

        private void createTitleList(CourseModel.Course mCourse) {
            ProgressBar progressBar = findViewById(R.id.ActivityCourse_ProgressBar);
            progressBar.setMax(mCourse.getSections().size());
            progressBar.setProgress(0);
            progressBar.setScaleY(2f);
            progressBar.setIndeterminate(true);

            loadTitleData(progressBar);
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

        private void inflateTitleList(List<SectionModel.Section> sectionList, List<List<LessonModel.Lesson>> lessonsList) {
            Collections.sort(sectionList, (o1, o2) -> {
                if (o1.getPosition() > o2.getPosition()) {
                    return 1;
                }
                if (o1.getPosition() < o2.getPosition()) {
                    return -1;
                }
                return 0;
            });
            // коллекция для групп
            ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
            // список атрибутов групп для чтения
            String groupFrom[] = new String[]{"groupName"};
            // список ID view-элементов, в которые будет помещены атрибуты групп
            int groupTo[] = new int[]{android.R.id.text1};

            // создаем общую коллекцию для коллекций элементов
            ArrayList<ArrayList<Map<String, String>>> childDataList = new ArrayList<>();
            // список атрибутов элементов для чтения
            String childFrom[] = new String[]{"monthName"};
            // список ID view-элементов, в которые будет помещены атрибуты элементов
            int childTo[] = new int[]{android.R.id.text1};

            for (int i = 0; i < sectionList.size(); i++) {
                HashMap<String, String> map = new HashMap<>();
                map.put("groupName", sectionList.get(i).getTitle());
                groupDataList.add(map);
            }

            for (List<LessonModel.Lesson> lessons : lessonsList){
                // создаем коллекцию элементов для группы
                ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
                // заполняем список атрибутов для каждого элемента
                for (LessonModel.Lesson lesson: lessons) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("monthName", lesson.getTitle()); // название месяца
                    сhildDataItemList.add(map);
                }
                // добавляем в коллекцию коллекций
                childDataList.add(сhildDataItemList);
            }

            SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                    ActivityCourse.this, groupDataList,
                    android.R.layout.simple_expandable_list_item_1, groupFrom,
                    groupTo, childDataList, android.R.layout.simple_list_item_1,
                    childFrom, childTo);

            ExpandableListView expandableListView = findViewById(R.id.ActivityCourse_ExpListView);
            expandableListView.setAdapter(adapter);

            expandableListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
                System.out.println(lessonsList.get(groupPosition).get(childPosition).getTitle());
                return false;
            });
        }
    }
}
