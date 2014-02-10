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
 * Singleton de <b>traitement des informations de débogage</b> du programme
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1.1
 */
public class Debug {

    /**
     * Status du mode DEBUG
     */
    private boolean debug;
    /**
     * Horodatage de l'information de débogage
     */
    private Date horodatageDebug;
    /**
     * Formatage de l'horodatage
     */
    private DateFormat horodatageFormatDebug;

    /**
     * Objet (Singleton) de gestion des informations de débogage
     */
    private static Debug gestionDebug = null;

    /**
     * Constructeur du Singleton
     */
    private Debug() {
    }

    /**
     * Affichage d'informations de débogage en console
     *
     * @param type De quoi provient le message
     * @param message Message de débogage
     */
    public void debug(String type, String message) {

        if (this.debug == true) {
            this.horodatageDebug = new Date();
            this.horodatageFormatDebug = new SimpleDateFormat("dd/MM/YYYY 'à' HH'h' mm'min' ss's' SSS'ms'");
            System.out.println("[" + this.horodatageFormatDebug.format(this.horodatageDebug) + "] DEBUG  : (" + type + ") - " + message);
        }
    }

    /**
     * Renvoi de l'instance actuelle ou création d'une nouvelle
     *
     * @return instance de l'objet gérant les informations de débogage
     */
    public static Debug obtenirGestionDebug() {
        if (gestionDebug == null) {
            gestionDebug = new Debug();
        }
        return gestionDebug;
    }

    /**
     * Permet de définir l'affichage ou non des informations
     *
     * @param debug statut des informations de type <code>boolean</code>
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}