package com.pity.ejercicio_quiz.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseView<TPresenter extends IBasePresenter> extends AppCompatActivity
        implements IBaseView {

    public TPresenter mPresenter;


    public abstract TPresenter createBasePresenter(final Context context);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPresenter == null) {
            mPresenter = createBasePresenter(this);
        }


    }

}