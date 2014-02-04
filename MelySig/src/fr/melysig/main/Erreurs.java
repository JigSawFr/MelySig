/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Singleton de <b>traitement des erreurs</b> du programme
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class Erreurs {

    /**
     * Status du mode d'affichage des erreurs
     */
    private boolean erreurs;
    private Date horodatageErreur;
    private DateFormat horodatageFormatErreur;

    private static Erreurs gestionErreurs = null;

    private Erreurs() {
    }

    /**
     * Affichage des erreurs en console
     *
     * @param type Type de l'erreur
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    public void erreur(String type, String message, Exception erreur) {
        this.horodatageErreur = new Date();
        this.horodatageFormatErreur = new SimpleDateFormat("dd/MM/YYYY 'à' HH'h' mm'min' ss's' SSS'ms'");
        if (erreur != null) {
            System.out.println("[" + this.horodatageFormatErreur.format(this.horodatageErreur) + "] ERREUR : (" + type + ") - " + message + " (" + erreur.getMessage() + ")");
            //JOptionPane.showMessageDialog(null,"Oooops Petit problème\n"+e.getMessage(),"Warning",JOptionPane.OK_OPTION);
        } else {
            System.out.println("[" + this.horodatageFormatErreur.format(this.horodatageErreur) + "] ERREUR : (" + type + ") - " + message);
        }
    }

    public static Erreurs obtenirGestionErreurs() {
        if (gestionErreurs == null) {
            gestionErreurs = new Erreurs();
        }
        return gestionErreurs;
    }

    public void setErreurs(boolean erreurs) {
        this.erreurs = erreurs;
    }
}
