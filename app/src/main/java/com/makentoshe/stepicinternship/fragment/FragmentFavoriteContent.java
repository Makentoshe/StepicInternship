package com.makentoshe.stepicinternship.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makentoshe.stepicinternship.R;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class FragmentFavoriteContent extends Fragment {

    public static FragmentFavoriteContent newInstance() {
        FragmentFavoriteContent fragment = new FragmentFavoriteContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoritecontent, container, false);
        return root;
    }
}
