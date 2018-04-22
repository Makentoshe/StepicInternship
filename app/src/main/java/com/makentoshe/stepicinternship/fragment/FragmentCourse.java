package com.makentoshe.stepicinternship.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.makentoshe.stepicinternship.common.model.SearchModel;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class FragmentCourse extends Fragment {

    private static final String COURSE_DATA = "mCourseData";

    public static FragmentCourse newInstance(SearchModel.SearchResult courseData) {
        FragmentCourse fragment = new FragmentCourse();
        Bundle args = new Bundle();
        args.putSerializable(COURSE_DATA, courseData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
}
