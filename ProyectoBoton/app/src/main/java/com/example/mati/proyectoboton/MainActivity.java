package com.example.mati.proyectoboton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;
    protected Button boton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1=(Button)findViewById(R.id.boton1);
        boton2=(Button)findViewById(R.id.boton2);
        boton3=(Button)findViewById(R.id.boton3);
        boton4=(Button)findViewById(R.id.boton4);


        boton1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this, boton1.class);
                Bundle miBudle = new Bundle();
                startActivity(miIntent);
            }
        }
        );
        boton2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent = new Intent(MainActivity.this, boton2.class);
                Bundle miBudle = new Bundle();
                startActivity(miIntent);
            }
        }
        );
        boton3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent = new Intent(MainActivity.this, boton3.class);
                Bundle miBudle = new Bundle();
                startActivity(miIntent);
            }

        }
        );
        boton4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent miIntent = new Intent(MainActivity.this, boton4.class);
                Bundle miBudle = new Bundle();
                startActivity(miIntent);
            }

        }
        );
    }
}
