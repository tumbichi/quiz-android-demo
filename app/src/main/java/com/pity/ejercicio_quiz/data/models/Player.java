package com.pity.ejercicio_quiz.data.models;

import com.pity.ejercicio_quiz.data.RealmHelper;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Player extends RealmObject {

    @PrimaryKey private int id;
    @Required private String nombre;
    @Required private String email;
    private int puntaje;
    RealmList<Question> quiz;

    public Player(){
    }

    public Player(String nombre, String email){
        this.id = RealmHelper.userID.incrementAndGet();
        this.nombre = nombre;
        this.email = email;
        this.puntaje = 0;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setQuiz(RealmList<Question> preguntas) {
        this.quiz = preguntas;
    }

    public void addAnswerCorrectPoints(){
        puntaje += Question.CORRECTA;
    }
    public void addNotAnsweredPoints(){
        puntaje += Question.NO_RESPONDIDA;
    }
    public void addAnswerIncorrect(){
        puntaje += Question.INCORRECTA;
    }
}
