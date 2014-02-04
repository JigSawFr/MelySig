/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.vues;

/**
 * Classe de vue d'<b>affichage des Parcours</b>
 * Permet d'afficher et manipuler les parcours via une IHM
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class ParcoursVue {

    /**
     * Permet d'afficher les informations concernant un membre
     *
     * @param id Identifiant unique de l'utilisateur
     * @param libelle
     * @param description
     */
    public void afficherDetails(int id, String libelle, String description) {

        System.out.println("-----------------------------");
        System.out.println("ID: " + id);
        System.out.println("Libellé: " + libelle);
        System.out.println("Description: " + description);
        System.out.println("-----------------------------");
    }
}
