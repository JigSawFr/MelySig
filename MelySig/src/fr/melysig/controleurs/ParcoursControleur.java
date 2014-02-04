/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.controleurs;

import fr.melysig.models.Parcours;
import fr.melysig.vues.ParcoursVue;
//import java.sql.SQLException;

/**
 * Classe de <b>mise en relation de la vue et le modèle des Parcours</b>
 * Permet le traitement des différents actions nécessaires
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class ParcoursControleur {

    private Parcours modele;
    private final ParcoursVue vue;

    /**
     * Constructeur de la classe
     */
    public ParcoursControleur() {

        this.modele = new Parcours();
        this.vue = new ParcoursVue();
    }

    public Parcours chargerParcours(int id) {
        this.modele = modele.chargerParcours(id);
        return modele;
    }

    public Parcours creerParcours(String libelle, String description) {
        this.modele = new Parcours();
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        return modele.creerParcours(this.modele);
    }

//    public int ajouterParcours(String libelle, String description) throws SQLException {
//        return modele.ajouterParcours(libelle, description);
//    }

    public int getId() {
        return modele.getId();
    }

    public String getLibelle() {
        return modele.getLibelle();
    }

    public String getDescription() {
        return modele.getDescription();
    }

    public void setLibelle(String libelle) {
        modele.setLibelle(libelle);
    }

    public void setDescription(String description) {
        modele.setDescription(description);
    }

    public void afficherDetails() {
        vue.afficherDetails(modele.getId(), modele.getLibelle(), modele.getDescription());
    }

    @Override
    public String toString() {
        return modele.toString();
    }  
}
