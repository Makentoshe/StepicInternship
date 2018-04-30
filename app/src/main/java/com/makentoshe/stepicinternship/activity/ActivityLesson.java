package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.Favorites;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.fragment.FragmentStep;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makentoshe on 26.04.2018.
 */

public class ActivityLesson extends AppCompatActivity {

    public static final String LESSON_EXTRA = "lesson";
    public static final String CHILD_POSITION = "child";
    public static final String PARENT_POSITION = "parent";
    public static final String COURSE_ID = "courseID";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        LessonModel.Lesson mLesson = (LessonModel.Lesson) getIntent().getExtras().getSerializable(LESSON_EXTRA);
        if (mLesson != null) {
            createToolbar(mLesson.getTitle());
            createTabs(mLesson);
        } else {
            int childPos = getIntent().getIntExtra(CHILD_POSITION, -1);
            int groupPos = getIntent().getIntExtra(PARENT_POSITION, -1);
            int id = getIntent().getIntExtra(COURSE_ID, -1);
            File lessonDir = findLessonInMem(id, groupPos, childPos);
            if (lessonDir != null){
                createTabsFav(lessonDir);
            }
        }
    }

    private void createTabsFav(File lessonDir){
        ViewPager viewPager = findViewById(R.id.ActivityLesson_ViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        try{
            LessonModel.Lesson lesson = new Gson().fromJson(
                    new FileReader(lessonDir + File.separator + Favorites.lessonFileName),
                    LessonModel.Lesson.class
            );

            for (int i = 0; i < lesson.getSteps().size(); i++) {
                FragmentStep fragmentStep = FragmentStep.newLocalInstance(i, lessonDir);
                adapter.addFragment(fragmentStep, "Step " + (i + 1));
            }

            viewPager.setAdapter(adapter);
            TabLayout tabLayout = findViewById(R.id.ActivityLesson_TabLayout);
            tabLayout.setupWithViewPager(viewPager);
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }


    }

    private File findLessonInMem(int id, int group, int child) {
        try {
            File courseFiles = Favorites.findCourseFolder(id);
            if (courseFiles == null){
                throw new IOException("Can't find course folder.");
            }
            for (final File file : courseFiles.listFiles()){
                if (file.getName().split("\\.")[0].equals(String.valueOf(group))){
                    //section found
                    for (final File _file : file.listFiles()){
                        if (_file.getName().split("\\.")[0].equals(String.valueOf(child))){
                           //lesson found
                            return _file;
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private void createToolbar(String title) {
        TextView titleTV = findViewById(R.id.ActivityLesson_Toolbar_TextView);
        titleTV.setText(title);
        ImageView imageView = findViewById(R.id.ActivityLesson_LeftArrow_ImageView);
        imageView.setImageResource(R.drawable.ic_left_arrow);
        findViewById(R.id.ActivityLesson_LeftArrow).setOnClickListener((v) -> onBackPressed());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createTabs(LessonModel.Lesson lesson) {
        ViewPager viewPager = findViewById(R.id.ActivityLesson_ViewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < lesson.getSteps().size(); i++) {
            Integer stepID = lesson.getSteps().get(i);
            FragmentStep fragmentStep = FragmentStep.newInstance(stepID);
            adapter.addFragment(fragmentStep, "Step " + (i + 1));
            System.out.println("Step " + (i + 1) + ": " + stepID);
        }

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.ActivityLesson_TabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
