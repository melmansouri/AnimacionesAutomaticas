package com.mel.animacionesautomaticas;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    //Animacion oneshot ideales para usar en splashscreen
    ImageView imageView;
    AnimationDrawable animationDrawable;

    //Animacion Crossfade mientras un elemento desaparece otro va apareciendo de forma gradual

    TextView contenido;
    ProgressBar progressBar;
    int duracionAnimacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        imageView = findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animacion_check);
        animationDrawable= (AnimationDrawable) imageView.getBackground();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
            }
        });

        contenido=findViewById(R.id.txtContenido);
        progressBar=findViewById(R.id.progressBar);
        duracionAnimacion=getResources().getInteger(android.R.integer.config_shortAnimTime);
        contenido.setVisibility(View.GONE);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crossFadeAnimation();
            }
        });
    }

    private void crossFadeAnimation(){
        //Mostramos progresivamente el texto
        contenido.setAlpha(0f);//Transparente
        contenido.setVisibility(View.VISIBLE);
        contenido.animate().alpha(1f).setDuration(duracionAnimacion);

        //Ocultamos progresivamente el progressBar
        progressBar.animate().alpha(0f).setDuration(duracionAnimacion).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void cambiarVisibilidad(View view) {
        if (textView.getVisibility() == View.VISIBLE) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }
}