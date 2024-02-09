package com.example.recu_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class NoticiaAdapter extends ArrayAdapter<Noticia> {


    public NoticiaAdapter(Context context, List<Noticia> noticias) {

        super(context, 0, noticias);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Noticia noticia = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_noticia, parent, false);
        }
        TextView tvTitulo = convertView.findViewById(R.id.tvTitulo);
        TextView tvFecha = convertView.findViewById(R.id.tvFecha);
        TextView lvLink = convertView.findViewById(R.id.tvLink);

        //Poner los datos en los textViews usando los getters de la clase noticia
        tvTitulo.setText(noticia.getTitulo());
        tvFecha.setText(noticia.getFecha());
        lvLink.setText(noticia.getLink());



        //HAcer que al click en el enlace se abre en el navegador
        tvTitulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir el enlace en el navegador web
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(noticia.getLink()));   //No me va, quizas hay que hacer algo en el movil antes
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }
}
