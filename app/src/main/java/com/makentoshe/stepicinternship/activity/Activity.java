package com.makentoshe.stepicinternship.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.makentoshe.stepicinternship.common.ExceptionHandler;

/**
 * Created by Makentoshe on 01.05.2018.
 */

@SuppressLint("Registered")
public class Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }
}
