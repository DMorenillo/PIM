package com.example.mati.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    CheckBox chkBoxLeer;
    CheckBox chkBoxMusica;
    CheckBox chkBoxDeporte;
    Button btnHobby;
    TextView txtHobby;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitialUISetup();
    }
    public void InitialUISetup()
    {
        chkBoxLeer = (CheckBox) findViewById(R.id.chkBoxLeer);
        chkBoxMusica = (CheckBox) findViewById(R.id.chkboxMusica);
        chkBoxDeporte = (CheckBox) findViewById(R.id.chkboxDeporte);
        btnHobby = (Button) findViewById(R.id.btnHobby);
        txtHobby = (TextView) findViewById(R.id.txtHobby);
        btnHobby.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                getHobbyClick(v);
            }
        });
    }

    public void getHobbyClick(View v)
     {
         String strMessage=" ";
         if (chkBoxLeer.isChecked())
         {
             strMessage+="Leer ";
         }
         if (chkBoxDeporte.isChecked())
         {
             strMessage+="Deporte ";
         }
         if (chkBoxMusica.isChecked())
         {
             strMessage+="Musica ";
         }
         showTextNotification(strMessage);
        }
        public void showTextNotification(String msgToDisplay)
        {
            txtHobby.setText(msgToDisplay);
            //Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
        }
}
