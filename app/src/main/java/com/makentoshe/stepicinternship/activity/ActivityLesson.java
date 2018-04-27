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

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.common.model.LessonModel;
import com.makentoshe.stepicinternship.fragment.FragmentStep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Makentoshe on 26.04.2018.
 */

public class ActivityLesson extends AppCompatActivity {

    public static final String LESSON_EXTRA = "lesson";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        LessonModel.Lesson mLesson = (LessonModel.Lesson) getIntent().getExtras().getSerializable(LESSON_EXTRA);
        createToolbar(mLesson.getTitle());
        createTabs(mLesson);
    }

    private void createToolbar(String title){
        TextView titleTV = findViewById(R.id.ActivityLesson_Toolbar_TextView);
        titleTV.setText(title);
        ImageView imageView = findViewById(R.id.ActivityLesson_LeftArrow_ImageView);
        imageView.setImageResource(R.drawable.ic_left_arrow);
        findViewById(R.id.ActivityLesson_LeftArrow).setOnClickListener((v)-> onBackPressed());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
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
            adapter.addFragment(fragmentStep, "Step " + (i+1));
            System.out.println("Step " + (i+1) + ": " + stepID);
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
