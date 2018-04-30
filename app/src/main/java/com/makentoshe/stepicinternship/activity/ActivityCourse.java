package com.makentoshe.stepicinternship.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.Favorites;
import com.makentoshe.stepicinternship.common.Loader;
import com.makentoshe.stepicinternship.common.model.CourseModel;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.SectionModel;
import com.makentoshe.stepicinternship.common.model.UnitModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Makentoshe on 24.04.2018.
 */

public class ActivityCourse extends AppCompatActivity {

    public static final String EXTRA_COURSE = "CourseData";
    private boolean isSaved = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        SearchModel.SearchResult course =
                (SearchModel.SearchResult) getIntent().getSerializableExtra(EXTRA_COURSE);
        loadCourseData(course.getCourse());
        createToolbar(course.getCourseTitle());
    }

    private void createToolbar(String courseTitle) {
        TextView title = findViewById(R.id.ActivityCourse_Toolbar_TextView);
        title.setText(courseTitle);
        ImageView imageView = findViewById(R.id.ActivityCourse_LeftArrow_ImageView);
        imageView.setImageResource(R.drawable.ic_left_arrow);
        findViewById(R.id.ActivityCourse_LeftArrow).setOnClickListener((v) -> onBackPressed());
    }

    private void loadCourseData(int id) {
        ProgressBar progressBar = findViewById(R.id.ActivityCourse_ProgressBar);
        Loader.loadCourse(id, course -> {
            if (course == null) {
                progressBar.setMax(1);
                progressBar.setProgress(1);
                progressBar.setBackgroundColor(Color.RED);
                return;
            }
            List<Integer> courseIds = Favorites.getSavedCourses();

            progressBar.setMax(course.getSections().size());
            progressBar.setProgress(0);
            progressBar.setScaleY(2f);
            progressBar.setIndeterminate(true);

            if (courseIds.contains(course.getId())) {
                isSaved = true;
                Favorites.getCourse(course,
                        (sections, unitsList, lessonsList) -> inflateTitleList(sections, lessonsList));
                progressBar.setMax(1);
                progressBar.setProgress(1);
                progressBar.setIndeterminate(false);

            } else {
                Loader.loadCourseMainDataWithProgress(course, progressBar,
                        (sections, unitsList, lessonsList) -> inflateTitleList(sections, lessonsList)
                );
            }
        });
    }

    private void inflateTitleList
            (List<SectionModel.Section> sectionList, List<List<LessonModel.Lesson>> lessonsList) {
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

        for (List<LessonModel.Lesson> lessons : lessonsList) {
            // создаем коллекцию элементов для группы
            ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
            // заполняем список атрибутов для каждого элемента
            for (LessonModel.Lesson lesson : lessons) {
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
            Intent intent = new Intent(ActivityCourse.this, ActivityLesson.class);
            intent.putExtra(ActivityLesson.LESSON_EXTRA, lessonsList.get(groupPosition).get(childPosition));
            startActivity(intent);
            return true;
        });
    }
}
