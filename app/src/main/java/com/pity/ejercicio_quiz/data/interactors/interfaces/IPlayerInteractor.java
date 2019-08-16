package com.pity.ejercicio_quiz.data.interactors.interfaces;

import com.pity.ejercicio_quiz.data.models.Player;

public interface IPlayerInteractor {

    void initPlayer(String name, String email);
    void dettachCurrentPlayer();
    Player getCurrentPlayer();
    void saveQuizResults();
}
