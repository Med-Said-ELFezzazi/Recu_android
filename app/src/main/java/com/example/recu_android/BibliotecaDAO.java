package com.example.recu_android;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BibliotecaDAO {

    public boolean nuevoLibro(BibliotecaSQLiteHelper helper,int isbn,String titulo,String autor,String editorial) {

        SQLiteDatabase db = helper.getWritableDatabase();
        if (db != null){
            db.execSQL("INSERT INTO Libro " +
                    " VALUES ('"+isbn+"','"+titulo+"','"+autor+"','"+editorial+"') ");
        }
        db.close();
        return true;
    }


    public static Libro[] arrayLibros(BibliotecaSQLiteHelper helper) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Libro[] libros = new Libro[0];
        if (db != null) {
            Cursor c = db.rawQuery("SELECT * FROM Libro ", null);
            libros = new Libro[c.getCount()];
            int cont = 0;
            c.moveToFirst();
            if(c.isLast()){
                int isbn = c.getInt(0);
                String titulo = c.getString(1);
                String autor = c.getString(2);
                String editorial = c.getString(3);
                Libro l = new Libro(isbn, titulo, autor, editorial);
                libros[cont] = l;
            }
            else{ // hay mas de uno
                while(!c.isLast()) {
                    int isbn = c.getInt(0);
                    String titulo = c.getString(1);
                    String autor = c.getString(2);
                    String editorial = c.getString(3);
                    Libro l = new Libro(isbn, titulo, autor, editorial);
                    libros[cont] = l;
                    cont++;
                    c.moveToNext();
                }
                // hay que volver a tratar el ultimo
                int isbn = c.getInt(0);
                String titulo = c.getString(1);
                String autor = c.getString(2);
                String editorial = c.getString(3);
                Libro l = new Libro(isbn, titulo, autor, editorial);
                libros[cont] = l;
            }

        }
        return libros;
    }

    public int cuantosLibros(BibliotecaSQLiteHelper helper){
        int cuantos = 0;
        SQLiteDatabase db = helper.getReadableDatabase();
        if(db != null) {
            Cursor c = db.rawQuery("SELECT * FROM Libro ", null);
            cuantos = c.getCount();
            c.close();
        }
        db.close();
        return cuantos;
    }
}
