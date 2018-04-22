package com.makentoshe.stepicinternship.activity.logic;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * All methods, which starts with "_" calls before UI methods (in the end of the super method)
 * Then calls UI methods
 * Another methods calls after UI solely by handles.
 */
public abstract class ActivityLogic {

    protected abstract void _onCreate(@Nullable Bundle savedInstanceState);

    protected abstract void _onStart();

    protected abstract void _onDestroy();

    protected abstract void _onStop();

    protected abstract void _onResume();

    protected abstract void _onRestart();

    protected abstract void onCreate(@Nullable Bundle savedInstanceState);

    protected void onStart(){}

    protected  void onDestroy(){}

    protected  void onStop(){}

    protected  void onResume(){}

    protected  void onRestart(){}

}
