package com.pity.ejercicio_quiz.ui.form.implementation;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;

import com.pity.ejercicio_quiz.base.BasePresenter;
import com.pity.ejercicio_quiz.data.interactors.implementation.PlayerInteractor;
import com.pity.ejercicio_quiz.ui.form.interfaces.IFormPresenter;
import com.pity.ejercicio_quiz.ui.form.interfaces.IFormView;

import java.util.regex.Pattern;

public class FormPresenter extends BasePresenter<IFormView> implements IFormPresenter {

    private PlayerInteractor playerInteractor;

    public FormPresenter(Context context) {
        super(context);
        playerInteractor = PlayerInteractor.getInstance();
    }


    @Override
    public void initMatch(String nombre, String email) {
        if (!validarCampos(nombre, email)) return;
        playerInteractor.initPlayer(nombre, email);
        mView.navigateToQuiz();
    }

    private boolean validarCampos(String nombre, String email){
        if (TextUtils.isEmpty(email)) {
            mView.toast("Debe ingresar un email");
            return false;
        }
        if (!isAEmailValidated(email)) {
            mView.toast("Debe ingresar un email valido");
            return false;
        }
        if (TextUtils.isEmpty(nombre)) {
            mView.toast("Debe ingresar un nombre");
            return false;
        }
        return true;
    }

    private boolean isAEmailValidated(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
