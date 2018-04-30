package com.makentoshe.stepicinternship.fragment;

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
import android.widget.VideoView;

import com.google.gson.Gson;
import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.StepicInternship;
import com.makentoshe.stepicinternship.common.model.StepModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Makentoshe on 26.04.2018.
 */

public class FragmentStep extends Fragment {

    private static final String STEP_ID = "stepID";
    private static final String LOCAL_STEP_ID = "localStepID";
    private static final String LOCAL_LESSON_DIR = "localLessonDir";

    private int stepID;
    private int localStepID;
    private VideoView mVideoView;
    private File lessonDir;

    public static FragmentStep newInstance(Integer stepId) {
        FragmentStep fragment = new FragmentStep();
        Bundle args = new Bundle();
        args.putInt(STEP_ID, stepId);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentStep newLocalInstance(int localID, File lessonDir) {
        FragmentStep fragment = new FragmentStep();
        Bundle args = new Bundle();
        args.putInt(LOCAL_STEP_ID, localID);
        args.putSerializable(LOCAL_LESSON_DIR, lessonDir);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stepID = getArguments().getInt(STEP_ID, -1);
        localStepID = getArguments().getInt(LOCAL_STEP_ID, -1);
        lessonDir = (File) getArguments().getSerializable(LOCAL_LESSON_DIR);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_step, container, false);

        if (stepID != -1) {
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
        } else {
            for (final File file : lessonDir.listFiles()){
                //get step data
                if (file.getName().split("\\.")[0].equals(String.valueOf(localStepID + 1))){
                    if (!file.getName().contains("stepV")){
                        try{
                            StepModel.Step step = new Gson().fromJson(new FileReader(file), StepModel.Step.class);
                            if (step.getBlock().getVideo() != null){
                                for (final File _file : lessonDir.listFiles()){
                                    if (_file.getName().contains("stepV")){
                                        setVideo(root, _file);
                                    }
                                }
                            }
                            if (step.getBlock().getText() != null){
                                setText(root, step.getBlock());
                            }
                        } catch (FileNotFoundException fnfe){
                            fnfe.printStackTrace();
                        }
                    }
                }
            }
        }

        return root;
    }

    private void fillLayout(View root, StepModel.Step step) {
        StepModel.Block block = step.getBlock();
        if (block.getVideo() != null) {
            //video
            setVideo(root, block);
        }
        if (block.getAnimation() != null) {
            //animation ?!?!?!
        }
        if (block.getText() != null) {
            setText(root, block);
        }
        if (block.getOptions() != null) {
            //code
        }

    }

    private void setVideo(View root, StepModel.Block block) {
        mVideoView = root.findViewById(R.id.FragmentStep_Video);
        mVideoView.setVisibility(View.INVISIBLE);
        Uri uri = Uri.parse(block.getVideo().getUrls().get(0).getUrl());
        mVideoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVisibility(View.VISIBLE);
    }

    private void setVideo(View root, File path) {
        mVideoView = root.findViewById(R.id.FragmentStep_Video);
        mVideoView.setVisibility(View.INVISIBLE);
        mVideoView.setVideoPath(path.getPath());
        MediaController mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(mVideoView);
        mVideoView.setMediaController(mediaController);
        mVideoView.setVisibility(View.VISIBLE);
    }

    private void setText(View root, StepModel.Block block) {
        TextView textView = root.findViewById(R.id.FragmentStep_Text);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(block.getText(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            textView.setText(Html.fromHtml(block.getText()));
        }
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mVideoView = null;
    }
}
