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
    public void _onCreate(@Nullable Bundle savedInstanceState) {
        log.info("call onCreate before UI");
    }

    @Override
    public void _onStart() {
        log.info("call onStart before UI");
    }

    @Override
    public void _onDestroy() {
        log.info("call onDestroy before UI");
    }

    @Override
    public void _onStop() {
        log.info("call onStop before UI");
    }

    @Override
    public void _onResume() {
        log.info("call onResume before UI");
    }

    @Override
    public void _onRestart() {
        log.info("call onRestart before UI");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        log.info("call onCreate after UI");
    }

    @Override
    public void onStart() {
        log.info("call onStart after UI");
    }

    @Override
    public void onDestroy() {
        log.info("call onDestroy after UI");
    }

    @Override
    public void onStop() {
        log.info("call onStop after UI");
    }

    @Override
    public void onResume() {
        log.info("call onResume after UI");
    }

    @Override
    public void onRestart() {
        log.info("call onRestart after UI");
    }

}
