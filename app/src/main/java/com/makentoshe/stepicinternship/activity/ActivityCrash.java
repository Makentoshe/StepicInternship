package com.makentoshe.stepicinternship.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.makentoshe.stepicinternship.R;

/**
 * Created by Makentoshe on 01.05.2018.
 */

public class ActivityCrash extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crash);

        ((TextView)findViewById(R.id.ActivityCrash_log))
                .setText(getIntent().getExtras().getString("error"));
    }

}
