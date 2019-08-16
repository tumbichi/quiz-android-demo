package com.pity.ejercicio_quiz.data.interactors.interfaces;

import com.pity.ejercicio_quiz.data.models.Question;

import io.realm.RealmResults;

public interface IQuestionInteractor {

    void initQuiz();

    RealmResults<Question> getQuiz();
}
