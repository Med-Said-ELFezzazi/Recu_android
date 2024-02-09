package com.example.recu_android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class BibliotecaSQLiteHelper extends SQLiteOpenHelper {
    public BibliotecaSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creacion de la tabla de libros
        db.execSQL(
                "CREATE TABLE Libro (" +
                        "isbn INTEGER PRIMARY KEY," +
                        "titulo TEXT," +
                        "autor TEXT," +
                        "editorial TEXT)");

        // insertar
        String insert1 = "INSERT INTO libro  VALUES (9788491641780, 'Memoria del consumismo', 'Frederico', 'La esfera de libros');";
        db.execSQL(insert1);

        String insert2 = "INSERT INTO libro  VALUES (9788426404763, 'La ultima apaga', 'Lumen', 'nucanor');";
        db.execSQL(insert2);

        String insert3 = "INSERT INTO libro  VALUES (9788491641322, 'Los pacientes del doctor', 'Grandes sergio', 'Tusquets');";
        db.execSQL(insert3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS Libro");
        db.execSQL("CREATE TABLE Libro (" +
                "isbn INTEGER PRIMARY KEY," +
                "titulo TEXT," +
                "autor TEXT," +
                "editorial TEXT)");
    }
}
