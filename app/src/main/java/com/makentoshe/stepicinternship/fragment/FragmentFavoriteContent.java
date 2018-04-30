package com.makentoshe.stepicinternship.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.activity.ActivityCourse;
import com.makentoshe.stepicinternship.adapter.FavCourseArrayAdapter;
import com.makentoshe.stepicinternship.common.Favorites;
import com.makentoshe.stepicinternship.service.DownloadService;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class FragmentFavoriteContent extends Fragment {

    private ListView coursesList;
    private FavCourseArrayAdapter mAdapter;
    BroadcastReceiver br;

    public static FragmentFavoriteContent newInstance() {
        FragmentFavoriteContent fragment = new FragmentFavoriteContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null){
                    if (intent.getAction().equals(DownloadService.UPDATE_FAVORITES_MESSAGE)){
                        mAdapter.onDestroy();
                        mAdapter = new FavCourseArrayAdapter(getContext(), Favorites.getSavedCourseFolders());
                        coursesList.setAdapter(mAdapter);
                    }
                }
            }
        };
        getActivity().registerReceiver(br, new IntentFilter(DownloadService.UPDATE_FAVORITES_MESSAGE));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_favoritecontent, container, false);
        coursesList = root.findViewById(R.id.Fragment_FavoriteContent_ListView);
        mAdapter = new FavCourseArrayAdapter(getContext(), Favorites.getSavedCourseFolders());
        coursesList.setAdapter(mAdapter);
        coursesList.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getContext(), ActivityCourse.class);
            intent.putExtra(ActivityCourse.EXTRA_COURSE, mAdapter.getCourses().get(position));
            startActivity(intent);
        });
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(br);
        mAdapter.onDestroy();
        mAdapter = null;
        coursesList = null;
        br = null;
    }
}
