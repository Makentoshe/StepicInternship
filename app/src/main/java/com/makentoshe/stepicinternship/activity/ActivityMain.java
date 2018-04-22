package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.fragment.FragmentFavoriteContent;
import com.makentoshe.stepicinternship.fragment.FragmentMainContent;

import java.util.ArrayList;
import java.util.List;

public class ActivityMain extends Activity<ActivityMain.ActivityMainLogic> {

    @Override
    public ActivityMainLogic newLogicInstance() {
        return new ActivityMainLogic();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
        getLogic().onCreate(savedInstanceState);
    }

    private void createToolbar(){
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
    }


    /**
     * Класс описывающий логику для ActivityMain
     */
    class ActivityMainLogic extends DefaultActivityLogic {

        //Вызывается после UI
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ViewPager viewPager = findViewById(R.id.pager);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
            adapter.addFragment(FragmentMainContent.newInstance(), "Catalog");
            adapter.addFragment(FragmentFavoriteContent.newInstance(), "Favorites");
            viewPager.setAdapter(adapter);

            TabLayout tabLayout = findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
        }

        // Adapter for the viewpager using FragmentPagerAdapter

        /**
         * Адаптер для ViewPager использующий FragmentPagerAdapter
         */
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

}
