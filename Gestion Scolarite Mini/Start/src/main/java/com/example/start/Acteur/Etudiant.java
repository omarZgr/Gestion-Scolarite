package com.example.start.Acteur;

public class Etudiant extends Persone{

    private int key ;
    private String appoge ;
    private String filiere ;

    public Etudiant() {
        super();
        this.key = -1 ;
    }

    public Etudiant(int id) {
        super(id);
    }



    public int getKey() {
        return key;
    }

    public String getAppoge() {
        return appoge;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setAppoge(String appoge) {
        this.appoge = appoge;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }
}
