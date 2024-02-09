package com.example.recu_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;




public class NoticiasPais extends AppCompatActivity {
 //   public String url = "https://www.europapress.es/rss/rss.aspx";

    private Button btnVolver;
    private ListView lvLista;
    private TextView tvLocalidad;
    private Spinner spinnerLocalidades;

    private List<Noticia> noticias;

    private ArrayList<String> localidades = new ArrayList<>();
    private ArrayList<String> rssLink = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_pais);

        btnVolver = findViewById(R.id.btnVolver);
        lvLista = findViewById(R.id.lvLista);
        tvLocalidad = findViewById(R.id.tvLocalidad);

        spinnerLocalidades = findViewById(R.id.spinnerLocalidades);

        cargarDatosDesdeArchivo();

        // ArrayAdapter para el Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, localidades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocalidades.setAdapter(adapter);

        spinnerLocalidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    String selectedUrl = rssLink.get(position - 1);
                    cargarXMLConSAX(selectedUrl);
                    String texto = "Noticias de ... " + localidades.get(position);
                    tvLocalidad.setText(texto);
                } else {
                    tvLocalidad.setText("Selecciona una localidad");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });



        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Noticia noticiaSeleccionada = noticias.get(position);
                    String url = noticiaSeleccionada.getLink();
                    if (url != null && !url.isEmpty()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } else {
                        Toast.makeText(NoticiasPais.this, "Enlace no disponible", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("NoticiasPais", "Error al abrir la noticia", e);
                }
            }
        });


        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void cargarDatosDesdeArchivo() {
        try {
            Resources res = getResources();
            InputStream in_s = res.openRawResource(R.raw.url_noticas_comunidades);

            BufferedReader reader = new BufferedReader(new InputStreamReader(in_s));
            String linea;

            localidades.add("Elige");

            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    localidades.add(partes[0]);
                    rssLink.add(partes[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void cargarXMLConSAX (String rssUrl){
        // Inicia la tarea asincrónica con el URL seleccionado
        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute(rssUrl);
    }

    //Clase interna
    public class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {
        protected Boolean doInBackground(String... params) {
            RssParserSAX saxparser = new RssParserSAX(params[0]);
            noticias = saxparser.parse();
            return true;
        }

        protected void onPostExecute(Boolean result) {
            //Tratamos la lista de noticias
            if (result && noticias != null) {
                NoticiaAdapter adapter = new NoticiaAdapter(NoticiasPais.this, noticias);
                lvLista.setAdapter(adapter);


            }
        }
    }
}



