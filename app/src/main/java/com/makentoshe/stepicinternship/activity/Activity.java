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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityLogic = (Logic) new DefaultActivityLogic();
        mActivityLogic.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mActivityLogic.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mActivityLogic.onDestroy();
        mActivityLogic = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mActivityLogic.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mActivityLogic.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mActivityLogic.onRestart();
    }
}
