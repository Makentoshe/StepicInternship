package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.makentoshe.stepicinternship.R;
import com.makentoshe.stepicinternship.activity.logic.DefaultActivityLogic;

public class ActivityMain extends Activity<ActivityMain.ActivityMainLogic> {

    @Override
    public ActivityMainLogic newLogicInstance() {
        return new ActivityMainLogic();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createToolbar();
        getLogic().onCreate(savedInstanceState);
    }

    private void createToolbar(){
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
    }

    static class ActivityMainLogic extends DefaultActivityLogic {

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }
    }
}
