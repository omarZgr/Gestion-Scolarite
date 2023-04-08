package com.example.start02.Acteur;

import javafx.scene.image.Image;

import java.util.Date;

public  class Persone {

    private int id;
    private String nom ;
    private String prenom ;
    private String gmail ;
    private Date dateNaissence ;
    private String tel ;
    private  String userName ;
    private String password ;
    private Image img ;

    public Persone(int id) {
        this.id= id;
    }

    public Persone() {
        this.id = -1;
    }

    public void setIdPersone(int idPersone) {
        this.id = idPersone;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }

    public void setDateNaissence(Date dateNaissence) {
        this.dateNaissence = dateNaissence;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getIdPersone() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGmail() {
        return gmail;
    }

    public Date getDateNaissence() {
        return dateNaissence;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Image getImg() {
        return img;
    }


}
