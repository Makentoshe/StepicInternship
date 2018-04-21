package com.makentoshe.stepicinternship.activity.logic;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Makentoshe on 21.04.2018.
 */

public abstract class ActivityLogic {

    protected abstract void onCreate(@Nullable Bundle savedInstanceState);

    protected abstract void onStart();

    protected abstract void onDestroy();

    protected abstract void onStop();

    protected abstract void onResume();

    protected abstract void onRestart();

}
