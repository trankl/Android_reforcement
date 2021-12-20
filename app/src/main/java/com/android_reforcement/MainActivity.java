package com.android_reforcement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.android_reforcement.database.DatabaseHelper;
import com.android_reforcement.extraFonctions.AppelToast;
import com.android_reforcement.model.Musicien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    EditText edT_MainActivity_nomMusicien;
    EditText edT_MainActivity_nombreEtoile;
    ListView listview_MainActivity_listMusiciens;
    Switch switch_MainActivity_SelecteurMusicien;
    Switch switch_MainActivity_mode_sombre;

    List<Musicien> musicienList= new ArrayList<>();
    DatabaseHelper databaseHelper = MyApplication.getInstance().getDatabaseHelper();
    SharedPreferences mySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fct_remplirvue();

        // methode pour initialisation le DatabaseHelper
        // Ce ligne est remplacer par la declaration dans class principale MyApplication
        // initialDBHelper();

        // Préférence pour mode sombre
        String  PREF_BOOL_MODE_SOMBRE_KEY = "Pref_check_box";
        boolean PREF_BOOL_MODE_SOMBRE_DEFAULT_VALUE = false;

        configurePreferencePourActivity();
        onSharedPreferenceChanged(mySharedPreferences, PREF_BOOL_MODE_SOMBRE_KEY);

    }

    public void fct_remplirvue(){
        this.edT_MainActivity_nomMusicien =findViewById(R.id.xml_edT_MainActivity_nomMusicien);
        this.edT_MainActivity_nombreEtoile =findViewById(R.id.xml_edT_MainActivity_nombreEtoile);
        this.listview_MainActivity_listMusiciens =findViewById(R.id.xml_listview_MainActivity_listMusiciens);
        this.switch_MainActivity_SelecteurMusicien =findViewById(R.id.xml_switch_MainActivity_SelecteurMusicien);
        this.switch_MainActivity_mode_sombre=findViewById(R.id.xml_switch_MainActivity_mode_sombre);
    }
/*
    public void initialDBHelper(){
        // on creer 1 objet type DatabaseHelper pour appeler la BD afin de memoriser ce musicien
        databaseHelper = new DatabaseHelper(MainActivity.this);
    }

 */

    public void act_voirTout(View view) {

        musicienList = databaseHelper.getAllMusiciens();

        // ArrayAdapter a utilisé à afficher le ListView avec des ListItem simple
        // android.R.layout.simple_list_item_1 est une disposition prédéfinie constante d'Android.
        ArrayAdapter arrayAdapter_listMusiciens = new ArrayAdapter<Musicien>(this, android.R.layout.simple_list_item_1,musicienList);

        listview_MainActivity_listMusiciens.setAdapter(arrayAdapter_listMusiciens);

       AppelToast.displayCustomToast(this, "Voir la liste des musicien");

    }

    public void act_ajouterMusicien(View view) {

        try {
            String nomMusicien = edT_MainActivity_nomMusicien.getText().toString();
            int nombreEtoileMusicien = Integer.parseInt(edT_MainActivity_nombreEtoile.getText().toString());
            boolean activeMusicien = switch_MainActivity_SelecteurMusicien.isActivated();

            Musicien musicien= new Musicien(nomMusicien,nombreEtoileMusicien,activeMusicien);
            musicienList.add(musicien);

            // on creer 1 objet type DatabaseHelper pour appeler la BD afin de memoriser ce musicien
           // DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
            // on ajoute ce musicien à la BD
            databaseHelper.addMusicien(musicien);

            AppelToast.displayCustomToast(this, "Nouveau musicien est créé.");
        } catch (Exception e){
            AppelToast.displayCustomToast(this, "Erreur quand on cree nouveau musicien"+e.toString());
        }

    }

    public void act_open_preference(View view) {

        // On crée un Intent
        Intent intent = new Intent(this, Act_Preference.class);

        //lancer Intent
        startActivity(intent);

    }
    // Methode qui permet de configurer l'activité pour beneficier des préférences
    private void configurePreferencePourActivity(){
            // Les valeurs des options présentées dans une Preference Activity sont stocjées dans le
            // contexte de l'application. Cela permet à tout composant d'application comme les activities,
            // les Services et les Broadcast Receivers d'y accéder
        Context context = getApplicationContext();

        // On accède aux préférences du contexte
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

        // On effectue le register pour bénéficier des mises à jour en direct des préférences
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // A faire : Vérifier la shared preference et les clés et modifier l'apparence et le
        // comportement en conséquence
        //int mode = Activity.MODE_PRIVATE;
        //SharedPreferences mySharedPreferences = getSharedPreferences(MY_PREFS, mode);
        Log.i("TRIGRAMME", "a1");

        mySharedPreferences= MyApplication.getInstance().getSharedPreferences();
        Log.i("TRIGRAMME", "a2");


       // boolean valeurPrefModeSombre =  mySharedPreferences.getBoolean("Pref_check_box", false);
        //Log.i("TRIGRAMME", "a3 ["+valeurPrefModeSombre+"]");


        // On récupère la clef de la preference MODE SOMBRE et la valeur par défaut si la preference n'existe pas
        String l_str_clefPreference_MODE_SOMBRE = MyApplication.getInstance().PREF_BOOL_MODE_SOMBRE_KEY;
        boolean l_bool_defaultValue_MODE_SOMBRE = MyApplication.getInstance().PREF_BOOL_MODE_SOMBRE_DEFAULT_VALUE;



        // On récupère maintenant la valeur de la préférence
        boolean l_bool_valeurPrefModeSombre = mySharedPreferences.getBoolean(l_str_clefPreference_MODE_SOMBRE, l_bool_defaultValue_MODE_SOMBRE);



       switch_MainActivity_mode_sombre.setChecked(l_bool_valeurPrefModeSombre);

      String nom = mySharedPreferences.getString("Pref_message", "nom");
      this.edT_MainActivity_nomMusicien.setText(nom);
      
    }
}