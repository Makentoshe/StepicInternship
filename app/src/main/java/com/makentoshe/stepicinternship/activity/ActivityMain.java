package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
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

public class ActivityMain extends AppCompatActivity {

    private WeakReference<FragmentMainContent> fragmentMainContentWeakReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
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
        ImageView clearIcon = findViewById(R.id.ActivityMain_Toolbar_Clear);
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
        searchTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() != 0){
                    clearIcon.setVisibility(View.VISIBLE);
                } else {
                    clearIcon.setVisibility(View.GONE);
                }
            }
        });
        clearIcon.setOnClickListener((v -> {
            searchTextView.setText("");
        }));
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

        Call<SearchModel> call = StepicInternship.getApi()
                .getSearchResult(is_popular, is_public, language, query, type, 1);
        call.enqueue(callback);
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

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.ActivityMain_Toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView clearIcon = findViewById(R.id.ActivityMain_Toolbar_Clear);
        DelayAutoCompleteTextView searchTextView = findViewById(R.id.ActivityMain_Toolbar_SearchTextView);
        ProgressBar bar = findViewById(R.id.ActivityMain_Toolbar_ProgressBar);
        if (searchTextView.getText().toString().length() != 0){
            clearIcon.setVisibility(View.VISIBLE);
        } else {
            clearIcon.setVisibility(View.GONE);
        }
        //do not give the editbox focus automatically when activity starts
        searchTextView.clearFocus();
        bar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((DelayAutoCompleteTextView)
                findViewById(R.id.ActivityMain_Toolbar_SearchTextView)
        ).releaseMemory();
    }
}
