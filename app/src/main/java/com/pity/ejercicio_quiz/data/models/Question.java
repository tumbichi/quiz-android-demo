package com.pity.ejercicio_quiz.data.models;

import com.pity.ejercicio_quiz.data.RealmHelper;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Question extends RealmObject {
    @PrimaryKey private int id;
    @Required private String pregunta;
    private RealmList<Option> opciones;


    @Ignore
    public static final int CORRECTA = 2;
    public static final int NO_RESPONDIDA = -2;
    public static final int INCORRECTA = -1;


    public Question() {}

    public Question(String pregunta){
        this.id = RealmHelper.questionID.incrementAndGet();
        this.pregunta = pregunta;
        this.opciones = new RealmList<>();

    }

    public int getId() {
        return id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public RealmList<Option> getOptions() {
        return opciones;
    }


}
