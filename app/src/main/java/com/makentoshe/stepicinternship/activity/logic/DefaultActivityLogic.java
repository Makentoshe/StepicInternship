package com.makentoshe.stepicinternship.activity.logic;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.logging.Logger;

/**
 * Created by Makentoshe on 21.04.2018.
 */

public class DefaultActivityLogic extends ActivityLogic {

    private Logger log = Logger.getLogger(DefaultActivityLogic.class.getName());

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        log.info("call onCreate");
    }

    @Override
    public void onStart() {
        log.info("call onStart");
    }

    @Override
    public void onDestroy() {
        log.info("call onDestroy");
    }

    @Override
    public void onStop() {
        log.info("call onStop");
    }

    @Override
    public void onResume() {
        log.info("call onResume");
    }

    @Override
    public void onRestart() {
        log.info("call onRestart");
    }
}
