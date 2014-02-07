///*
// * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
// * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
// * Copyright 2014 - melysig.exia-nancy.com
// * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
// */
package fr.melysig.controleurs;
//
//import fr.melysig.models.PointsInterets;
//import fr.melysig.vues.PointsInteretsVue;
//
///**
// * Classe de <b>mise en relation de la vue et le modèle des Points d'intérêts</b>
// * Permet le traitement des différents actions nécessaires
// *
// * @author Sébastien R.
// * @since 0.4
// * @version 0.1
// */
public class PointsInteretsControleur {
//
//    /**
//     * Déclaration du modèle
//     */
//    private PointsInterets modele;
//    /**
//     * Déclaration de la vue
//     */
//    private PointsInteretsVue vue;
//
//    /**
//     * Constructeur de la classe
//     *
//     * @param modele
//     * @param vue
//     */
//    public PointsInteretsControleur(PointsInterets modele, PointsInteretsVue vue) {
//
//        this.modele = modele;
//        this.vue = vue;
//    }
////    public PointsInteretsControleur() {
////
////        this.modele = new PointsInterets();
////        this.vue = new PointsInteretsVue();
////    }
//
//    /**
//     * Permet de charger les données d'un point d'intérêt
//     *
//     * @param id Identifiant unique du POI
//     * @return l'objet PointsInterets contenant les données chargées
//     */
////    public PointsInterets chargerPointInteret(int id) {
////        this.modele = modele.chargerPointInteret(id);
////        return modele;
////    }
//
//    /**
//     * Permet de créer un nouveau point d'intérêt
//     *
//     * @param coordonneeX Coordonnée X du POI
//     * @param coordonneeY Coordonnée Y du POI
//     * @param libelle Libellé du POI
//     * @param description Description du POI
//     * @param lieu Lieu du POI
//     * @param utilisateur Utilisateur du POI
//     * @param theme Thème du POI
//     * @return l'objet PointsInterets contenant les données à incorporer
//     */
//    public PointsInterets creerPointInteret(int coordonneeX, int coordonneeY, String libelle, String description, int lieu, int utilisateur, int theme) {
//        this.modele = new PointsInterets();
//        this.modele.setX(coordonneeX);
//        this.modele.setY(coordonneeY);
//        this.modele.setLibelle(libelle);
//        this.modele.setDescription(description);
//        this.modele.setLieu(lieu);
//        this.modele.setUtilisateur(utilisateur);
//        this.modele.setTheme(theme);
//        return modele.creerPointInteret(this.modele);
//    }
//
//    /**
//     * Permet de mettre à jour un point d'intérêt existant
//     *
//     * @param coordonneeX Coordonnée X du POI
//     * @param coordonneeY Coordonnée Y du POI
//     * @param libelle Libellé du POI
//     * @param description Description du POI
//     * @param lieu Lieu du POI
//     * @param utilisateur Utilisateur du POI
//     * @param theme Thème du POI
//     * @return l'objet PointsInterets contenant les donnéees mises à jour
//     */
//    public PointsInterets mettreAjourPointInteret(int coordonneeX, int coordonneeY, String libelle, String description, int lieu, int utilisateur, int theme) {
//        this.modele = new PointsInterets();
//        this.modele.setX(coordonneeX);
//        this.modele.setY(coordonneeY);
//        this.modele.setLibelle(libelle);
//        this.modele.setDescription(description);
//        this.modele.setLieu(lieu);
//        this.modele.setUtilisateur(utilisateur);
//        this.modele.setTheme(theme);
//        return modele.mettreAjourPointInteret(this.modele);
//    }
//
//    /**
//     * Permet de mettre à jour un points d'intérêt existant (Polymorphisme)
//     *
//     * @return l'objet PointsInterets contenant les donnéees mises à jour
//     */
//    public PointsInterets mettreAjourPointInteret() {
//        return modele.mettreAjourPointInteret(this.modele);
//    }
//
//    /**
//     * Permet de supprimer un point d'intérêt existant
//     */
//    public void effacerUtilisateur() {
//        this.modele.effacerPointInteret(this.modele);
//        this.modele = new PointsInterets();
//    }
//
//    /**
//     * Permet de supprimer un point d'intérêt existant (Polymorphisme)
//     *
//     * @param id Identifiant du point d'intérêt à effacer
//     */
//    public void effacerPointInteret(int id) {
//        this.modele = new PointsInterets();
//        this.modele.chargerPointInteret(id);
//        this.modele.effacerPointInteret(this.modele);
//        this.modele = new PointsInterets();
//    }
//
//    public int getId() {
//        return modele.getId();
//    }
//
//    public void setId(int id) {
//        modele.setId(id);
//    }
//
//    public int getX() {
//        return modele.getX();
//    }
//
//    public void setX(int X) {
//        modele.setX(X);
//    }
//
//    public int getY() {
//        return modele.getY();
//    }
//
//    public void setY(int Y) {
//        modele.setY(Y);
//    }
//
//    public String getLibelle() {
//        return modele.getLibelle();
//    }
//
//    public void setLibelle(String libelle) {
//        modele.setLibelle(libelle);
//    }
//
//    public String getDescription() {
//        return modele.getDescription();
//    }
//
//    public void setDescription(String description) {
//        modele.setDescription(description);
//    }
//
//    public int getLieu() {
//        return modele.getLieu();
//    }
//
//    public void setLieu(int lieu) {
//        modele.setLieu(lieu);
//    }
//
//    public int getUtilisateur() {
//        return modele.getUtilisateur();
//    }
//
//    public void setUtilisateur(int utilisateur) {
//        modele.setUtilisateur(utilisateur);
//    }
//
//    public int getTheme() {
//        return modele.getTheme();
//    }
//
//    public void setTheme(int theme) {
//        modele.setTheme(theme);
//    }
//
//    /**
//     * Permet d'afficher les données de l'objet Parcours
//     *
//     * @return les données définit de l'objet
//     */
//    @Override
//    public String toString() {
//        return modele.toString();
//    }
}
