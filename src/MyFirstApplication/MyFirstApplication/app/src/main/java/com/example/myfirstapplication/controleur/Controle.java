package com.example.myfirstapplication.controleur;
import android.content.Context;

import com.example.myfirstapplication.modele.AccesLocal;
import com.example.myfirstapplication.modele.Profil;
import com.example.myfirstapplication.outils.Serializer;

import java.util.Date;


public final class Controle {

    private static Controle instance=null;
    private static Profil profil;
    private static String nomFic="saveprofil";
    private static AccesLocal accesLocal;


    /**
     * constructeur privé
     */
    private Controle(){
        super();
    }

    /**
     * creation d el'instance
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if (Controle.instance==null) {
            Controle.instance=new Controle();
            accesLocal=new AccesLocal(contexte);
            profil=accesLocal.recupDernier();
            //recupSerialize(contexte);
        }
        return Controle.instance;
    }

    /**
     * creation de profil
     * @param poids
     * @param taille est en centimetre
     * @param age
     * @param sexe 1 pour homme et 0 pour femme
     */
    public void creerProfile( Integer poids, Integer taille, Integer age, Integer sexe , Context contexte){
        profil=new Profil(new Date(),poids, taille, age, sexe);
        accesLocal.ajout(profil);
        //Serializer.serialize(nomFic,profil,contexte);
    }

    /**
     * recuperer img de profil
     * @return img
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * recuperation de message
     * @return message
     */
    public String getMessage(){
        return profil.getMessage();
    }

    /**
     * recperation de l'objet serialisé le profil
     * @param contexte
     */
    private static void recupSerialize(Context contexte) {
        profil =(Profil) Serializer.deserialize(nomFic,contexte);

    }

        public Integer getPoids(){
        if (profil==null){return null;}
        else {return profil.getPoids();}
    }

    public Integer getTaille(){
        if (profil==null){return null;}
        else {return profil.getTaille();}
    }


    public Integer getAge(){
        if (profil==null){return null;}
        else {return profil.getAge();}
    }

    public Integer getSexe(){
        if (profil==null){return null;}
        else {return profil.getSexe();
    }}  

}
