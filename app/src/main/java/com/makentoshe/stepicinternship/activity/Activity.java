package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;

/**
 * Created by Makentoshe on 21.04.2018.
 */

public abstract class Activity<Logic extends DefaultActivityLogic> extends AppCompatActivity {

    private Logic mActivityLogic;

    public abstract Logic newLogicInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLogic = newLogicInstance();
        mActivityLogic._onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityLogic._onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityLogic._onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityLogic._onStop();
    }

    @Override
    public boolean isFinishing() {
        mActivityLogic = null;
        return super.isFinishing();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityLogic._onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mActivityLogic._onRestart();
    }

    public Logic getLogic(){
        return mActivityLogic;
    }
}
