package com.example.joseossa.tallerdos;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Jose Ossa on 19/10/2017.
 */

public class DialogoCurso extends DialogFragment implements View.OnClickListener {
    Button si, no;
    Comunicador comunicador;
    EditText nuevoCurso;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        comunicador=(Comunicador) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.mi_dialogo_curso, null);
        si = (Button) view.findViewById(R.id.botonSi);
        no = (Button) view.findViewById(R.id.botonNo);
        nuevoCurso = (EditText) view.findViewById(R.id.nuevoCurso);
        si.setOnClickListener(this);
        no.setOnClickListener(this);
        setCancelable(false);
        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.botonSi){

            comunicador.onDialogMessage(nuevoCurso.getText().toString());

            dismiss();
        }else{
            comunicador.onDialogMessage("nel");
            dismiss();
        }
    }

    interface Comunicador{
        public void onDialogMessage(String message);
    }
}
