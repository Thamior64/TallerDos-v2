package com.example.joseossa.tallerdos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrearUsuario extends AppCompatActivity {

    Button bCrearCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        bCrearCrear = (Button)findViewById(R.id.botonCrearCrear);

        bCrearCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CrearUsuario.this, home.class);
                startActivity(intent);
            }
        });
    }
}
