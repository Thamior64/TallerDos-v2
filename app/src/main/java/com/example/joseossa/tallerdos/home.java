package com.example.joseossa.tallerdos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import comun.Curso;
import comun.Mensaje;

public class home extends AppCompatActivity implements DialogoCurso.Comunicador {

    Button bCerrar, bAbrir;

    private RecyclerView cursos;
    private RecyclerView.Adapter cursosAdapter;

    private List listaCursos;

    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("HOMEWORKS");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listaCursos = new ArrayList();

        bCerrar = (Button)findViewById(R.id.botonCerrar);
        bAbrir = (Button)findViewById(R.id.botonAgregarCurso);


        bCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(home.this, Inicio.class);
                startActivity(intent);
            }
        });

        cursos = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(LinearLayoutManager.VERTICAL);
        cursos.setLayoutManager(lim);

        bAbrir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDialogoCurso(view);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot cursosSnap: dataSnapshot.getChildren()){
                    listaCursos.add(new Curso(cursosSnap.child("nombre").getValue(String.class),cursosSnap.child("palabritas").getValue(String.class)));
                }
                //Toast.makeText(getApplicationContext(),"Actualizado "+ listaCursos.size(), Toast.LENGTH_LONG).show();
                cursosAdapter = new CursosAdapter(listaCursos, new CursosAdapter.OnCursoListener() {
                    @Override
                    public void onCursoClick(Curso curso) {
                        Intent nuevaActividad = new Intent(home.this,InsideActivity.class);
                        nuevaActividad.putExtra("TITULO",curso.getNombre());
                        nuevaActividad.putExtra("RELLENO",curso.getPalabritas());
                        startActivity(nuevaActividad);
                    }
                });

                cursos.setAdapter(cursosAdapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getApplicationContext(),"Error de recibimiento de datos", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void mostrarDialogoCurso(View v){
        android.app.FragmentManager manager=getFragmentManager();
        DialogoCurso dialogoCurso = new DialogoCurso();
        dialogoCurso.show(manager, "Dialogo");
    }

    @Override
    public void onDialogMessage(final String message) {
        if (!message.contains("nel")){


            listaCursos.add(new Curso(message,""));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Cliente.getInstance().enviarMensaje(new Mensaje("curso:"+message));
                }
            }).start();
            cursosAdapter.notifyItemInserted(listaCursos.size()-1);
            cursosAdapter.notifyDataSetChanged();
        }
    }
}
