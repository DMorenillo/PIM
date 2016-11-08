package com.example.mati.listaobjeto;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Titular[] datos= new Titular[]{
            new Titular("Titulo 1", "Subtitulo largo 1",R.drawable.img1),
            new Titular("Titulo 2", "Subtitulo largo 2",R.drawable.img2),
            new Titular("Titulo 3", "Subtitulo largo 3",R.drawable.img3)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdaptadorTitutlares adaptador = new AdaptadorTitutlares(this);
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
        lstOpciones.setAdapter(adaptador);

        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int position, long id) {
                String mensaje ="Titulo: " + datos[position].getTitulo()+ " Subtitulo: " +datos[position].getSubtitulo();
                Toast.makeText(getApplicationContext(),mensaje, Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
    }
    class AdaptadorTitutlares extends ArrayAdapter{
        Activity context;

        AdaptadorTitutlares(Activity context){
            super(context, R.layout.listitem_titular,datos);
            this.context= context;
        }
        public View getView(int i, View convertView, ViewGroup parent){
            LayoutInflater inflater = context.getLayoutInflater();
            View item = inflater.inflate(R.layout.listitem_titular, null);

            TextView lblTitulo = (TextView) item.findViewById(R.id.tvTitulo);
            lblTitulo.setText(datos[i].getTitulo());

            TextView lblSubtitulo = (TextView) item.findViewById(R.id.tvSubtitulo);
            lblSubtitulo.setText(datos[i].getSubtitulo());

            //ImageView imagen = (ImageView)item.findViewById(R.id.ivImagen);
            //imagen.setBackground(getDrawable(datos[i].getImagen()));
            return (item);
        }
    }
}
