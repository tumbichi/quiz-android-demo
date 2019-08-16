package com.pity.ejercicio_quiz.ui.form.interfaces;

import com.pity.ejercicio_quiz.base.IBasePresenter;

public interface IFormPresenter extends IBasePresenter<IFormView> {

    void initMatch(String nombre, String email);
}
