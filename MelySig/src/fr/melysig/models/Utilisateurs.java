/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.models;

import fr.melysig.main.Debug;
import fr.melysig.main.Erreurs;
import fr.melysig.mappages.DAO;
import fr.melysig.mappages.UtilisateursDAO;

/**
 * Classe de <b>traitement des Utilisateurss</b>
 * Permet le traitement des différents membres
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.2
 */
public class Utilisateurs {

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
     * Tableau contenant la liste des utilisateurs du programme
     */
    //private ArrayList<Utilisateurs> listeUtilisateurs = new ArrayList<Utilisateurs>();

    /**
     * Création de l'objet DAO pour les utilisateurs
     */
    DAO<Utilisateurs> utilisateursDAO;
    UtilisateursDAO accessDirectDAO;

    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    /**
     * Constructeur de la classe Utilisateurs (Polymorphisme)
     */
    public Utilisateurs() {
        this.id = 0;
        this.pseudo = null;
        this.motDePasse = null;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.utilisateursDAO = new UtilisateursDAO();
        this.accessDirectDAO = new UtilisateursDAO();
    }

    /**
     * Constructeur de la classe Utilisateurs (Polymorphisme)
     *
     * @param id
     * @param pseudo
     * @param motDePasse
     * @param nom
     * @param prenom
     * @param email
     */
    public Utilisateurs(int id, String pseudo, String motDePasse, String nom, String prenom, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.utilisateursDAO = new UtilisateursDAO();
    }

    /**
     * Vérification des informations de connexion
     *
     * @param identifiant
     * @param motDePasse
     * @return vrai/faux <code>boolean</code>
     */
    public boolean verifierUtilisateur(String identifiant, String motDePasse) {

        debug("Vérification des accès de l'utilisateur " + identifiant + " avec le mot de passe : " + motDePasse);
        boolean resultat = this.accessDirectDAO.verifier(identifiant, motDePasse);
        return resultat;
    }

    /**
     * Chargement des <b>informations concernant un utilisateur existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> de l'utilisateur
     * @return objet de type <code>utilisateur</code>
     */
    public Utilisateurs chargerUtilisateur(int id) {

        debug("Recherche d'un utilisateur existant...Chargement du n° " + id);
        Utilisateurs resultat = this.utilisateursDAO.chercher(id);
        return resultat;
    }

    /**
     * Ajouter un <b>nouvel utilisateur</b> dans la base de données
     *
     * @param nouvelUtilisateur objet utilisateurs contenant les données à ajouter
     * @return objet utilisateurs avec l'identifiant unique en base de données
     */
    public Utilisateurs creerUtilisateur(Utilisateurs nouvelUtilisateur) {

        debug("Ajout d'un nouvel utilisateur.");
        Utilisateurs resultat = this.utilisateursDAO.creer(nouvelUtilisateur);
        return resultat;
    }

    /**
     * Modifier un <b>utilisateur existant</b> dans la base de données
     *
     * @param monUtilisateur objet utilisateurs contenant les données à modifier
     * @return objet utilisateurs avec l'identifiant unique en base de données
     */
    public Utilisateurs mettreAjourUtilisateur(Utilisateurs monUtilisateur) {

        debug("Modification d'un utlisateur existant.");
        Utilisateurs resultat = this.utilisateursDAO.mettreAjour(monUtilisateur);
        return resultat;
    }

    /**
     * Permet de <b>supprimer un utilisateur existant</b>
     *
     * @param monUtilisateur objet utilisateurs contenant l'id de l'utilisateur à supprimer
     */
    public void effacerUtilisateur(Utilisateurs monUtilisateur) {

        debug("Suppression d'un utilisateur existant.");
        this.utilisateursDAO.effacer(monUtilisateur);
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
     * Permet de définir l'identifiant de l'utilisateur
     *
     * @param id ID de type <code>int</code>
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Utilisateurs -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Utilisateurs -> " + message);
    }

    /**
     * Méthode d'affichage de l'objet Utilisateurss
     *
     * @return <code>null</code>
     */
    @Override
    public String toString() {
        debug("Objet -> Identifiant : " + this.getId() + " | Pseudo : " + this.getPseudo() + " | Mot de passe : " + this.getMotDePasse() + " | Nom : " + this.getNom() + " | Prénom : " + this.getPrenom() + " | Email : " + this.getEmail());
        return null;
    }
}
