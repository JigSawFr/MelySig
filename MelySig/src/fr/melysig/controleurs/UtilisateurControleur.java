/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.controleurs;

import fr.melysig.models.Utilisateur;
import fr.melysig.vues.UtilisateurVue;

/**
 * Classe de <b>mise en relation de la vue et le modèle des Utilisateurs</b>
 * Permet le traitement des différents actions nécessaires
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.1
 */
public class UtilisateurControleur {

    private Utilisateur modele;
    private UtilisateurVue vue;

    /**
     * Constructeur de la classe
     *
     * @param modele Modèle requis par le contrôleur
     * @param vue Vue requise par le contrôleur
     */
    public UtilisateurControleur(Utilisateur modele, UtilisateurVue vue) {

        this.modele = modele;
        this.vue = vue;
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
     * Permet d'afficher les détails de l'utilisateur via la vue depuis le
     * modèle
     */
    public void miseAjourVue() {

        vue.afficherDetailsUtilisateur(modele.getId(), modele.getPseudo(), modele.getMotDePasse(), modele.getNom(), modele.getPrenom(), modele.getEmail());
    }

    /**
     * Permet de charger les détails d'un utilisateurs depuis la base de données
     *
     * @param id identifiant unique de type <code>int</code>
     * @return <code>true</code> si l'utilisateur a été trouvé
     * <br /><code>false</code> dans le cas contraire
     */
    public boolean chargerUtilisateur(int id) {

        return modele.chargerUtilisateur(id);
    }

}
