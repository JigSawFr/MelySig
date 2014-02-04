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
 * Singleton de <b>traitement des informations de débuggage</b> du programme
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class Debug {

    /**
     * Status du mode DEBUG
     */
    private boolean debug;
    private Date horodatageDebug;
    private DateFormat horodatageFormatDebug;

    private static Debug gestionDebug = null;

    private Debug() {
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param type De quoi provient le message
     * @param message Message de débuggage
     */
    public void debug(String type, String message) {

        if (this.debug == true) {

            this.horodatageDebug = new Date();
            this.horodatageFormatDebug = new SimpleDateFormat("dd/MM/YYYY 'à' HH'h' mm'min' ss's' SSS'ms'");
            System.out.println("[" + this.horodatageFormatDebug.format(this.horodatageDebug) + "] DEBUG  : (" + type + ") - " + message);
        } else {
        }
    }

    public static Debug obtenirGestionDebug() {

        if (gestionDebug == null) {
            gestionDebug = new Debug();
        }
        return gestionDebug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
