package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.adapter.AutoCompleteSearchAdapter;
import com.makentoshe.stepicinternship.common.StepicAPI;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.fragment.FragmentFavoriteContent;
import com.makentoshe.stepicinternship.fragment.FragmentMainContent;
import com.makentoshe.stepicinternship.view.DelayAutoCompleteTextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.ActivityMain_Toolbar);
    }


    /**
     * Class which describes logic for ActivityMain
     */
    class ActivityMainLogic extends DefaultActivityLogic {

        private WeakReference<FragmentMainContent> fragmentMainContentWeakReference;

        //Вызывается после UI
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            createTabs();
            createSearchTextView();
        }

        private void createTabs() {
            ViewPager viewPager = findViewById(R.id.ActivityMain_ViewPager);
            ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

            FragmentMainContent fragmentMainContent = FragmentMainContent.newInstance();
            adapter.addFragment(fragmentMainContent, "Catalog");
            fragmentMainContentWeakReference = new WeakReference<>(fragmentMainContent);

            adapter.addFragment(FragmentFavoriteContent.newInstance(), "Favorites");
            viewPager.setAdapter(adapter);
            TabLayout tabLayout = findViewById(R.id.ActivityMain_TabLayout);
            tabLayout.setupWithViewPager(viewPager);
        }

        private void createSearchTextView() {
            DelayAutoCompleteTextView searchTextView = findViewById(R.id.ActivityMain_Toolbar_SearchTextView);
            AutoCompleteSearchAdapter adapter = new AutoCompleteSearchAdapter(ActivityMain.this);
            searchTextView.setAutoCompleteProgressBar(findViewById(R.id.ActivityMain_Toolbar_ProgressBar));
            searchTextView.setAdapter(adapter);
            searchTextView.setOnItemClickListener((parent, view, position, id) ->
                    startSearch(
                            true,
                            true,
                            "ru",
                            adapter.getItem(position),
                            "course"
                    )
            );
            searchTextView.setOnEditorActionListener((textView, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    startSearch(
                            true,
                            true,
                            "ru",
                            searchTextView.getText().toString(),
                            "course"
                    );
                    return true;
                }
                return false;
            });
        }

        private void startSearch(boolean is_popular, boolean is_public, String language, String query, String type) {
            Callback<SearchModel> callback = new Callback<SearchModel>() {
                @Override
                public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                    SearchModel model = response.body();
                    fragmentMainContentWeakReference.get()
                            .receiveSearchModel(model, is_popular, is_public, language, query, type);
                }

                @Override
                public void onFailure(Call<SearchModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something go wrong", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            };

            StepicAPI.search(is_popular, is_public, language, query, type, 1, callback);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((DelayAutoCompleteTextView)
                findViewById(R.id.ActivityMain_Toolbar_SearchTextView)
        ).releaseMemory();
    }
}
