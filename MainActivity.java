package com.example.franly.tresenraya;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int jugador;
    private int[] CASILLAS;
    private Partida partida;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CASILLAS = new int[9];

        CASILLAS[0] = R.id.a00;
        CASILLAS[1] = R.id.a01;
        CASILLAS[2] = R.id.a02;
        CASILLAS[3] = R.id.a10;
        CASILLAS[4] = R.id.a11;
        CASILLAS[5] = R.id.a12;
        CASILLAS[6] = R.id.a20;
        CASILLAS[7] = R.id.a21;
        CASILLAS[8] = R.id.a22;

        ImageView img;

        for (int i = 0; i < CASILLAS.length; i++) {
            img = findViewById(CASILLAS[i]);
            img.setEnabled(false);
        }
    }

    /**
     * Reinicia el juego
     *
     * @param view
     */
    public void aJugar(View view) {

        ImageView img;
        int id = 0;
        int dificultad = 0;
        cont = 0;

        for (int i = 0; i < CASILLAS.length; i++) {
            img = findViewById(CASILLAS[i]);
            img.setImageResource(R.drawable.casilla);
            img.setEnabled(true);
            img.setAlpha(255);
        }

        jugador = 1;

        if (view.getId() == R.id.btn_jugador2) {
            jugador = 2;
        }

        RadioGroup radioGroup = findViewById(R.id.radio_group);

        id = radioGroup.getCheckedRadioButtonId();

        if (id == R.id.rbtn_normal) {
            dificultad = 1;
        } else if (id == R.id.rbtn_insane) {
            dificultad = 2;
        }

        partida = new Partida(dificultad);
        setBotones(false);

    }


    public void toque(View view) {
        int casilla = 0;

        for (int i = 0; i < CASILLAS.length; i++) {
            if (CASILLAS[i] == view.getId()) {
                casilla = i;
                break;
            }
        }

        boolean ganador = false;
        if (cont >= 3) {
            marca(casilla);
            ganador = comprobarGanador(ganador);

            if (partida.getArrayListCasillas().size() > 1 && (ganador == false)) {
                casilla = partida.ia();
                marca(casilla);
                ganador = comprobarGanador(ganador);
            }

        } else {
            marca(casilla);
            if (partida.getArrayListCasillas().size() > 1) {
                casilla = partida.ia();
                marca(casilla);
            }
        }

        if (cont >= 9 && (ganador == false)) {
            Context context = getApplicationContext();
            CharSequence text = "¡¡Empate!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast1 = Toast.makeText(context, text, duration);
            toast1.setGravity(Gravity.CENTER, 0, 0);
            toast1.show();

            reiniciar(true);

        }
        //if (partida.getArrayListCasillas().size() > 1) {
        //    casilla = partida.ia();
        //     marca(casilla);
        //  }


    }

    /**
     * Marca la casilla que ha elegido el jugador
     *
     * @param casilla
     */
    private void marca(int casilla) {
        ImageView img = findViewById(CASILLAS[casilla]);
        img.setEnabled(false);

        partida.cambioDeTurno();
        if (partida.getArrayListCasillas().size() > 0) {
            if (partida.getJugador() == 1) {
                img.setImageResource(R.drawable.circulo);
                img.setTag(R.drawable.circulo);
                partida.addPosicion(casilla);
            } else {
                img.setImageResource(R.drawable.aspa);
                img.setTag(R.drawable.aspa);
            }

            partida.removeArrayListCasillas(casilla);
            cont++;
        }

    }

    private boolean comprobarGanador(boolean ganador) {
        int j = 0;
        int var = 0;
        String gana = "";

        ImageView img1;
        ImageView img2;
        ImageView img3;

        Log.d("W", "comprobarGanador: ¡¡Entra!!");

        for (int i = 0; i < 9; i += 3) {

            img1 = findViewById(CASILLAS[i]);
            img2 = findViewById(CASILLAS[i + 1]);
            img3 = findViewById(CASILLAS[i + 2]);

            if (img1.getTag() != null && img2.getTag() != null && img3.getTag() != null) {
                if ((img1.getTag().toString().equals(img2.getTag().toString())) && (img2.getTag().toString().equals(img3.getTag().toString()))) {
                    ganador = true;
                    var = 1;
                    gana = img1.getTag().toString();
                }
            }

            img1 = findViewById(CASILLAS[j]);
            img2 = findViewById(CASILLAS[j + 3]);
            img3 = findViewById(CASILLAS[j + 6]);
            j++;

            if (img1.getTag() != null && img2.getTag() != null && img3.getTag() != null) {
                if ((img1.getTag().toString().equals(img2.getTag().toString())) && (img2.getTag().toString().equals(img3.getTag().toString()))) {
                    ganador = true;
                    var = 2;
                    gana = img1.getTag().toString();
                }
            }
        }

        img1 = findViewById(CASILLAS[0]);
        img2 = findViewById(CASILLAS[4]);
        img3 = findViewById(CASILLAS[8]);

        if (img1.getTag() != null && img2.getTag() != null && img3.getTag() != null) {
            if ((img1.getTag().toString().equals(img2.getTag().toString())) && (img1.getTag().toString().equals(img3.getTag().toString()))) {
                ganador = true;
                var = 3;
                gana = img1.getTag().toString();
            }
        }

        img1 = findViewById(CASILLAS[2]);
        img2 = findViewById(CASILLAS[4]);
        img3 = findViewById(CASILLAS[6]);

        if (img1.getTag() != null && img2.getTag() != null && img3.getTag() != null) {
            if ((img1.getTag().toString().equals(img2.getTag().toString())) && (img2.getTag().toString().equals(img3.getTag().toString()))) {
                ganador = true;
                var = 4;
                gana = img1.getTag().toString();
            }
        }


        Log.d("W", "Ganador: " + ganador);

        if (ganador == true) {
            Context context = getApplicationContext();
            CharSequence text = "";
            int duration = Toast.LENGTH_SHORT;
            int circulo = R.drawable.circulo;
            int aspa = R.drawable.aspa;

            if (gana.equals(Integer.toString(circulo))) {
                Log.d("W", "¡¡Hay Ganador!! " + var + " !!Ganan los Círculos!!");
                text = "¡¡Hay Ganador!! " + var + " !!Ganan los Círculos!! " + gana;
                Toast toast1 = Toast.makeText(context, text, duration);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();

            } else if (gana.equals(Integer.toString(aspa))) {
                Log.d("W", "¡¡Hay Ganador!! " + var + " !!Ganan las Aspas!!");
                text = "¡¡Hay Ganador!! " + var + " !!Ganan las Aspas!! " + gana;
                Toast toast1 = Toast.makeText(context, text, duration);
                toast1.setGravity(Gravity.CENTER, 0, 0);
                toast1.show();
            }

            reiniciar(ganador);
            cont = 0;
        }

        return ganador;

    }

    public void setBotones(boolean valor) {
        Boolean clickable = false;
        Button btnJugador1 = findViewById(R.id.btn_jugador1);
        Button btnJugador2 = findViewById(R.id.btn_jugador2);

        btnJugador1.setEnabled(valor);
        btnJugador2.setEnabled(valor);

        //radioGroup.setAlpha(0);

        RadioButton rbtn_lvl_easy = findViewById(R.id.rbtn_easy);
        RadioButton rbtn_lvl_normal = findViewById(R.id.rbtn_normal);
        RadioButton rbtn_lvl_insane = findViewById(R.id.rbtn_insane);

        rbtn_lvl_easy.setEnabled(valor);
        rbtn_lvl_normal.setEnabled(valor);
        rbtn_lvl_insane.setEnabled(valor);

    }

    /**
     * Reestablece los botones para volver a iniciar la partida
     *
     * @param ganador false: no los reestablece; true: los reestable
     */
    public void reiniciar(boolean ganador) {
        ImageView img1;

        setBotones(ganador);

        for (int i = 0; i < 9; i++) {
            img1 = findViewById(CASILLAS[i]);
            img1.setAlpha(80);

            if (img1.isEnabled() == true) {
                img1.setEnabled(false);
            }

            img1.setTag(null);
        }

    }

}
