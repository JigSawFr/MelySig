/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.controleurs;

import fr.melysig.models.Actualites;

/**
 * Classe de <b>mise en relation de la vue et le modèle des Actualités</b>
 * Permet le traitement des différentes actions nécessaires
 *
 * @author Gérold M., Sébastien R.
 * @since 0.4
 * @version 0.2
 */
public class ActualitesControleur {

    /**
     * Déclaration du modèle
     */
    private Actualites modele;
    /**
     * Déclaration de la vue
     */

    /**
     * Constructeur de la classe
     *
     * @param modele
     * @param vue
     */
    public ActualitesControleur(Actualites modele) {

        this.modele = modele;
    }
//    public ParcoursControleur() {
//
//        this.modele = new Actualites();
//        this.vue = new ActualitesVue();
//    }

    /**
     * Permet de charger les données d'une actualité
     *
     * @param id Identifiant unique d l'actualité
     * @return l'objet actualites contenant les données chargées
     */
    public Actualites chargerActualite(int id) {
        this.modele = modele.chargerActualite(id);
        return modele;
    }

    /**
     * Permet de créer une nouvelle actualité
     *
     * @param libelle libellé de l'actualité
     * @param description description de l'actualité
     * @return
     */
    public Actualites creerActualite(String libelle, String description, int utilisateur) {
        this.modele = new Actualites();
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        this.modele.setUtilisateur(utilisateur);
        return modele.creerActualite(this.modele);
    }

    /**
     * Permet de modifier une actualité existante (Polymorphisme)
     *
     * @return
     */
    public Actualites mettreAjourActualite() {
        return modele.mettreAjourActualite(this.modele);
    }

    /**
     * Permet de modifier une actualité existante
     *
     * @param libelle libellé de l'actualité
     * @param description description de l'actualité
     * @return
     */
    public Actualites mettreAjourActualite(String libelle, String description, int utilisateur) {
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        this.modele.setUtilisateur(utilisateur);
        return modele.mettreAjourActualite(this.modele);
    }

    /**
     * Permet de supprimer une actualité existante
     */
    public void effacerActualite() {
        this.modele.effacerActualite(this.modele);
        this.modele = new Actualites();
    }

    /**
     * Permet de supprimer un parcours existant (Polymorphisme)
     *
     * @param id Identifiant du parcours à supprimer
     */
    public void effacerParcours(int id) {
        this.modele = new Actualites();
        this.modele.chargerActualite(id);
        this.modele.effacerActualite(this.modele);
        this.modele = new Actualites();
    }

    public int getId() {
        return modele.getId();
    }

    public void setId(int id) {
        modele.setId(id);
    }

    public String getLibelle() {
        return modele.getLibelle();
    }

    public void setLibelle(String libelle) {
        modele.setLibelle(libelle);
    }

    public String getDescription() {
        return modele.getDescription();
    }

    public void setDescription(String description) {
        modele.setDescription(description);
    }

    public int getUtilisateur() {
        return modele.getUtilisateur();
    }

    public void setUtilisateur(int utilisateur) {
        modele.setUtilisateur(utilisateur);
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
