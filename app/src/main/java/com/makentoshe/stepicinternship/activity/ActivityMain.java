package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.makentoshe.stepicinternship.R;

public class ActivityMain extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private Toolbar createToolbar(){
        Toolbar toolbar = findViewById(R.id.activity_main_toolbar);
        return toolbar;
    }
}
