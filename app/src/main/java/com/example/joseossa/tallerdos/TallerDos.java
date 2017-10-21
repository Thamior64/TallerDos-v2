package com.example.joseossa.tallerdos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class TallerDos extends AppCompatActivity {

    Button bIniciar;
    Button bCrear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taller_dos);

        bIniciar = (Button)findViewById(R.id.botonIniciar);
        bCrear = (Button)findViewById(R.id.botonCrear);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TallerDos.this, home.class);
                startActivity(intent);

            }
        });

        bCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(TallerDos.this, CrearUsuario.class);
                startActivity(intent);
            }
        });



    }
}
