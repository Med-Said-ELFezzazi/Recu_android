package com.example.recu_android;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentListado extends Fragment {

    private Libro[] libros ;
    private ListView lstListado;
    private LibroListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        libros = ListadoLibros.libros;
        return inflater.inflate(R.layout.fragment_listado, container, false);

    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        libros = ListadoLibros.libros;
        lstListado = (ListView)getView().findViewById(R.id.lstListado);
        lstListado.setAdapter(new AdaptadorLibro(this));
        lstListado.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (listener != null)
                    listener.onLibroSeleccionado(
                            (Libro)lstListado.getAdapter().getItem(position));
                return true;
            }
        });
    }
    class AdaptadorLibro extends ArrayAdapter<Libro> {
        Activity context;
        AdaptadorLibro(Fragment context) {
            super(context.getActivity(), R.layout.fragment_listado_list, libros);
            this.context = context.getActivity();
        }
        @NonNull
        @Override
        public View getView(int position,
                            @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.fragment_listado_list, null);
            TextView titulo = (TextView) item.findViewById(R.id.titulo);
            titulo.setText(libros[position].getTitulo());
            TextView autor = (TextView) item.findViewById(R.id.autor);
            autor.setText(libros[position].getAutor());
            return (item);
        }
    }
    public void setLibroListener (LibroListener listener){
        this.listener = listener;
    }

}