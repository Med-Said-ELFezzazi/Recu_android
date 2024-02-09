package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class ListadoLibros  extends AppCompatActivity implements LibroListener{

    private Button btnVolver;
    public static Libro[] libros;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);

        btnVolver = findViewById(R.id.btnvolver);
        BibliotecaSQLiteHelper bibliodbh =
                new BibliotecaSQLiteHelper(this, "DBBiblio", null, 1);
        libros = BibliotecaDAO.arrayLibros(bibliodbh);
       FragmentListado fragmentListado =
                (FragmentListado)getSupportFragmentManager().
                        findFragmentById(R.id.frgListado);
        fragmentListado.setLibroListener(this);
        System.out.println(libros[0]);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

   @Override
    public void onLibroSeleccionado(Libro l) {
        boolean existeDetalle = (getSupportFragmentManager().findFragmentById(R.id.frgDetalle)!= null);
        if (existeDetalle) {
            ((FragmentDetalle)getSupportFragmentManager().
                    findFragmentById(R.id.frgDetalle)).mostrarDetalle(l.getTitulo()+"\n"+l.getTitulo()+"\n"+l.getIsbn()+"\n"+l.getEditorial());
        }
        else {
            Intent i = new Intent(this, DetalleActivity.class);
            i.putExtra(DetalleActivity.EXTRA_TEXTO, l.getTitulo()+"\n"+l.getTitulo()+"\n"+l.getIsbn()+"\n"+l.getEditorial());
            startActivity(i);
        }
    }
}

/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_libros);

        lvLibros = findViewById(R.id.lvLibros);
        btnVolver = findViewById(R.id.btnVolver); // Asegúrate de que el ID coincida aquí

        BibliotecaSQLiteHelper bibliodbh = new BibliotecaSQLiteHelper(this, "DBBiblio", null, 1);
        libros = BibliotecaDAO.arrayLibros(bibliodbh);

        LibroAdapter adapter = new LibroAdapter(this, libros);
        lvLibros.setAdapter(adapter);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


public class LibroAdapter extends ArrayAdapter<Libro> {
    public LibroAdapter(Context context, Libro[] libros) {
        super(context, 0, libros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Libro libro = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_libro, parent, false);
        }

        TextView tvTitulo = convertView.findViewById(R.id.titulo);
        TextView tvAutor = convertView.findViewById(R.id.autor);
        tvTitulo.setText(libro.getTitulo());
        tvAutor.setText(libro.getAutor());

        return convertView;
    }
}*/

