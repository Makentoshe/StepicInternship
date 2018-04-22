package com.makentoshe.stepicinternship.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.adapter.AutoCompleteSearchAdapter;
import com.makentoshe.stepicinternship.view.DelayAutoCompleteTextView;

/**
 * Created by Makentoshe on 21.04.2018.
 */

public class FragmentMainContent extends Fragment {

    public static FragmentMainContent newInstance() {
        FragmentMainContent fragment = new FragmentMainContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_maincontent, container, false);
        DelayAutoCompleteTextView searchTextView = getActivity().findViewById(R.id.ActivityMain_Toolbar_SearchTextView);
        AutoCompleteSearchAdapter adapter = new AutoCompleteSearchAdapter(getContext());
        searchTextView.setAdapter(adapter);
        return root;
    }
}
