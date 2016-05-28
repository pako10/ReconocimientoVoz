package com.cesarandroid.reconocimentodevoz;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton reconoce = (ImageButton) findViewById(R.id.BotonVoz);
        tvResultado = (TextView) findViewById(R.id.textView);

        reconoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Este es el intent del reconocimiento de voz
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //Especificamos el idioma, en esta ocasión probé con el de Estados Unidos
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //Iniciamos la actividad dentro de un Try en caso surja un error.

                try {
                    startActivityForResult(intent, 1);
                }catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(), "Tu dispositivo no soporta el reconocimiento de voz", Toast.LENGTH_LONG).show();
                }

            }


        });
    }

    public void onActivityResult(int requestcode, int resultcode, Intent datos)
    {

// Si el reconocimiento de voz es correcto almacenamos dentro de un array los datos obtenidos.
//Mostramos en pantalla el texto obtenido de la posición 0.
        if (resultcode == Activity.RESULT_OK && datos!=null)
        {
            String nuevo = RecognizerIntent.EXTRA_RESULTS;
            if(nuevo.equals("hola")){

            }
            ArrayList<String> text = datos.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if(text.get(0).equals("hola")){
                tvResultado.setText("Correcto");
            }else {
                tvResultado.setText("Incorrecto");
            }
            Toast.makeText(this,text.get(0),Toast.LENGTH_LONG).show();
        }

    }


}
