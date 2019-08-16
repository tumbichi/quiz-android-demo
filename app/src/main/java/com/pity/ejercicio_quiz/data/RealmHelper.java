package com.pity.ejercicio_quiz.data;

import android.app.Application;

import com.pity.ejercicio_quiz.data.models.Option;
import com.pity.ejercicio_quiz.data.models.Player;
import com.pity.ejercicio_quiz.data.models.Question;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmHelper extends Application {

    public static AtomicInteger userID = new AtomicInteger();
    public static AtomicInteger questionID = new AtomicInteger();
    public static AtomicInteger optionID = new AtomicInteger();

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
        setUpRealmConfig();

        Realm realm = Realm.getDefaultInstance();
        userID = getIdByTable(realm, Player.class);
        questionID = getIdByTable(realm, Question.class);
        optionID = getIdByTable(realm, Option.class);
        realm.close();


    }

    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .name("quiz.realm")
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);

    }

    private <T extends RealmObject> AtomicInteger getIdByTable (Realm realm, Class<T> anyClass){
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}

