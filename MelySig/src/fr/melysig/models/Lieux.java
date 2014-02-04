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
import fr.melysig.mappages.LieuxDAO;

/**
 * Classe de <b>traitement des Parcours</b>
 * Permet le traitement des différents parcours
 *
 * @author Julien P.
 * @since 0.4
 * @version 0.1.1
 */

public class Lieux {
    
    /**
     * Identifiant Unique du lieux
     */
    private int id;
    /**
     * Nom du lieux
     */
    private String nom;
    /**
     * Carte du lieux
     */
    private String carte;
    /**
     * Description du lieux
     */
    private String description;
    /**
     * idUtilisateur du lieux
     */
    private int idUtilisateur;
    
     /**
     * Création de l'objet DAO pour les parcours
     */
    DAO<Lieux> lieuxDAO;

    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();
    
    
    /**
     * Constructeur de la classe Lieux (Polymorphisme)
     */
    public Lieux() {
        this.id = 0;
        this.nom = null;
        this.carte = null;
        this.description = null;
        this.idUtilisateur = 0;
        this.lieuxDAO = new LieuxDAO();
    }
    
    /**
     * Constructeur de la classe Parcours (Polymorphisme)
     *
     * @param id
     * @param nom
     * @param carte
     * @param description
     * @param idUtilisateur
     */
    public Lieux(int id, String nom, String carte, String description, int idUtilisateur) {
        this.id = id;
        this.nom = nom;
        this.carte = carte;
        this.description = description;
        this.idUtilisateur = idUtilisateur;
        this.lieuxDAO = new LieuxDAO();
    }
    
     /**
     * Chargement des <b>informations concernant un lieux existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du lieux
     * @return <b>true</b> si le lieux existe
     * <br/><b>false</b> dans le cas contraire
     */
    
     public Lieux chargerLieux(int id) {

        debug("Recherche d'un lieux existant...Chargement du n° " + id);
        Lieux resultat = this.lieuxDAO.chercher(id);
        return resultat;
    }

    public Lieux creerLieux(Lieux nouveauLieux) {

        debug("Ajout d'un nouveau lieux");
        Lieux resultat = this.lieuxDAO.creer(nouveauLieux);
        return resultat;
    }
    
     /**
     * Permet d'obtenir l'identifiant unique du lieux
     *
     * @return l'id unique de type <code>int</code>
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Permet d'obtenir le nom du lieux
     *
     * @return le lieu de type <code>String</code>
     */
    public String getNom() {
        return nom;
    }
    
    /**
     * Permet de définir le nom du lieux
     *
     * @param nom le nom de type <code>String</code>
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
     /**
     * Permet d'obtenir la carte du lieux
     *
     * @return la carte de type <code>String</code>
     */
    public String getCarte() {
        return carte;
    }
    
    /**
     * Permet de définir la carte du lieux
     *
     * @param carte la carte de type <code>String</code>
     */
    public void setCarte(String carte) {
        this.carte = carte;
    }
    
    /**
     * Permet d'obtenir la description du lieux
     *
     * @return la description de type <code>String</code>
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Permet de définir la description du lieux
     *
     * @param description la description de type <code>String</code>
     */
    public void setDescription(String description){
        this.description = description;
    }
    
    /**
     * Permet d'obtenir l'id utilisateur du lieux
     *
     * @return l'id utilisateur de type <code>int</code>
     */
    public int getIDUtilisateur() {
        return idUtilisateur;
    }
    
    /**
     * Permet de définir l'id utilisateur du lieux
     *
     * @param idUtilisateur l'id utilisateur de type <code>int</code>
     */
    public void setIDUtilisateur(int idUtilisateur){
        this.idUtilisateur = idUtilisateur;
    }    
    
    /**
     * Affichage des erreurs du Singleton en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Lieux -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Lieux -> " + message);
    }
    
    /**
     * Méthode d'affichage de l'objet Lieux
     *
     * @return
     */
    @Override
    public String toString() {
        String affichage = "\n---- Affichage de l'objet Lieux ----\n";
        affichage += "Identifiant: " + this.getId() + "\n";
        affichage += "Nom: " + this.getNom() + "\n";
        affichage += "Carte: " + this.getCarte() + "\n";
        affichage += "Description: " + this.getDescription() + "\n";
        affichage += "ID Utilisateur: " + this.getIDUtilisateur() + "\n";
        affichage += "---------------------------------------\n";
        return affichage;
    }
    
    
}
