package com.example.myfirstapplication.modele;

import java.io.Serializable;
import java.util.Date;

public class Profil implements Serializable {

    // constantes statiques
    private static final Integer minFemme=15;
    private static final Integer maxFemme=30;
    private static final Integer minHomme=10;
    private static final Integer maxHomme=25;
    // proprietes
    private Date dateMesure;
    private Integer poids;
    private Integer taille;
    private Integer age;
    private Integer sexe;
    private float img;
    private String message;

    public Profil(Date dateMesure,Integer poids, Integer taille, Integer age, Integer sexe) {
        this.dateMesure=dateMesure;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.calculIMG();
        this.resultIMG();
    }

    public Date getDateMesure() {return dateMesure;}

    public Integer getPoids() {
        return poids;
    }

    public Integer getTaille() {
        return taille;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getSexe() {
        return sexe;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }

    public void setImg(float img) {
        this.img = img;
    }

    private void calculIMG(){
        float tailleM=(float)taille/100;
        this.img=(float)((1.2*poids/(tailleM*tailleM)) + (0.23*age) - (10.83*sexe) - 5.4);
    }

    private void resultIMG(){
        if (sexe==0) {
                         if (img <minFemme) {message="trop maigre";}
                         else if (img>maxFemme) {message="trop de graisse";}
                         else {message="normal";}
                    }
        else        {
                         if (img <minHomme) {message="trop maigre";}
                         else if (img>maxHomme) {message="trop de graisse";}
                         else {message="normal";}

                     }
    }
}
