/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.vues;

/**
 * Classe de vue d' <b>affichage des UtilisateursVues</b>
 * Permet d'afficher et manipuler les membres via une IHM
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.1.1
 */
public class UtilisateursVue {

    /**
     * Permet d'afficher les informations concernant un membre
     *
     * @param id Identifiant unique de l'utilisateur
     * @param pseudo Pseudo de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @param nom Nom de famille de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     * @param email Email de l'utilisateur
     */
    public void afficherDetailsUtilisateur(int id, String pseudo, String motDePasse, String nom, String prenom, String email) {

        System.out.println("-----------------------------");
        System.out.println("ID: " + id);
        System.out.println("Pseudo: " + pseudo);
        System.out.println("Mot de passe: " + motDePasse);
        System.out.println("Nom de famille: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("-----------------------------");
    }
}
