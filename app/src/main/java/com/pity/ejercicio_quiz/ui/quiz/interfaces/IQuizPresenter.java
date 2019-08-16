package com.pity.ejercicio_quiz.ui.quiz.interfaces;

import com.pity.ejercicio_quiz.base.IBasePresenter;

public interface IQuizPresenter extends IBasePresenter<IQuizView> {

    void saveAnswer(boolean a, boolean b, boolean c, boolean d);
    void saveAnswer(boolean a, boolean b, boolean c);
}
