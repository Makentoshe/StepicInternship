package com.makentoshe.stepicinternship.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import java.lang.ref.SoftReference;

/**
 * Created by Makentoshe on 22.04.2018.
 */

public class DelayAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;

    private int mAutoCompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;

    private SoftReference<Handler> mHandler;
    private ProgressBar mProgressBar;

    private SoftReference<Handler> createHandler(){
       mHandler = new SoftReference<>(new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                DelayAutoCompleteTextView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
            }
        });
        return mHandler;
    }

    @Override
    public Handler getHandler() {
        if (mHandler == null){
            return createHandler().get();
        }
        Handler handler = mHandler.get();
        if (handler == null){
            return createHandler().get();
        }
        return handler;
    }

    public DelayAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setAutoCompleteDelay(int autoCompleteDelay) {
        this.mAutoCompleteDelay = autoCompleteDelay;
    }

    public void setAutoCompleteProgressBar(ProgressBar bar){
        mProgressBar = bar;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        if (mProgressBar != null){
            mProgressBar.setVisibility(VISIBLE);
        }
        //send message if the time has passed
        getHandler().removeMessages(MESSAGE_TEXT_CHANGED);
        getHandler().sendMessageDelayed(getHandler().obtainMessage(MESSAGE_TEXT_CHANGED, text), mAutoCompleteDelay);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onFilterComplete(int count) {
        if (mProgressBar != null){
            mProgressBar.setVisibility(INVISIBLE);
        }
        super.onFilterComplete(count);
    }

    public void releaseMemory(){
        getHandler().removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.clear();
        mHandler = null;
        mProgressBar = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && getAdapter() != null) {
            performFiltering(getText(), 0);
        }
    }

}
