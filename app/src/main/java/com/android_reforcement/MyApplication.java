package com.android_reforcement;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android_reforcement.database.DatabaseHelper;

public class MyApplication extends Application {



    // Declare 1 objet type Application qui sera utilisé en tanque Singleton
    private static MyApplication singleton_application;

    DatabaseHelper databaseHelper;

    // ---------------------------------------------------------------------------------------------
    // Déclaration d'une variable centralisée permettant
    public static String MY_PREFS = "MY_PREFS";

    SharedPreferences mySharedPreferences;

    // Préférence pour mode sombre
    String  PREF_BOOL_MODE_SOMBRE_KEY = "Pref_check_box";
    boolean PREF_BOOL_MODE_SOMBRE_DEFAULT_VALUE = false;
    // ---------------------------------------------------------------------------------------------


    // Renvoyer l'intance de l'app
    public static MyApplication getInstance(){
        return singleton_application;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton_application = this;

        // Appel le fonction pour initialisation le DB helper
        initialDBHelper();

        // Case 1 pour methodes pour recuperer les preferences:
        // Appel le fonction pour charger les preference lorque on demarrer l'application
         chargePreference();

    }

    // Methode pour initialisation le Database Helper
    public void initialDBHelper(){
        // on creer 1 objet type DatabaseHelper pour appeler la BD afin de memoriser ce musicien
         databaseHelper = new DatabaseHelper(this);

    }
 //Case 1 pour methodes pour recuperer les preferences:
    public void chargePreference() {

        int mode = Activity.MODE_PRIVATE;
        //mySharedPreferences = getSharedPreferences(MY_PREFS, mode);
        mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    // methode GETTER pour l'objet DatabaseHelper centralisé
    public DatabaseHelper getDatabaseHelper(){
        return databaseHelper;
    }

    // methode Getter pour l'objet SharePreferences centralisé
    public SharedPreferences getSharedPreferences() {
        return mySharedPreferences;
    }
}
