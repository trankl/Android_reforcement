package com.android_reforcement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.android_reforcement.model.Musicien;

public class DatabaseHelper extends SQLiteOpenHelper {

    String table_musicien = "musicien";
    String f_musicien_id = "musicien_id";
    String f_musicien_nom ="musicien_nom";
    String f_musicien_etoile = "musicien_etoile";
    String f_musicien_active = "isActive";



    public DatabaseHelper(@Nullable Context context) {
        super(context, "musichall-db", null, 1);
    }

    // ce methode pour creer 1 table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement= "CREATE TABLE "+table_musicien+"(" +
                f_musicien_id     + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                f_musicien_nom    + " VARCHAR(100) NOT NULL," +
                f_musicien_etoile + " INT NOT NULL," +
                f_musicien_active + " BOOLEAN NOT NULL" +
                ");";

        db.execSQL(createTableStatement);
    }

    // ce methode pour mettre à jour la table suite à la demande de client
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Methode pour ajouter 1 musicien à bd
    public boolean addMusicien(Musicien musicienACreer){
        boolean resultatInsertion = false;

        // ouvrir la base de donnees pour creer nouveau musicien
        SQLiteDatabase ref_db = this.getWritableDatabase();

        // creer 1 contentvalue pour stocker les valeur du nouveau musicien
        ContentValues contentValues_nouveauMusicienAjouter = new ContentValues();

        // preparer les veleurs du nouveau musicien
        contentValues_nouveauMusicienAjouter.put(f_musicien_nom, musicienACreer.getMusicien_nom());
        contentValues_nouveauMusicienAjouter.put(f_musicien_etoile, musicienACreer.getMusicien_etoile());
        contentValues_nouveauMusicienAjouter.put(f_musicien_active, musicienACreer.isActive());

        // faire insert dans la base de donnees
        long lastInsertID =ref_db.insert(table_musicien, null, contentValues_nouveauMusicienAjouter );

        // CAS 1 : CAS ECHEC, Le inserted id vaut -1 cela veut dire que l'insertion n'a pas eu lieu
        if (lastInsertID ==-1){
             resultatInsertion = false;
        }
        // CAS 2 : CAS SUCCES, on positionne le flag en sortie à true
        else {
            resultatInsertion = true;
        }

        return resultatInsertion;


    }
}
