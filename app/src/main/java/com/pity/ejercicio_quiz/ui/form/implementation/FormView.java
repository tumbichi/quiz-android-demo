package com.pity.ejercicio_quiz.ui.form.implementation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pity.ejercicio_quiz.R;
import com.pity.ejercicio_quiz.base.BaseView;
import com.pity.ejercicio_quiz.ui.form.interfaces.IFormView;
import com.pity.ejercicio_quiz.ui.quiz.implementation.QuizView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormView extends BaseView<FormPresenter> implements IFormView {


    @BindView(R.id.form_view_edit_text_nombre)
    EditText editTextName;

    @BindView(R.id.form_view_edit_text_email)
    EditText editTextEmail;
    @BindView(R.id.form_view_button_siguiente)
    Button buttonNext;

    @Override
    public FormPresenter createBasePresenter(Context context) {
        return new FormPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ButterKnife.bind(this);
        mPresenter.attachView(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @OnClick(R.id.form_view_button_siguiente)
    public void onClickNext(View view){
        mPresenter.initMatch(editTextName.getText().toString() , editTextEmail.getText().toString());
    }


    @Override
    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToQuiz() {
        startActivity(new Intent(this, QuizView.class));
        finish();
    }
}
