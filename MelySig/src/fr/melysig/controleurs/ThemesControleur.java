/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.controleurs;

import fr.melysig.models.Themes;
import fr.melysig.vues.EditionVue;

/**
 *
 * @author Coonax
 */
public class ThemesControleur {
       /**
     * Déclaration du modèle
     */
    private Themes modele;
    /**
     * Déclaration de la vue
     */
    private EditionVue vue;

    /**
     * Constructeur de la classe
     * @param modele
     * @param vue
     */
    public ThemesControleur(Themes  modele, EditionVue vue) {

        this.modele = modele;
        this.vue = vue;
    }
//    public Themes Controleur() {
//
//        this.modele = new Themes ();
//        this.vue = new EditionVue();
//    }

    /**
     * Permet de charger les données d'un Themes 
     *
     * @param id Identifiant unique d'un Themes 
     * @return l'objet Themes  contenant les données chargées
     */
    public Themes  chargerTheme (int id) {
        this.modele = modele.chargerTheme (id);
        return modele;
    }

    /**
     * Permet de créer un nouvel Themes 
     *
     * @param libelle Libelle du thème
     * @param description Description du thème
     * @return l'objet Themes  contenant les donnéees
     */
    public Themes creerTheme (String libelle, String description) {
        this.modele = new Themes();
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        return modele.creerTheme (this.modele);
    }

    /**
     * Permet de mettre à jour un Themes  existant
     *
     * @param libelle Libelle du thème
     * @param description Description du thème
     * @return l'objet Themes contenant les donnéees mises à jour
     */
    public Themes mettreAjourTheme(String libelle, String description) {
        this.modele = new Themes();
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        return modele.mettreAjourTheme(this.modele);
    }

    /**
     * Permet de mettre à jour un theme existant (Polymorphisme)
     *
     * @return l'objet Themes contenant les donnéees mises à jour
     */
    public Themes mettreAjourTheme() {
        return modele.mettreAjourTheme(this.modele);
    }

    /**
     * Permet de supprimer un theme existant
     */
    public void effacerTheme() {
        this.modele.effacerTheme(this.modele);
        this.modele = new Themes();
    }

    /**
     * Permet de supprimer un theme existant (Polymorphisme)
     *
     * @param id Identifiant du theme à effacer
     */
    public void effacerTheme(int id) {
        this.modele = new Themes();
        this.modele.chargerTheme(id);
        this.modele.effacerTheme(this.modele);
        this.modele = new Themes();
    }

    /**
     * Permet d'obtenir l'identifiant unique du theme depuis le modèle
     *
     * @return l'identifiant unique de type <code>int</code>
     */
    public int getId() {
        return modele.getId();
    }

    /**
     * Permet d'obtenir le libelle du theme depuis le modèle
     *
     * @return le libelle de type <code>String</code>
     */
    public String getLibelle() {
        return modele.getLibelle();
    }

    /**
     * Permet d'obtenir la description du theme depuis le modèle
     *
     * @return la description de type <code>String</code>
     */
    public String getDescription() {
        return modele.getDescription();
    }


    /**
     * Permet de définir le libelle du theme via le modèle
     *
     * @param libelle le libelle de type <code>String</code>
     */
    public void setLibelle(String libelle) {
        modele.setLibelle(libelle);
    }

    /**
     * Permet de définir la description du theme via le modèle
     *
     * @param description la description de type <code>String</code>
     */
    public void setDescription(String description) {
        modele.setDescription(description);
    }


    /**
     * Permet d'afficher les détails du theme via la vue depuis le modèle
     */
    public void miseAjourVue() {

       // vue.afficherDetailsTheme(modele.getId(), modele.getLibelle(), modele.getDescription());
    }

    /**
     * Permet d'afficher les données de l'objet Themes
     *
     * @return les données définit de l'objet
     */
    @Override
    public String toString() {
        return modele.toString();
    }
}
    

