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
import fr.melysig.mappages.PointsInteretsDAO;
import java.util.ArrayList;

/**
 * Classe de <b>traitement des Points d'intérêts</b>
 * Permet le traitement des différents points d'intérêts
 *
 * @author Sébastien R.
 * @since 0.4
 * @version 0.1
 */
public class PointsInterets {

    /**
     * Identifiant Unique du points d'intérêt
     */
    private int id;
    /**
     * Coordonnée du point X
     */
    private int X;
    /**
     * Coordonnée du point Y
     */
    private int Y;
    /**
     * Libellé du POI
     */
    private String libelle;
    /**
     * Description du POI
     */
    private String description;
    /**
     * ID du lieu
     */
    private Lieux lieu;
    /**
     * ID de l'utilisateur
     */
    private int utilisateur;
    /**
     * ID du thème
     */
    //private int theme;

    private Themes theme;
    
    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();
    
    /**
     * Constructeur de la classe Utilisateurs (Polymorphisme)
     */
    public PointsInterets() {
        this.id = 0;
        this.X = 0;
        this.Y = 0;
        this.libelle = null;
        this.description = null;
        this.lieu = null;
        this.utilisateur = 0;
        this.theme = null;
    }

    /**
     * Constructeur de la classe POI (Polymorphisme)
     *
     * @param id
     * @param X
     * @param Y
     * @param libelle
     * @param description
     * @param lieu
     * @param utilisateur
     * @param theme
     */
    public PointsInterets(int id, int X, int Y, String libelle, String description, Lieux lieu, int utilisateur, Themes theme) {
        this.id = id;
        this.X = X;
        this.Y = Y;
        this.libelle = libelle;
        this.description = description;
        this.lieu = lieu;
        this.utilisateur = utilisateur;
        this.theme = theme;
    }

    /**
     * Permet d'obtenir l'ID du POI
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de définir l'ID du POI
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir la coordonnée X
     *
     * @return
     */
    public int getX() {
        return X;
    }

    /**
     * Permet de définir la coordonnée X
     *
     * @param X
     */
    public void setX(int X) {
        this.X = X;
    }

    /**
     * Permet d'obtenir la coordonnée Y
     *
     * @return
     */
    public int getY() {
        return Y;
    }

    /**
     * Permet de définir la coordonnée Y
     *
     * @param Y
     */
    public void setY(int Y) {
        this.Y = Y;
    }

    /**
     * Permet d'obtenir le libellé du POI
     *
     * @return
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Permet de définir le libellé du POI
     *
     * @param libelle
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Permet d'obtenir la description du POI
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de définir la description du POI
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Permet d'obtenir l'ID du lieu du POI
     *
     * @return
     */
    public Lieux getLieu() {
        return lieu;
    }

    /**
     * Permet de définir l'ID du lieu du POI
     *
     * @param lieu
     */
    public void setLieu(Lieux lieu) {
        this.lieu = lieu;
    }

    /**
     * Permet d'obtenir l'ID de l'utilisateur du POI
     *
     * @return
     */
    public int getUtilisateur() {
        return utilisateur;
    }

    /**
     * Permet de définir l'ID de l'utilisateur du POI
     *
     * @param utilisateur
     */
    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Permet d'obtenir l'ID du thème du POI
     *
     * @return
     */
    public Themes getTheme() {
        return theme;
    }

    /**
     * Permet de définir l'ID du thème du POI
     *
     * @param theme
     */
    public void setTheme(Themes theme) {
        this.theme = theme;
    }

          /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Points d'intérêts -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Points d'intérêts -> " + message);
    }
    
    /**
     * Méthode d'affichage de l'objet PointsInterets
     *
     * @return <code>null</code>
     */
    @Override
    public String toString() {
        debug("Objet -> Identifiant : " + this.getId() + " | Coordonnée X : " + this.getX() + " | Coordoonnée Y : " + this.getY() + " | Libellé : " + this.getLibelle() + " | Description : " + this.getDescription() + " | Lieu : " + this.getLieu() + " | Utilisateur : " + this.getUtilisateur() + " | Thème : " + this.getTheme());
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PointsInterets) {
            return o!= null && ((PointsInterets)o).getId() == getId();
        }
        return false;
    }

    
    
}
