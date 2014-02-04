/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.controleurs;

import fr.melysig.models.Parcours;
import fr.melysig.vues.ParcoursVue;

/**
 * Classe de <b>mise en relation de la vue et le modèle des Parcours</b>
 * Permet le traitement des différentes actions nécessaires
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1.2
 */
public class ParcoursControleur {

    /**
     * Déclaration du modèle
     */
    private Parcours modele;
    /**
     * Déclaration de la vue
     */
    private final ParcoursVue vue;

    /**
     * Constructeur de la classe
     */
    public ParcoursControleur() {

        this.modele = new Parcours();
        this.vue = new ParcoursVue();
    }

    /**
     * Permet de charger les données d'un parcours
     *
     * @param id Identifiant unique du parcours
     * @return l'objet parcours contenant les données chargées
     */
    public Parcours chargerParcours(int id) {
        this.modele = modele.chargerParcours(id);
        return modele;
    }

    /**
     * Permet de créer un nouveau parcours
     *
     * @param libelle libellé du parcours
     * @param description description du parcours
     * @return
     */
    public Parcours creerParcours(String libelle, String description) {
        this.modele = new Parcours();
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        return modele.creerParcours(this.modele);
    }

    /**
     * Permet de modifier un parcours existant
     *
     * @param libelle libellé du parcours
     * @param description description du parcours
     * @return
     */
    public Parcours mettreAjourParcours(String libelle, String description) {
        this.modele.setLibelle(libelle);
        this.modele.setDescription(description);
        return modele.mettreAjourParcours(this.modele);
    }

    /**
     * Permet de supprimer un parcours existant
     */
    public void effacerParcours() {
        this.modele.effacerParcours(this.modele);
        this.modele = new Parcours();
    }

    /**
     * Permet d'obtenir l'identifiant unique du parcours depuis le modèle
     *
     * @return l'identifiant unique de type <code>int</code>
     */
    public int getId() {
        return modele.getId();
    }

    /**
     * Permet d'obtenir le libellé du parcours
     *
     * @return le libellé de type <code>String</code>
     */
    public String getLibelle() {
        return modele.getLibelle();
    }

    /**
     * Permet d'obtenir la description du parcours
     *
     * @return la description de type <code>String</code>
     */
    public String getDescription() {
        return modele.getDescription();
    }

    /**
     * Permet de définir le libellé du parcours
     *
     * @param libelle le libellé de type <code>String</code>
     */
    public void setLibelle(String libelle) {
        modele.setLibelle(libelle);
    }

    /**
     * Permet de définir la description du parcours
     *
     * @param description la description de type <code>String</code>
     */
    public void setDescription(String description) {
        modele.setDescription(description);
    }

    /**
     * Permet d'afficher les détails du parcours
     */
    public void afficherDetails() {
        vue.afficherDetails(modele.getId(), modele.getLibelle(), modele.getDescription());
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
