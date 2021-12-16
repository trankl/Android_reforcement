package com.android_reforcement.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.android_reforcement.model.Musicien;

import java.util.ArrayList;
import java.util.List;

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

        ref_db.close();
        return resultatInsertion;
    }

    public List<Musicien> getAllMusiciens(){
        List<Musicien> listMusiciens = new ArrayList<Musicien>();

        SQLiteDatabase ref_db = this.getWritableDatabase();

        String getListMusicienStatement = "SELECT * FROM " +table_musicien;

        //Cette interface cursor fournit un accès en lecture-écriture aléatoire à l'ensemble de résultats renvoyé par une requête de base de données.
        Cursor cursor_resultatALaRequete = ref_db.rawQuery(getListMusicienStatement, null);

        if(cursor_resultatALaRequete.moveToFirst() == true){
            do {
                int id = cursor_resultatALaRequete.getInt(0);
                String nom = cursor_resultatALaRequete.getString(1);
                int nbEtoile = cursor_resultatALaRequete.getInt(2);
                boolean isActive = cursor_resultatALaRequete.getInt(3) == 1 ? true : false;

                Musicien musicien = new Musicien(id, nom, nbEtoile, isActive);

                listMusiciens.add(musicien);
            } while (cursor_resultatALaRequete.moveToNext());
        }

        // fermer le cursor
        cursor_resultatALaRequete.close();

        // fermer la BD
        ref_db.close();

        return listMusiciens;
    }
/*
    // Methode qui permet de supprimer un musicien de la base de donnée
    public boolean removeOneMusicien(MusicienModel i_ref_musicienModel) {
        // On déclare la variable en sortie pour le résultat de la suppression initialement à false
        boolean o_bool_resultatSuppression = false;

        // On déclare une référence vers la base de donnée
        SQLiteDatabase l_ref_db = this.getWritableDatabase();

        // On récupère l'id à partir du getter dans l'objet Musicien Model fourni en paramètre
        int l_int_musicienIdASupprimer = i_ref_musicienModel.getId();

        // On doit rechercher le musicien dans la base de donnée SQLite
        // La requete à executer est : DELETE FROM T_MUSICIENS WHERE MUSICIEN_ID = XXX
        String l_str_requete = "DELETE FROM " + T_MUSICIENS
                + " WHERE " +  F_MUSICIEN_ID + " = " + l_int_musicienIdASupprimer;

        // On execute la requete NON PREPAREE
        // TODO "PREPARER" avec le système de PREPARED STATEMENTS ???
        Cursor l_Cursor_suppressionMusicien = l_ref_db.rawQuery(l_str_requete, null);

        // CAS 1 : On a réussi à faire "MoveToFirst", alors on positionne le flag en sortie à true
        if (l_Cursor_suppressionMusicien.moveToFirst()) {
            o_bool_resultatSuppression = true;
        }
        // CAS 2 : On n'a pas réussi à faire le "MoveToFirst", on positionne le flag en sortie à false
        else {o_bool_resultatSuppression = false;}

        // On retourne le résultat de la suppression
        return o_bool_resultatSuppression;
    }

 */
}
