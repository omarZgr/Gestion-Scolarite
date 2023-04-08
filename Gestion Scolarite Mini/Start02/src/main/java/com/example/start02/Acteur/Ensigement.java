package com.example.start02.Acteur;

public class Ensigement extends Persone{

    private int key ;
    private String matricule ;
    private String etat ;

    public Ensigement() {
        super();
        this.key = -1 ;
    }

    public Ensigement(int id) {
        super(id);
    }

    public int getKey() {
        return key;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getEtat() {
        return etat;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
