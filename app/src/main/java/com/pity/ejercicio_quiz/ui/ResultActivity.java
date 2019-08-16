package com.pity.ejercicio_quiz.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.pity.ejercicio_quiz.R;
import com.pity.ejercicio_quiz.data.interactors.implementation.PlayerInteractor;
import com.pity.ejercicio_quiz.ui.form.implementation.FormView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {


    @BindView(R.id.result_view_text_view_username)
    TextView textViewUsername;
    @BindView(R.id.result_view_text_view_score)
    TextView textViewScore;
    @BindView(R.id.result_view_image_view_score)
    ImageView imageViewScore;
    @BindView(R.id.result_view_button_rematch)
    Button buttonRematch;

    PlayerInteractor mPlayerInteractor = PlayerInteractor.getInstance();

    @OnClick(R.id.result_view_button_rematch)
    public void onClickRematch(View view){
        mPlayerInteractor.dettachCurrentPlayer();
        startActivity(new Intent(this, FormView.class));
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);
        ButterKnife.bind(this);

        showResults(mPlayerInteractor.getCurrentPlayer().getNombre(), mPlayerInteractor.getCurrentPlayer().getPuntaje());
    }

    private void showResults(String nombre, int puntaje) {
        String result = puntaje + "";

        textViewUsername.setText(nombre);
        textViewScore.setText(result);

        setImageWithThis(puntaje);



    }

    private void setImageWithThis(int puntaje) {
        if (puntaje <= -15) {
            Glide.with(this)
                    .load(R.drawable.cinco)
                    .fitCenter()
                    .centerCrop()
                    .into(imageViewScore);
        } else if (puntaje > -15 && puntaje <= -5) {
            Glide.with(this)
                    .load(R.drawable.cuatro)
                    .fitCenter()
                    .centerCrop()
                    .into(imageViewScore);
        } else if (puntaje > -5 && puntaje <= 5) {
            Glide.with(this)
                    .load(R.drawable.tres)
                    .fitCenter()
                    .centerCrop()
                    .into(imageViewScore);
        } else if (puntaje > 5 && puntaje <= 15) {
            Glide.with(this)
                    .load(R.drawable.dos)
                    .fitCenter()
                    .centerCrop()
                    .into(imageViewScore);
        } else if (puntaje > 15) {
            Glide.with(this)
                    .load(R.drawable.uno)
                    .fitCenter()
                    .centerCrop()
                    .into(imageViewScore);
        }
    }
}
