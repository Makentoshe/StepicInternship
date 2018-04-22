package com.makentoshe.stepicinternship.activity.logic;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Все методы, начинающиеся с "_" вызываются перед вызовами методов UI класса(где-то в районе super).
 * Далее вызываются методы UI(их в этом классе нет)
 * Остальные методы вызываются после UI исключительно ручками.
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
