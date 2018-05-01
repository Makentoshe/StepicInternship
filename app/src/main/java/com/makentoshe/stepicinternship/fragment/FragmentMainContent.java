package com.makentoshe.stepicinternship.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.activity.ActivityCourse;
import com.makentoshe.stepicinternship.adapter.CourseArrayAdapter;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.service.DownloadService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment contain list of courses.
 */
public class FragmentMainContent extends Fragment {

    private ListView coursesList;
    private ArrayList<SearchModel.SearchResult> coursesDataList = new ArrayList<>();
    private CourseArrayAdapter mAdapter;
    private int mPage = 1; //Текущая страница
    private String mQuery; //Текущий запрос
    private String mLanguage; //Текущий язык запроса

    // подгрузка элементов списка при достижении нижней границы
    private static final boolean enableLoading = true;
    private static final String COURSES_DATA = "coursesData";

    public static FragmentMainContent newInstance() {
        FragmentMainContent fragment = new FragmentMainContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called when need to parse and update courses view and data.
     *
     * @param searchModel model with courses data in it.
     * @param is_popular
     * @param is_public
     * @param language
     * @param query
     * @param type
     */
    public void receiveSearchModel(SearchModel searchModel, boolean is_popular, boolean is_public, String language, String query, String type) {
        updateView((ArrayList<SearchModel.SearchResult>) searchModel.getSearchResults());
        mPage = 1;
        mLanguage = language;
        mQuery = query;
    }

    /**
     * Called when need to parse and update courses view and data.
     *
     * @param searchModel model with courses data in it.
     */
    public void receiveSearchModel(SearchModel searchModel, int page, boolean is_add) {
        if (searchModel == null) return;
        if (is_add) {
            addView((ArrayList<SearchModel.SearchResult>) searchModel.getSearchResults());
        } else {
            updateView((ArrayList<SearchModel.SearchResult>) searchModel.getSearchResults());
        }
        mPage = page;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maincontent, container, false);
        // Get reference of widget from XML layout
        coursesList = root.findViewById(R.id.Fragment_MainContent_ListView);
        // Create loader
        Runnable runnable;
        if (enableLoading) {
            runnable = () -> {
                ProgressBar progressBar = getActivity().findViewById(R.id.ActivityMain_Toolbar_ProgressBar);
                progressBar.setVisibility(View.VISIBLE);
                Callback<SearchModel> callback = new Callback<SearchModel>() {
                    @Override
                    public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                        SearchModel model = response.body();
                        progressBar.setVisibility(View.INVISIBLE);
                        receiveSearchModel(model, mPage, true);
                    }

                    @Override
                    public void onFailure(Call<SearchModel> call, Throwable t) {
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
                        t.printStackTrace();
                    }
                };
                mPage++;
                Call<SearchModel> call = StepicInternship
                        .getApi()
                        .getSearchResult(true, true, mLanguage, mQuery, "course", mPage);

                call.enqueue(callback);
            };
        } else {
            runnable = () -> {
            };
        }
        if (savedInstanceState != null){
            coursesDataList = (ArrayList<SearchModel.SearchResult>) savedInstanceState.getSerializable(COURSES_DATA);
        }
        // Create an ArrayAdapter from List
        mAdapter = new CourseArrayAdapter(getContext(), coursesDataList, runnable);
        // DataBind ListView with items from ArrayAdapter
        coursesList.setAdapter(mAdapter);
        if (savedInstanceState == null){
            start();
        }
        coursesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), ActivityCourse.class);
            intent.putExtra(ActivityCourse.EXTRA_RAW_COURSE, coursesDataList.get(position));
            startActivity(intent);
        });

        coursesList.setOnItemLongClickListener((parent, view, position, id) -> showPopup(view, position));
        return root;
    }

    private boolean showPopup(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), v);
        popupMenu.inflate(R.menu.fragment_maincontent_popupmenu);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.AddToFavorites:
                    addToFavorites(position);
                    return true;
                default:
                    return false;
            }
        });
        popupMenu.show();
        return true;
    }

    private void addToFavorites(int position) {
        Intent intent = new Intent(getActivity(), DownloadService.class);
        intent.putExtra(DownloadService.COURSE_EXTRA, coursesDataList.get(position));
        getActivity().startService(intent);
    }

    /**
     * Load courses by default to show.
     */
    private void start() {
        ProgressBar progressBar = getActivity().findViewById(R.id.ActivityMain_Toolbar_ProgressBar);
        progressBar.setVisibility(View.VISIBLE);
        Callback<SearchModel> callback = new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                SearchModel model = response.body();
                progressBar.setVisibility(View.INVISIBLE);
                receiveSearchModel(model, 1, false);
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.MULTIPLY);
                t.printStackTrace();
            }
        };

        Call<SearchModel> call = StepicInternship.getApi()
                .getSearchResult(
                        true,
                        true,
                        "ru",
                        "",
                        "course",
                        1
                );

        call.enqueue(callback);
    }

    /**
     * Update view to show new loaded courses.
     *
     * @param newResults list on new courses/
     */
    private void updateView(ArrayList<SearchModel.SearchResult> newResults) {
        coursesList.setScrollY(0);
        coursesDataList.clear();
        addView(newResults);
    }

    private void addView(ArrayList<SearchModel.SearchResult> newResults) {
        coursesDataList.addAll(newResults);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(COURSES_DATA, coursesDataList);
    }

    @Override
    public void onDestroyView() {
        coursesList = null;
        coursesDataList = null;
        mAdapter.onDestroy();
        mAdapter = null;
        super.onDestroyView();
    }
}
