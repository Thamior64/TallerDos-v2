package com.example.joseossa.tallerdos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import comun.Curso;

/**
 * Created by Jose Ossa on 20/10/2017.
 */

public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.CursoViewHolder> {

    private final List<Curso> cursos;
    private final OnCursoListener cursoClick;

    public CursosAdapter(List<Curso> cursos, OnCursoListener cursoClick) {
        this.cursos = cursos;
        this.cursoClick = cursoClick;
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public static class CursoViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo;

        public CursoViewHolder(View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.cursoTitulo);
        }

        public void bind(final Curso item,final OnCursoListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onCursoClick(item);
                }
            });
        }
    }

    @Override
    public CursosAdapter.CursoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cursos_items, parent, false);
        return new CursoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CursosAdapter.CursoViewHolder holder, int position) {
        holder.titulo.setText(cursos.get(position).getNombre());
        holder.bind(cursos.get(position),cursoClick);
    }

    public interface OnCursoListener {
        void onCursoClick(Curso curso);
    }

}
