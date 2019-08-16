package com.pity.ejercicio_quiz.ui.quiz.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.pity.ejercicio_quiz.R;
import com.pity.ejercicio_quiz.base.BaseView;
import com.pity.ejercicio_quiz.ui.ResultActivity;
import com.pity.ejercicio_quiz.ui.form.implementation.FormView;
import com.pity.ejercicio_quiz.ui.quiz.interfaces.IQuizView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizView extends BaseView<QuizPresenter> implements IQuizView, View.OnClickListener {

    @BindView(R.id.quiz_view_text_view_question)
    TextView textViewQuestion;
    @BindView(R.id.quiz_view_button_siguiente)
    Button buttonNext;
    @BindView(R.id.quiz_view_checkbox_a)
    CheckBox checkBoxA;
    @BindView(R.id.quiz_view_checkbox_b)
    CheckBox checkBoxB;
    @BindView(R.id.quiz_view_checkbox_c)
    CheckBox checkBoxC;
    @BindView(R.id.quiz_view_checkbox_d)
    CheckBox checkBoxD;

    @Override
    public QuizPresenter createBasePresenter(Context context) {
        return new QuizPresenter(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        setViewListeners();
        mPresenter.attachView(this);
        mPresenter.onCreate(savedInstanceState);
    }

   /* @Override
    protected void onStop() {
        super.onStop();
        finish();
    }*/

    private void setViewListeners(){
        checkBoxA.setOnClickListener(this);
        checkBoxB.setOnClickListener(this);
        checkBoxC.setOnClickListener(this);
        checkBoxD.setOnClickListener(this);
    }

    public void onClickCheckBox(CheckBox checkBox) {
        if (checkBox.isChecked()) {
            onCheckedBox(checkBox);
        }else{
            onUncheckedBox(checkBox);
        }

    }

    private void onCheckedBox(CheckBox checkBox){
        checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_check_box_background));
        checkBox.setTextColor(getResources().getColor(R.color.icons));
    }

    private void onUncheckedBox(CheckBox checkBox){
        checkBox.setBackground(ContextCompat.getDrawable(this, R.drawable.custom_check_box_background_light));
        checkBox.setTextColor(getResources().getColor(R.color.primary_text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.quiz_view_checkbox_a:
                onClickCheckBox(checkBoxA);
                break;
            case R.id.quiz_view_checkbox_b:
                onClickCheckBox(checkBoxB);
                break;
            case R.id.quiz_view_checkbox_c:
                onClickCheckBox(checkBoxC);
                break;
            case R.id.quiz_view_checkbox_d:
                onClickCheckBox(checkBoxD);
                break;
        }
    }

    @OnClick (R.id.quiz_view_button_siguiente)
    public void onClickNext(View view){
        if (checkBoxD.getVisibility() == View.GONE){
            mPresenter.saveAnswer(checkBoxA.isChecked(), checkBoxB.isChecked(), checkBoxC.isChecked());
        }else{
            mPresenter.saveAnswer(checkBoxA.isChecked(), checkBoxB.isChecked(), checkBoxC.isChecked(), checkBoxD.isChecked());
        }


    }


    @Override
    public void setTextOfOptions(String optionA, String optionB, String optionC, String optionD) {
        checkBoxA.setText(optionA);
        checkBoxB.setText(optionB);
        checkBoxC.setText(optionC);
        checkBoxD.setText(optionD);
        checkBoxD.setVisibility(View.VISIBLE);


    }

    @Override
    public void setTextOfOptions(String optionA, String optionB, String optionC) {
        checkBoxA.setText(optionA);
        checkBoxB.setText(optionB);
        checkBoxC.setText(optionC);
        checkBoxD.setVisibility(View.GONE);
    }

    @Override
    public void setTextOfQuestion(String question) {
        textViewQuestion.setText(question);
    }


    @Override
    public void navigateToResult() {
        startActivity(new Intent(this, ResultActivity.class));
        finish();
    }

    @Override
    public void cleanCheckBoxs() {
        if (checkBoxA.isChecked()) {
            checkBoxA.setChecked(false);
            onUncheckedBox(checkBoxA);
        }
        if (checkBoxB.isChecked()) {
            checkBoxB.setChecked(false);
            onUncheckedBox(checkBoxB);
        }
        if (checkBoxC.isChecked()) {
            checkBoxC.setChecked(false);
            onUncheckedBox(checkBoxC);
        }
        if (checkBoxD.isChecked()) {
            checkBoxD.setChecked(false);
            onUncheckedBox(checkBoxD);
        }
    }
}
