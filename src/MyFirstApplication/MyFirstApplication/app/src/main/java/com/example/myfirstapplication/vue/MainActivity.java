package com.example.myfirstapplication.vue;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myfirstapplication.controleur.*;
import com.example.myfirstapplication.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializeViewTreeOwners();
        init();
        //this.control= Controle.getInstance();
    }

    //proprietes
    private EditText PoidsText;
    private EditText TailleText;
    private EditText AgeText;
    private RadioButton radioHomme;
    private TextView observation;
    private ImageView bodyShape;
    private Controle control;

    /**
     * initialisation des liens avec les objets graphiques
     */
    private void init(){
        PoidsText = (EditText) findViewById(R.id.PoidsText) ;
        TailleText = (EditText) findViewById(R.id.TailleText);
        AgeText = (EditText) findViewById(R.id.AgeText) ;
        radioHomme = (RadioButton) findViewById(R.id.radioHomme) ;
        bodyShape = (ImageView) findViewById(R.id.bodyShape) ;
        observation = (TextView) findViewById(R.id.observation) ;
        this.control= Controle.getInstance(this);
        ecouteCalcul();
        recupprofil();
    }

    /**
     * Ecoute evenement sur bouton calcul
     */
    private void ecouteCalcul(){
        ((Button) findViewById(R.id.calculIMC)).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // Affiche un message temporaire "Calcul en cours..." lors du clic
                //Toast.makeText(MainActivity.this, "Calcul en cours...", Toast.LENGTH_SHORT).show();
                //Log.d("BoutonTest", "Le bouton calculIMC a été cliqué.*************");
                Integer poids=0;
                Integer taille=0;
                Integer age=0;
                Integer sexe=0;
                // recuperer les donnes saisie
                try {
                poids=Integer.parseInt(PoidsText.getText().toString());
                taille=Integer.parseInt(TailleText.getText().toString());
                age=Integer.parseInt(AgeText.getText().toString());
                    }  catch (Exception e){}
                if (radioHomme.isChecked()){sexe=1;}
                // controle des donnees saisie
                if (poids*taille*age ==0)
                            {Toast.makeText(MainActivity.this,"saisie incorrect", Toast.LENGTH_SHORT).show();}
                else {
                    afficherResultat(poids,taille,age,sexe);
                }
            }
        });
    }

    /**
     * affichage de l'IMG de message , de message , et de limage
     * @param poids
     * @param taille
     * @param age
     * @param sexe
     */
    private void afficherResultat(Integer poids,Integer taille, Integer age, Integer sexe) {
        this.control.creerProfile(poids, taille, age, sexe,this);
        float img = this.control.getImg();
        String message = this.control.getMessage();
        if (message.equalsIgnoreCase("normal")) {
            if (sexe == 1) {
                bodyShape.setImageResource(R.drawable.muscled);
                observation.setTextColor(Color.GREEN);
            } else {
                bodyShape.setImageResource(R.drawable.athletef);
                observation.setTextColor(Color.GREEN);
            }
        } else if (message.equalsIgnoreCase("trop maigre")) {
            if (sexe == 1) {
                bodyShape.setImageResource(R.drawable.skinny);
                observation.setTextColor(Color.BLUE);
            } else {
                bodyShape.setImageResource(R.drawable.skinnyg);
                observation.setTextColor(Color.BLUE);
            }
        } else if (message.equalsIgnoreCase("trop de graisse")) {
            if (sexe == 1) {
                bodyShape.setImageResource(R.drawable.fat);
                observation.setTextColor(Color.RED);
            } else {
                bodyShape.setImageResource(R.drawable.fatgirl);
                observation.setTextColor(Color.RED);
            }

        }
            observation.setText(String.format("%180.01f", img) + " :  " + message);
    }

    /**
     * recuperation de profil
      */
private void recupprofil(){
    if (control.getPoids() != null) {PoidsText.setText(control.getPoids().toString());
                                     TailleText.setText(control.getTaille().toString());
                                     AgeText.setText(control.getAge().toString());
                                    if (control.getSexe()==1){radioHomme.setChecked(true);}
                                    //simule le click sur button clique
        ((Button)findViewById(R.id.calculIMC)).performClick();
    }
}
}
