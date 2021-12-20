package com.android_reforcement;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class Act_Preference extends PreferenceActivity {
/*
    =====> REGLAGE BOOLEAN (MODE SOMBRE OUI NON - SWITCH)
=====> REGLAGE FLOAT (LAST FLOAT)
=====> REGLAGE INT (NOMBRE)
=====> REGLAGE LONG (GRAND NOMBRE)
=====> REGLAGE STRING (PHRASE QUELCQUONQUE)



    private Switch s_mode_sombre;
    private EditText edt_number_float;
    private EditText edt_number_int;
    private EditText edt_text_message;

    private boolean b_mode_sombre;
    private float f_number_float;
    private int int_number;
    private Long l_grand_nombre;
    private String s_phase_message;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        il y a 2 methodes pour recuperer les preferences:
        1 - par la vue de l'activity
        2 - par fichier preferences.xml: il faut declarer dans Manifest.
        Ce lui permet de pas besoin creer 1 layout. il cree par lui meme quand on fait ficher xml
         */

        // case 1:
       // setContentView(R.layout.activity_act_preference);

        // case 2:
        addPreferencesFromResource(R.xml.preferences);


    }


  //  public void editSharePreference(){

        // referenceMemoire = findByID();
        // referenceMemoire.getText();

        // SharedPreferences.Editor editor = mySharePreferences.edit();

        // editor.putBoolean("isTrue", true);
        // editor.putFloat("lastFloat", lf);
        // editor.putInt("wholeNumber", 2);
        // editor.putLong("aNumber", 31);
        // editor.putString("textEntryValue", "Non vide");

        // Enregistre les modifications
        // editor.commit();


   // }
}