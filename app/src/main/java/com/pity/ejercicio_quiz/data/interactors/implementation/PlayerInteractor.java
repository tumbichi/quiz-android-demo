package com.pity.ejercicio_quiz.data.interactors.implementation;

import android.util.Log;

import com.pity.ejercicio_quiz.data.interactors.interfaces.IPlayerInteractor;
import com.pity.ejercicio_quiz.data.models.Player;
import io.realm.Realm;


public class PlayerInteractor implements IPlayerInteractor {

    private Player mCurrentPlayer;
    private static PlayerInteractor mInstance;
    private Realm realm;

    private PlayerInteractor(){
        realm = Realm.getDefaultInstance();
    }

    public static PlayerInteractor getInstance(){
        if (mInstance == null) mInstance = new PlayerInteractor();
        return mInstance;
    }


    @Override
    public void initPlayer(String name, String email) {
        mCurrentPlayer = new Player(name, email);
    }



    @Override
    public void dettachCurrentPlayer() {
        Log.e("PlayerInteractor", "dettachCurrentPlayer");
        mCurrentPlayer = null;
    }

    @Override
    public Player getCurrentPlayer() throws NullPointerException {
        if (mCurrentPlayer == null) return null;
                                    return mCurrentPlayer;
    }

    @Override
    public void saveQuizResults() {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(mCurrentPlayer);
        realm.commitTransaction();
    }


}
