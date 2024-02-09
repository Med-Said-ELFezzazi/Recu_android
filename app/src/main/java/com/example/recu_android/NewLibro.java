package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewLibro extends AppCompatActivity {
    private Button btnInsertar,btnCancelar,btnVolverInsert;
    private EditText titulo,autor,isbn,editorial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_libro);


        btnInsertar = findViewById(R.id.btnInsertar);
        btnCancelar = findViewById(R.id.btnCancelar);
        btnVolverInsert = findViewById(R.id.btnVolverInsert);

        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        isbn = findViewById(R.id.isbn);
        editorial = findViewById(R.id.editorial);

        BibliotecaSQLiteHelper bibliodbh = new BibliotecaSQLiteHelper(this, "DBBiblio", null, 1);

        btnVolverInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vaciar();
            }
        });

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Compriobacion
                if(!algunoVacio()){
                    if(comprobar()) {
                        BibliotecaDAO dao = new BibliotecaDAO();
                        dao.nuevoLibro(bibliodbh, Integer.parseInt(isbn.getText().toString().trim()), titulo.getText().toString(), autor.getText().toString(), editorial.getText().toString());
                        vaciar();
                        dialogo("Libro añadido correctamente");
                    }
                    else{
                        dialogo("El isbn tiene que ser numerico");
                        // vaciar el campo isbn
                        isbn.setText("");
                    }
                }
                else{
                    Toast.makeText(NewLibro.this, "Deberia rellenar todos los campos", Toast.LENGTH_SHORT).show();


                }
            }
        });
    }

    private void vaciar() {
        titulo.setText("");
        autor.setText("");
        isbn.setText("");
        editorial.setText("");
    }
    private boolean comprobar(){
        try{
            Integer.parseInt(isbn.getText().toString().trim());
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private boolean algunoVacio(){
        if(titulo.getText().toString().trim().equals(""))
            return true;
        if(isbn.getText().toString().trim().equals(""))
            return true;
        if(autor.getText().toString().trim().equals(""))
            return true;
        if(editorial.getText().toString().trim().equals(""))
            return true;
        return false;
    }



    private void dialogo(String msj) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(msj);

        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alertDialogBuilder.show();
    }
}