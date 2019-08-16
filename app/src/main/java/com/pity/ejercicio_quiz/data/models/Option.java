package com.pity.ejercicio_quiz.data.models;

import com.pity.ejercicio_quiz.data.RealmHelper;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Option extends RealmObject {
    @PrimaryKey private int id;
    @Required private String opcion;

    private boolean isRight;
    private Question question;

    public Option(){}

    public Option(String opcion, boolean isRight, Question question){
        this.id = RealmHelper.optionID.incrementAndGet();
        this.opcion = opcion;
        this.isRight = isRight;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getOpcion() {
        return opcion;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isAnswerCorrect(boolean answer){
        return answer == isRight;
    }
}
