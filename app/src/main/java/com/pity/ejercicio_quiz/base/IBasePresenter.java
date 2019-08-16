package com.pity.ejercicio_quiz.base;

import android.os.Bundle;

public interface IBasePresenter<TView extends IBaseView> {

    void attachView(TView view);

    void dettachView();

    boolean isViewAtacched();

    void onCreate(Bundle savedState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();




}