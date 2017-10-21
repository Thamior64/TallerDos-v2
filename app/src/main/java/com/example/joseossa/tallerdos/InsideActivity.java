package com.example.joseossa.tallerdos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import comun.Mensaje;

public class InsideActivity extends AppCompatActivity {

    Button volver,guardar;
    String titulo,relleno;

    EditText nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        Bundle info = getIntent().getExtras();
        titulo = info.getString("TITULO");
        relleno = info.getString("RELLENO");

        nota = (EditText) findViewById(R.id.notes);

        nota.setText(relleno);

        guardar = (Button) findViewById(R.id.botonGuardar);
        volver = (Button) findViewById(R.id.botonVolver);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Cliente.getInstance().enviarMensaje(new Mensaje("guardar:"+titulo+":"+nota.getText().toString()));
                    }
                }).start();
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
