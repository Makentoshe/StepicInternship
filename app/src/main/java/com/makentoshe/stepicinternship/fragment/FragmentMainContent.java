package com.makentoshe.stepicinternship.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.adapter.CourseArrayAdapter;
import com.makentoshe.stepicinternship.common.model.SearchModel;

import java.util.ArrayList;

/**
 * Created by Makentoshe on 21.04.2018.
 */
public class FragmentMainContent extends Fragment {

    private ListView coursesList;
    private ArrayList<SearchModel.SearchResult> coursesDataList = new ArrayList<>();
    private CourseArrayAdapter mAdapter;

    public static FragmentMainContent newInstance() {
        FragmentMainContent fragment = new FragmentMainContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void receiveSearchModel(SearchModel searchModel){
        updateView((ArrayList<SearchModel.SearchResult>) searchModel.getSearchResults());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maincontent, container, false);
        // Get reference of widget from XML layout
        coursesList = root.findViewById(R.id.Fragment_MainContent_ListView);
        // Create an ArrayAdapter from List
        mAdapter = new CourseArrayAdapter(getContext(), coursesDataList);
        // DataBind ListView with items from ArrayAdapter
        coursesList.setAdapter(mAdapter);
        return root;
    }



    private void updateView(ArrayList<SearchModel.SearchResult> newResults){
        coursesDataList.clear();
        coursesDataList.addAll(newResults);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        coursesList = null;
        coursesDataList = null;
        mAdapter = null;
        super.onDestroyView();
    }
}
