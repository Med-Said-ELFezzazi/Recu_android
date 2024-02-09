package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SQLite extends AppCompatActivity {

    private Button btnNewLib, btnListadoLib, btnBuscarLib, btnvolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        btnNewLib = findViewById(R.id.btnNewLib);
        btnListadoLib = findViewById(R.id.btnListadoLib);
        btnBuscarLib = findViewById(R.id.btnBuscarLib);
        btnvolver = findViewById(R.id.btnvolver);

        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SQLite.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNewLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SQLite.this, NewLibro.class);
                startActivity(intent);
            }
        });

        btnListadoLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SQLite.this, ListadoLibros.class);
                startActivity(i);
            }
        });


    }
}