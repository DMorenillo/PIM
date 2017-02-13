package com.example.mati.proyecto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class JuegosSQLiteHelper extends SQLiteOpenHelper{
    //Sentencia SQL que nos permite crear la tabla Usuarios
    String userSQL = "CREATE TABLE IF NOT EXISTS usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT);";
    //Sentecia SQL que nos permite crear la tabla Juegos
    String matchSQL = "CREATE TABLE IF NOT EXISTS juegos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, genero TEXT, precio INTEGER);";

    public JuegosSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory almacen, int version){
        super (context, nombre, almacen, version);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(userSQL);
        db.execSQL(matchSQL);
    }

    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva){
        db.execSQL("DROP TABLE IF EXISTS usuarios;");
        db.execSQL("DROP TABLE IF EXISTS juegos;");

        db.execSQL(userSQL);
        db.execSQL(matchSQL);
    }

    public void creaBD(SQLiteDatabase db){
        db.execSQL(userSQL);
        db.execSQL(matchSQL);
    }




}
