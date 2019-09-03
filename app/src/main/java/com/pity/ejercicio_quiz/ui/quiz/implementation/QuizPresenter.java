package com.pity.ejercicio_quiz.ui.quiz.implementation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.pity.ejercicio_quiz.base.BasePresenter;
import com.pity.ejercicio_quiz.data.interactors.implementation.PlayerInteractor;
import com.pity.ejercicio_quiz.data.interactors.implementation.QuestionInteractor;
import com.pity.ejercicio_quiz.data.models.Option;
import com.pity.ejercicio_quiz.data.models.Question;
import com.pity.ejercicio_quiz.ui.quiz.interfaces.IQuizPresenter;
import com.pity.ejercicio_quiz.ui.quiz.interfaces.IQuizView;

import io.realm.RealmList;
import io.realm.RealmResults;

public class QuizPresenter extends BasePresenter<IQuizView> implements IQuizPresenter {

    private QuestionInteractor questionInteractor;
    private PlayerInteractor playerInteractor;
    private RealmResults<Question> currentQuiz;
    private static int CURRENT_QUESTION_ID;
    private int LAST_QUESTION_ID;

    public QuizPresenter(Context context) {
        super(context);
        questionInteractor = QuestionInteractor.getInstance();
        playerInteractor = PlayerInteractor.getInstance();
    }

    @Override
    public void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        if (!questionInteractor.isItQuizCreated()){
            Log.e("QuizPresenter", "Crear Quiz");
            questionInteractor.initQuiz();
        }

        startQuiz();


    }

    private void startQuiz(){

        currentQuiz = questionInteractor.getQuiz();

        if (currentQuiz != null) {
            CURRENT_QUESTION_ID = 0;
            LAST_QUESTION_ID = currentQuiz.size();
            RealmList<Option> options = currentQuiz.get(CURRENT_QUESTION_ID).getOptions();
            if (options.size() > 0) Log.e("QuizPresenter",
                    options.get(0).getId() + " " + options.get(0).getOpcion() + options.get(0).isRight());


        }

        playThisQuestion(CURRENT_QUESTION_ID);
    }

    @SuppressLint("LongLogTag")
    private void playThisQuestion(int idQuestion){
        Question currentQuestion = currentQuiz.get(idQuestion);
        mView.setTextOfQuestionNumber(CURRENT_QUESTION_ID + 1);
        Log.e("QuizPresenter:playThisQuestion() ", "idQuestion=" + idQuestion);
        if (currentQuestion != null) {
            Log.e("QuizPresenter", "id: "+ currentQuestion.getId() + " " + currentQuestion.getPregunta());
            final int NUMBER_OF_OPTIONS = currentQuiz.get(idQuestion).getOptions().size();
            String[] currentOptions = getOptionsNames(currentQuestion, NUMBER_OF_OPTIONS);

            if (NUMBER_OF_OPTIONS == 3) {
                mView.setTextOfOptions(currentOptions[0], currentOptions[1], currentOptions[2]);
            } else {
                mView.setTextOfOptions(currentOptions[0], currentOptions[1], currentOptions[2], currentOptions[3]);
            }

            mView.setTextOfQuestion(currentQuiz.get(idQuestion).getPregunta());
        }
    }

    @Override
    public void saveAnswer(boolean a, boolean b, boolean c, boolean d) {
        Question currentQuestion = currentQuiz.get(CURRENT_QUESTION_ID);
        RealmList<Option> currentOptions = currentQuestion.getOptions();
        boolean[] correctAnswers = {
                currentOptions.get(0).isAnswerCorrect(a),
                currentOptions.get(1).isAnswerCorrect(b),
                currentOptions.get(2).isAnswerCorrect(c),
                currentOptions.get(3).isAnswerCorrect(d)};
        boolean[] answers = {a, b, c, d};

        countPoints(correctAnswers, answers);

        mView.cleanCheckBoxs();

        nextQuestion();


    }

    @Override
    public void saveAnswer(boolean a, boolean b, boolean c) {

        Question currentQuestion = currentQuiz.get(CURRENT_QUESTION_ID);
        RealmList<Option> currentOptions = currentQuestion.getOptions();
        boolean[] correctAnswers = {
                currentOptions.get(0).isAnswerCorrect(a),
                currentOptions.get(1).isAnswerCorrect(b),
                currentOptions.get(2).isAnswerCorrect(c)};
        boolean[] answers = {a, b, c};

        countPoints(correctAnswers, answers);

        mView.cleanCheckBoxs();

        nextQuestion();


    }

    private void nextQuestion(){
        CURRENT_QUESTION_ID++;
        if (CURRENT_QUESTION_ID >= LAST_QUESTION_ID) {
            finishQuiz();
        }else playThisQuestion(CURRENT_QUESTION_ID);
    }

    private void finishQuiz(){
        RealmList<Question> questions = new RealmList<>();
        questions.addAll(currentQuiz.subList(0, currentQuiz.size()));

        playerInteractor.getCurrentPlayer().setQuiz(questions);
        playerInteractor.saveQuizResults();
        mView.navigateToResult();

    }

    private void countPoints(boolean[] correctAnswers, boolean[] answers){
        if (correctAnswers.length == 3) {
            if (correctAnswers[0] && correctAnswers[1] && correctAnswers[2]) {
                playerInteractor.getCurrentPlayer().addAnswerCorrectPoints();
                Log.e("countPoint", "+2");
            } else {
                if (!answers[0] && !answers[1] && !answers[2] ) {
                    playerInteractor.getCurrentPlayer().addNotAnsweredPoints();
                    Log.e("countPoint", "-2");
                } else {
                    if (correctAnswers[0] || correctAnswers[1] || correctAnswers[2])
                    playerInteractor.getCurrentPlayer().addAnswerIncorrect();
                    Log.e("countPoint", "-1");
                }
            }
        }else {
            if (correctAnswers.length == 4) {
                if (correctAnswers[0] && correctAnswers[1]
                        && correctAnswers[2] && correctAnswers[3]) {
                    playerInteractor.getCurrentPlayer().addAnswerCorrectPoints();
                    Log.e("countPoint", "+2");
                } else {
                    if (!answers[0] && !answers[1] && !answers[2] && !answers[3]) {
                        playerInteractor.getCurrentPlayer().addNotAnsweredPoints();
                        Log.e("countPoint", "-2");
                    } else {
                        if (correctAnswers[0] || correctAnswers[1]
                                || correctAnswers[2] || correctAnswers[3])
                            playerInteractor.getCurrentPlayer().addAnswerIncorrect();
                        Log.e("countPoint", "-1");
                    }
                }
            }
        }
    }

    private String[] getOptionsNames(Question question, int NUMBER_OF_OPTIONS){
        String[] currentOptionsNames = new String[NUMBER_OF_OPTIONS];
        RealmList<Option> currentOptions = question.getOptions();
        for (int j = 0; j < NUMBER_OF_OPTIONS; j++) {
            currentOptionsNames[j] = currentOptions.get(j).getOpcion();
        }
        return currentOptionsNames;
    }

}
