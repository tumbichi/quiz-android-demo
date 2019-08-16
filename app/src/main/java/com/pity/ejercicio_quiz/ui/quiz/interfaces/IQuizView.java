package com.pity.ejercicio_quiz.ui.quiz.interfaces;

import com.pity.ejercicio_quiz.base.IBaseView;

public interface IQuizView extends IBaseView {
    void setTextOfOptions(String optionA, String optionB, String optionC, String optionD);
    void setTextOfOptions(String optionA, String optionB, String optionC);
    void setTextOfQuestion(String question);
    void navigateToResult();
    void cleanCheckBoxs();
}
