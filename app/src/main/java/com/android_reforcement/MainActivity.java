package com.android_reforcement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;

import com.android_reforcement.extraFonctions.AppelToast;
import com.android_reforcement.model.Musicien;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edT_MainActivity_nomMusicien;
    EditText edT_MainActivity_nombreEtoile;
    ListView listview_MainActivity_listMusiciens;
    Switch switch_MainActivity_SelecteurMusicien;

    List<Musicien> musicienList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fct_remplirvue();

    }

    public void fct_remplirvue(){
        this.edT_MainActivity_nomMusicien =findViewById(R.id.xml_edT_MainActivity_nomMusicien);
        this.edT_MainActivity_nombreEtoile =findViewById(R.id.xml_edT_MainActivity_nombreEtoile);
        this.listview_MainActivity_listMusiciens =findViewById(R.id.xml_listview_MainActivity_listMusiciens);
        this.switch_MainActivity_SelecteurMusicien =findViewById(R.id.xml_switch_MainActivity_SelecteurMusicien);
    }



    public void act_voirTout(View view) {

       AppelToast.displayCustomToast(this, "Voir la liste des musicien");

    }

    public void act_ajouterMusicien(View view) {

        try {
            String nomMusicien = edT_MainActivity_nomMusicien.getText().toString();
            int nombreEtoileMusicien = Integer.parseInt(edT_MainActivity_nombreEtoile.getText().toString());
            boolean activeMusicien = switch_MainActivity_SelecteurMusicien.isActivated();

            Musicien musicien= new Musicien(nomMusicien,nombreEtoileMusicien,activeMusicien);
            musicienList.add(musicien);

            AppelToast.displayCustomToast(this, "Nouveau musicien est créé.");
        } catch (Exception e){
            AppelToast.displayCustomToast(this, "Erreur quand on cree nouveau musicien"+e.toString());
        }

    }
}