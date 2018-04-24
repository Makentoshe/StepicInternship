package com.makentoshe.stepicinternship.activity.logic;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.logging.Logger;

/**
 * Default ActivityLogic implementation
 */
public class DefaultActivityLogic extends ActivityLogic {

    private Logger log = Logger.getLogger(DefaultActivityLogic.class.getName());

    @Override
    public void _onCreate(@Nullable Bundle savedInstanceState) {
        log.info(this.getClass().getSimpleName() + " call onCreate before UI");
    }

    @Override
    public void _onStart() {
        log.info(this.getClass().getSimpleName() + " call onStart before UI");
    }

    @Override
    public void _onDestroy() {
        log.info(this.getClass().getSimpleName() + " call onDestroy before UI");
    }

    @Override
    public void _onStop() {
        log.info(this.getClass().getSimpleName() + " call onStop before UI");
    }

    @Override
    public void _onResume() {
        log.info(this.getClass().getSimpleName() + " call onResume before UI");
    }

    @Override
    public void _onRestart() {
        log.info(this.getClass().getSimpleName() + " call onRestart before UI");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        log.info(this.getClass().getSimpleName() +  " call onCreate after UI");
    }

    @Override
    public void onStart() {
        log.info(this.getClass().getSimpleName() +  " call onStart after UI");
    }

    @Override
    public void onDestroy() {
        log.info(this.getClass().getSimpleName() + " call onDestroy after UI");
    }

    @Override
    public void onStop() {
        log.info(this.getClass().getSimpleName() + " call onStop after UI");
    }

    @Override
    public void onResume() {
        log.info(this.getClass().getSimpleName() + " call onResume after UI");
    }

    @Override
    public void onRestart() {
        log.info(this.getClass().getSimpleName() + " call onRestart after UI");
    }

}
