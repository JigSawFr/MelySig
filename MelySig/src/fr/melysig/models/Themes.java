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
import fr.melysig.mappages.ThemesDAO;

/**
 *
 * @author Coonax
 */
public class Themes {
    /**
     * Identifiant Unique du theme
     */
    private int id;
    /**
     * Libelle du theme
     */
    private String libelle;
    /**
     * Description du theme
     */
    private String description;
    

    /**
     * Création de l'objet DAO pour les themes
     */
    DAO<Themes> themesDAO;

    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    /**
     * Constructeur de la classe Themes (Polymorphisme)
     */
    public Themes() {
        this.id = 0;
        this.libelle = null;
        this.description = null;
        this.themesDAO = new ThemesDAO();
    }

    /**
     * Constructeur de la classe Themes (Polymorphisme)
     *
     * @param id
     * @param libelle
     * @param description
     */
    public Themes(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.themesDAO = new ThemesDAO();
    }

    /**
     * Chargement des <b>informations concernant un theme existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du theme
     * @return objet de type <code>theme</code>
     */
    public Themes chargerTheme(int id) {

        debug("Recherche d'un theme existant...Chargement du n° " + id);
        Themes resultat = this.themesDAO.chercher(id);
        return resultat;
    }

    /**
     * Ajouter un <b>nouveau theme</b> dans la base de données
     *
     * @param nouveauTheme objet themes contenant les données à ajouter
     * @return objet themes avec l'identifiant unique en base de données
     */
    public Themes creerTheme(Themes nouveauTheme) {

        debug("Ajout d'un nouveau theme.");
        Themes resultat = this.themesDAO.creer(nouveauTheme);
        return resultat;
    }

    /**
     * Modifier un <b>theme existant</b> dans la base de données
     *
     * @param monTheme objet themes contenant les données à modifier
     * @return objet themes avec l'identifiant unique en base de données
     */
    public Themes mettreAjourTheme(Themes monTheme) {

        debug("Modification d'un theme existant.");
        Themes resultat = this.themesDAO.mettreAjour(monTheme);
        return resultat;
    }

    /**
     * Permet de <b>supprimer un theme existant</b>
     *
     * @param monTheme objet themes contenant l'id du theme à supprimer
     */
    public void effacerTheme(Themes monTheme) {

        debug("Suppression d'un theme existant.");
        this.themesDAO.effacer(monTheme);
    }

    /**
     * Permet d'obtenir l'identifiant unique du theme
     *
     * @return l'id unique de type <code>int</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Permet d'obtenir le libelle du theme
     *
     * @return le libelle de type <code>String</code>
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Permet d'obtenir la description du theme
     *
     * @return la description de type <code>String</code>
     */
    public String getDescription() {
        return description;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet de définir le libelle du theme
     *
     * @param libelle Libelle de type <code>String</code>
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Permet de définir la description du theme
     *
     * @param description Description de type <code>String</code>
     */
    public void setDescription(String description) {
        this.description = description;
    }

    
    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Themes -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Themes -> " + message);
    }

    /**
     * Méthode d'affichage de l'objet Themes
     *
     * @return <code>null</code>
     */
    @Override
    public String toString() {
        debug("Objet -> Identifiant : " + this.getId() + " | Libelle : " + this.getLibelle() + " | Description : " + this.getDescription());
        return null;
    }
}
