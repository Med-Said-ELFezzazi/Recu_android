package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DetalleActivity extends AppCompatActivity {
    private BibliotecaSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public static final String EXTRA_TEXTO = "com.example.fragments.EXTRA_TEXTO";
    private Button btnCerrar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        FragmentDetalle detalle = (FragmentDetalle)getSupportFragmentManager().findFragmentById(R.id.frgDetalle);

        btnCerrar = findViewById(R.id.Btncancelar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.beginTransaction();
                try {
                  //  db.execSQL("DELETE FROM Libro WHERE isbn = ?", new String[]{String.valueOf()});
                    db.setTransactionSuccessful();
                    dialogo("Libro eliminado correctamente");
                    //Actualizar

                } catch (Exception e) {
                    dialogo("Error al eliminar el libro");
                } finally {
                    db.endTransaction();
                }
            }
        });






        //msg al log
        Log.i("EXTRA_TEXTO: ",EXTRA_TEXTO);
        Log.i("paso: ",getIntent().getStringExtra(EXTRA_TEXTO));
        Log.i("AAA: ",getIntent().getClass().toString());
        detalle.mostrarDetalle(getIntent().getStringExtra(EXTRA_TEXTO));
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