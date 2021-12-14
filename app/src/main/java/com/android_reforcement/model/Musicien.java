package com.android_reforcement.model;

public class Musicien {
   // @Id @GeneratedValue(strategy=GenerationType.IDENTITY )
    private int musicien_id;

    private String musicien_nom;
    private int musicien_etoile;
    private boolean isActive;

    public Musicien() {
        this("inconnu",0, false);
    }

    // Contructor avec 3 parametres
    public Musicien(String i_musicien_nom, int i_musicien_etoile, boolean i_isActive) {
        this.musicien_nom = i_musicien_nom;
        this.musicien_etoile = i_musicien_etoile;
        this.isActive = i_isActive;
    }

    // Contructor avec 4 parametres
    public Musicien(int i_musicien_id, String i_musicien_nom, int i_musicien_etoile, boolean i_isActive) {
        this.musicien_id = i_musicien_id;
        this.musicien_nom = i_musicien_nom;
        this.musicien_etoile = i_musicien_etoile;
        this.isActive = i_isActive;
    }

    @Override
    public String toString() {
        return "Musicien [idMusicien=" + musicien_id + ", nom=" + musicien_nom + ", nombre d'etoile" + musicien_etoile  + "] ";
    }

    public int getMusicien_id() {
        return musicien_id;
    }

    public String getMusicien_nom() {
        return musicien_nom;
    }

    public void setMusicien_nom(String musicien_nom) {
        this.musicien_nom = musicien_nom;
    }

    public int getMusicien_etoile() {
        return musicien_etoile;
    }

    public void setMusicien_etoile(int musicien_etoile) {
        this.musicien_etoile = musicien_etoile;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
