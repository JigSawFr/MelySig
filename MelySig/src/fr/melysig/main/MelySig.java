/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

/**
 * Classe principale du programme.
 * <br />Permet le lancement du MVC
 *
 * @author Sébastien R.
 * @since 0.1
 * @version 0.3.1
 */
public class MelySig {

    /**
     * Méthode principale permettant le lancement du programme. Etant statique, celle-ci est initialisée dès le démarrage.
     *
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {

        /**
         * Initialisation du MVC
         * Lancement du programme !
         */
        MVC.obtenirMVC();
    }
}