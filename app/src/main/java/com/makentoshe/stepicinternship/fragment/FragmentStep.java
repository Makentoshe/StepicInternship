package com.makentoshe.stepicinternship.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.activity.ActivityCourse;
import com.makentoshe.stepicinternship.adapter.CourseArrayAdapter;
import com.makentoshe.stepicinternship.common.StepicAPI;
import com.makentoshe.stepicinternship.common.model.SearchModel;
import com.makentoshe.stepicinternship.common.model.StepModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 26.04.2018.
 */

public class FragmentStep extends Fragment {

    private static final String STEP_ID = "stepID";

    private int stepID;
    private VideoView mVideoView;

    public static FragmentStep newInstance(Integer stepId) {
        FragmentStep fragment = new FragmentStep();
        Bundle args = new Bundle();
        args.putInt(STEP_ID, stepId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stepID = getArguments().getInt(STEP_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step, container, false);
        Call<StepModel> call = StepicInternship.getApi().getStepData(stepID);
        call.enqueue(new Callback<StepModel>() {
            @Override
            public void onResponse(Call<StepModel> call, Response<StepModel> response) {
                fillLayout(root, response.body().getSteps().get(0));
            }

            @Override
            public void onFailure(Call<StepModel> call, Throwable t) {

            }
        });
        return root;
    }

    private void fillLayout(View root, StepModel.Step step){
        StepModel.Block block = step.getBlock();
       if (block.getVideo() != null){
           //video
            setVideo(root, block);

       }
       if (block.getAnimation() != null){
           //animation ?!?!?!

       }
       if (block.getText() != null){
           setText(root, block);

       }
       if (block.getOptions() != null){
           //code

       }

    }

    private void setVideo(View root, StepModel.Block block){
        StepModel.Video video = block.getVideo();
        mVideoView = root.findViewById(R.id.FragmentStep_Video);
        mVideoView.setVisibility(View.INVISIBLE);
        Uri uri = Uri.parse(video.getUrls().get(0).getUrl());
        mVideoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVisibility(View.VISIBLE);
    }

    private void setText(View root, StepModel.Block block){
        TextView textView = root.findViewById(R.id.FragmentStep_Text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            textView.setText(Html.fromHtml(block.getText(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(block.getText()));
        }
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null){
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoView = null;
    }
}
