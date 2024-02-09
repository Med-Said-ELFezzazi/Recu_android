package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {
    private BibliotecaSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    private Button btnSQLite, btnNoticias, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSQLite = findViewById(R.id.btnSQLite);
        btnNoticias = findViewById(R.id.btnNoticias);
        btnSalir = findViewById(R.id.btnSalir);

        //cargar BD
        // Inicializa el dbHelper       CREACION DE LA BD
        dbHelper = new BibliotecaSQLiteHelper(this, "Libros.db", null, 1);

        // Obtén una instancia de la base de datos
        db = dbHelper.getReadableDatabase();        //Las tablas ya están creadas


        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacion();
            }
        });

        btnSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SQLite.class);
                startActivity(intent);
            }
        });


        //noticias
        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NoticiasPais.class);
                startActivity(i);
            }
        });


    }


    private void confirmacion() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirmación de salir");
        alertDialogBuilder.setMessage("Estas seguro de que quieres salir de la app ?");

        alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
                System.exit(0);
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //hacer nada
            }
        });

        alertDialogBuilder.show();
    }

}