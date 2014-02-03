/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.models;

/**
 * Classe de <b>traitement principal</b> du programme
 * <br />Contient des méthodes génériques
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class Principal {

    /**
     * Status du mode DEBUG
     */
    private boolean debug = true;

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param type De quoi provient le message
     * @param message Message de débuggage
     */
    public void debug(String type, String message) {

        if (this.debug == true) {
            System.out.println("DEBUG : (" + type + ") - " + message);
        } else {
        }
    }

    /**
     * Permet de définir le status du mode debug
     *
     * @param debug staut du mode de type <code>boolean</code>
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

}
