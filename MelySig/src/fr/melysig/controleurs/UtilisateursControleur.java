/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.controleurs;

import fr.melysig.models.Utilisateurs;
import fr.melysig.vues.UtilisateursVue;

/**
 * Classe de <b>mise en relation de la vue et le modèle des Utilisateurss</b>
 * Permet le traitement des différents actions nécessaires
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.2
 */
public class UtilisateursControleur {

    /**
     * Déclaration du modèle
     */
    private Utilisateurs modele;
    /**
     * Déclaration de la vue
     */
    private UtilisateursVue vue;

    /**
     * Constructeur de la classe
     * @param modele
     * @param vue
     */
    public UtilisateursControleur(Utilisateurs modele, UtilisateursVue vue) {

        this.modele = modele;
        this.vue = vue;
    }
//    public UtilisateursControleur() {
//
//        this.modele = new Utilisateurs();
//        this.vue = new UtilisateursVue();
//    }

    /**
     * Permet de charger les données d'un utilisateur
     *
     * @param id Identifiant unique d'un utilisateur
     * @return l'objet utilisateur contenant les données chargées
     */
    public Utilisateurs chargerUtilisateur(int id) {
        this.modele = modele.chargerUtilisateur(id);
        return modele;
    }

    /**
     * Permet de créer un nouvel utilisateur
     *
     * @param pseudo Pseudo de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @param nom Nom de famille de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     * @param email Email de l'utilisateur
     * @return l'objet Utilisateurs contenant les donnéees
     */
    public Utilisateurs creerUtilisateur(String pseudo, String motDePasse, String nom, String prenom, String email) {
        this.modele = new Utilisateurs();
        this.modele.setPseudo(pseudo);
        this.modele.setMotDePasse(motDePasse);
        this.modele.setNom(nom);
        this.modele.setPrenom(prenom);
        this.modele.setEmail(email);
        return modele.creerUtilisateur(this.modele);
    }

    /**
     * Permet de mettre à jour un utilisateur existant
     *
     * @param pseudo Pseudo de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @param nom Nom de famille de l'utilisateur
     * @param prenom Prénom de l'utilisateur
     * @param email Email de l'utilisateur
     * @return l'objet Utilisateurs contenant les donnéees mises à jour
     */
    public Utilisateurs mettreAjourUtilisateur(String pseudo, String motDePasse, String nom, String prenom, String email) {
        this.modele = new Utilisateurs();
        this.modele.setPseudo(pseudo);
        this.modele.setMotDePasse(motDePasse);
        this.modele.setNom(nom);
        this.modele.setPrenom(prenom);
        this.modele.setEmail(email);
        return modele.mettreAjourUtilisateur(this.modele);
    }

    /**
     * Permet de mettre à jour un utilisateur existant (Polymorphisme)
     *
     * @return l'objet Utilisateurs contenant les donnéees mises à jour
     */
    public Utilisateurs mettreAjourUtilisateur() {
        return modele.mettreAjourUtilisateur(this.modele);
    }

    /**
     * Permet de supprimer un utilisateur existant
     */
    public void effacerUtilisateur() {
        this.modele.effacerUtilisateur(this.modele);
        this.modele = new Utilisateurs();
    }

    /**
     * Permet de supprimer un utilisateur existant (Polymorphisme)
     *
     * @param id Identifiant de l'utilisateur à effacer
     */
    public void effacerUtilisateur(int id) {
        this.modele = new Utilisateurs();
        this.modele.chargerUtilisateur(id);
        this.modele.effacerUtilisateur(this.modele);
        this.modele = new Utilisateurs();
    }

    /**
     * Permet d'obtenir l'identifiant unique de l'utilisateur depuis le modèle
     *
     * @return l'identifiant unique de type <code>int</code>
     */
    public int getId() {
        return modele.getId();
    }

    /**
     * Permet d'obtenir le pseudo de l'utilisateur depuis le modèle
     *
     * @return le pseudo de type <code>String</code>
     */
    public String getPseudo() {
        return modele.getPseudo();
    }

    /**
     * Permet d'obtenir le mot de passe de l'utilisateur depuis le modèle
     *
     * @return le mot de passe de type <code>String</code>
     */
    public String getMotDePasse() {
        return modele.getMotDePasse();
    }

    /**
     * Permet d'obtenir le nom de famille de l'utilisateur depuis le modèle
     *
     * @return le nom de famille de type <code>String</code>
     */
    public String getNom() {
        return modele.getNom();
    }

    /**
     * Permet d'obtenir le prénom de l'utilisateur depuis le modèle
     *
     * @return le prénom de type <code>String</code>
     */
    public String getPrenom() {
        return modele.getPrenom();
    }

    /**
     * Permet d'obtenir l'email de l'utilisateur depuis le modèle
     *
     * @return l'email de type <code>String</code>
     */
    public String getEmail() {
        return modele.getEmail();
    }

    /**
     * Permet de définir le pseudo de l'utilisateur via le modèle
     *
     * @param pseudo le pseudo de type <code>String</code>
     */
    public void setPseudo(String pseudo) {
        modele.setPseudo(pseudo);
    }

    /**
     * Permet de définir le mot de passe de l'utilisateur via le modèle
     *
     * @param motDePasse le mot de passe de type <code>String</code>
     */
    public void setMotDePasse(String motDePasse) {
        modele.setMotDePasse(motDePasse);
    }

    /**
     * Permet de définir le nom de famille de l'utilisateur via le modèle
     *
     * @param nom le nom de famille de type <code>String</code>
     */
    public void setNom(String nom) {
        modele.setNom(nom);
    }

    /**
     * Permet de définir le prénom de l'utilisateur via le modèle
     *
     * @param prenom le prénom de type <code>String</code>
     */
    public void setPrenom(String prenom) {
        modele.setPrenom(prenom);
    }

    /**
     * Permet de définir l'email de l'utilisateur via le modèle
     *
     * @param email l'email de type <code>String</code>
     */
    public void setEmail(String email) {
        modele.setEmail(email);
    }

    /**
     * Permet d'afficher les détails de l'utilisateur via la vue depuis le modèle
     */
    public void miseAjourVue() {

        vue.afficherDetailsUtilisateur(modele.getId(), modele.getPseudo(), modele.getMotDePasse(), modele.getNom(), modele.getPrenom(), modele.getEmail());
    }

    /**
     * Permet d'afficher les données de l'objet Parcours
     *
     * @return les données définit de l'objet
     */
    @Override
    public String toString() {
        return modele.toString();
    }
}
