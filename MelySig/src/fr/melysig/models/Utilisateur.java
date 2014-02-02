/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.models;

/**
 * Classe de <b>traitement des Utilisateurs</b>
 * Permet le traitement des différents membres
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.1
 */
public class Utilisateur {

    /**
     * Identifiant Unique de l'utilisateur
     */
    private int id;
    /**
     * Pseudo de l'utilisateur
     */
    private String pseudo;
    /**
     * Mot de passe de l'utilisateur
     */
    private String motDePasse;
    /**
     * Nom de famille de l'utilisateur
     */
    private String nom;
    /**
     * Prénom de l'utilisateur
     */
    private String prenom;
    /**
     * Email de l'utilisateur
     */
    private String email;

    /**
     * Chargement des <b>informations concernant un utilisateur existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> de l'utilisateur
     * @return <b>true</b> si l'utilsiateur existe en BDD et a été bien chargé
     * <br/><b>false</b> dans le cas contraire
     */
    public boolean chargerUtilisateur(int id) {

        /* Requête de récupération des informations sur l'utilisateur */
        this.id = 1;
        this.pseudo = "TheRainb0w";
        this.motDePasse = "root";
        this.nom = "Robert";
        this.prenom = "Sébastien";
        this.email = "sebastien.robert@viacesi.fr";
        /* Fin de la requête */

        return true;
    }

    /**
     * Permet d'obtenir l'identifiant unique de l'utilisateur
     *
     * @return l'id unique de type <code>int</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Permet d'obtenir le pseudo de l'utilisateur
     *
     * @return le pseudo de type <code>String</code>
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Permet d'obtenir le mot de passe de l'utilisateur
     *
     * @return le mot de passe de type <code>String</code>
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Permet d'obtenir le nom de famille de l'utilisateur
     *
     * @return le nom de famille de type <code>String</code>
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet d'obtenir le prénom de l'utilisateur
     *
     * @return le prénom de type <code>String</code>
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Permet d'obtenir l'email de l'utilisateur
     *
     * @return l'email de type <code>String</code>
     */
    public String getEmail() {
        return email;
    }

    /**
     * Permet de définir le pseudo de l'utilisateur
     *
     * @param pseudo Pseudo de type <code>String</code>
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Permet de définir le mot de passe de l'utilisateur
     *
     * @param motDePasse Mot de passe de type <code>String</code>
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Permet de définir le nom de famille de l'utilisateur
     *
     * @param nom Nom de famille de type <code>String</code>
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de définir le prénom de l'utilisateur
     *
     * @param prenom Prénom de type <code>String</code>
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Permet de définir l'email de l'utilisateur
     *
     * @param email Email de type <code>String</code>
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
