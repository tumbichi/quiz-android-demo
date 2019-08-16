package com.pity.ejercicio_quiz.data.interactors.implementation;

import com.pity.ejercicio_quiz.data.RealmHelper;
import com.pity.ejercicio_quiz.data.interactors.interfaces.IQuestionInteractor;
import com.pity.ejercicio_quiz.data.models.Option;
import com.pity.ejercicio_quiz.data.models.Question;

import java.util.ArrayList;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;

public class QuestionInteractor implements IQuestionInteractor {

    private static QuestionInteractor mInstance;
    private Realm realm;

    private QuestionInteractor() {
        realm = Realm.getDefaultInstance();
    }

    public static QuestionInteractor getInstance() {
        if (mInstance == null) mInstance = new QuestionInteractor();
        return mInstance;
    }

    public boolean isItQuizCreated() {
        return RealmHelper.questionID.get() > 0;
    }

    @Override
    public void initQuiz() {
        ArrayList<Question> quiz = new ArrayList<>();
        int pos = 0;

        quiz.add(new Question("¿Cuál es la capital de Honduras?"));
        quiz.get(pos).getOptions().add(new Option("Honduras", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Tegucigalpa", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Madrileña", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿En qué continente queda Madagascar?"));
        quiz.get(pos).getOptions().add(new Option("África", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("América", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Asia", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Cuál es la montaña más alta del mundo?"));
        quiz.get(pos).getOptions().add(new Option("Monte Everest", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Kilimanjaro", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Makalu", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Cuál es la capital de Brasil?"));
        quiz.get(pos).getOptions().add(new Option("Rio de Janeiro", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Brasilia", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("San Pablo", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Dónde quedan las Cataratas de Iguazú?"));
        quiz.get(pos).getOptions().add(new Option("Brasil y Uruguay", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Argentina y Brasil", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Argentina y Uruguay", false, quiz.get(pos)));
        pos++;


        quiz.add(new Question("¿En qué países se habla principalmente el idioma inglés?"));
        quiz.get(pos).getOptions().add(new Option("España", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Inglaterra", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("China", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("USA", true, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Qué países limitan con Francia?"));
        quiz.get(pos).getOptions().add(new Option("España", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Italia", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Rusia", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Finlandia", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Cuáles de estos países pertenecen al Mercosur?"));
        quiz.get(pos).getOptions().add(new Option("Argentina", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Brasil", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("México", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Panamá", false, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Cuáles de los siguientes países poseen\n" +
                "reconocidas pirámides?"));
        quiz.get(pos).getOptions().add(new Option("Egipto", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Italia", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Rusia", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("México", true, quiz.get(pos)));
        pos++;

        quiz.add(new Question("¿Cuáles ciudades fueron centro de grandes\n" +
                "civilizaciones de la antigüedad?"));
        quiz.get(pos).getOptions().add(new Option("Roma", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("New York", false, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Atenas", true, quiz.get(pos)));
        quiz.get(pos).getOptions().add(new Option("Buenos Aires", false, quiz.get(pos)));


        Iterator<Question> it = quiz.iterator();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                while (it.hasNext()){
                    realm.copyToRealmOrUpdate(it.next());
                }
            }
        });

    }

    @Override
    public RealmResults<Question> getQuiz () {
            return realm.where(Question.class).findAll();
    }

}

