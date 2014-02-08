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
import fr.melysig.mappages.ParcoursDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Classe de <b>traitement des Parcours</b>
 * Permet le traitement des différents parcours
 *
 * @author Sébastien R., Julien P.
 * @since 0.3
 * @version 0.1.2
 */
public class Parcours{

    /**
     * Identifiant Unique du parcours
     */
    private int id;
    /**
     * Libéllé du parcours
     */
    private String libelle;
    /**
     * Description du parcours
     */
    private String description;
    /**
     * Tableau contenant la liste des points d'intérêts d'un parcours
     */
    private List<PointsInterets> listePointsInterets = new ArrayList<PointsInterets>();

    
    /**
     * Création de l'objet DAO pour les parcours
     */
    DAO<Parcours> parcoursDAO;

    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    /**
     * Constructeur de la classe Parcours (Polymorphisme)
     */
    public Parcours() {
        this.id = 0;
        this.libelle = null;
        this.description = null;
        this.parcoursDAO = new ParcoursDAO();
    }

    /**
     * Constructeur de la classe Parcours (Polymorphisme)
     *
     * @param id
     * @param libelle
     * @param description
     */
    public Parcours(int id, String libelle, String description/*, ArrayList<PointsInterets> listePointsInterets*/) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        /*this.listePointsInterets = listePointsInterets;*/
        this.parcoursDAO = new ParcoursDAO();
    }

//    /**
//     * Chargement des <b>informations concernant un parcours existant</b>
//     * dans la base de données
//     *
//     * @param id Identifiant <b>unique</b> du parcours
//     * @return objet de type <code>parcours</code>
//     */
//    public Parcours chargerParcours(int id) {
//
//        debug("Recherche d'un parcours existant...Chargement du n° " + id);
//        Parcours resultat = this.parcoursDAO.chercher(id);
//        return resultat;
//    }
//
//    /**
//     * Ajouter un <b>nouveau parcours</b> dans la base de données
//     *
//     * @param nouveauParcours objet parcours contenant les données à ajouter
//     * @return objet parcours avec l'identifiant unique en base de données
//     */
//    public Parcours creerParcours(Parcours nouveauParcours) {
//
//        debug("Ajout d'un nouveau parcours.");
//        Parcours resultat = this.parcoursDAO.creer(nouveauParcours);
//        return resultat;
//    }
//    
//    /**
//     * Modifier un <b>parcours existant</b> dans la base de données
//     *
//     * @param monParcours objet parcours contenant les données à modifier
//     * @return objet parcours avec l'identifiant unique en base de données
//     */
//    public Parcours mettreAjourParcours(Parcours monParcours) {
//
//        debug("Modification d'un parcours existant.");
//        Parcours resultat = this.parcoursDAO.mettreAjour(monParcours);
//        return resultat;
//    }
//
//    /**
//     * Permet de <b>supprimer un parcours existant</b>
//     * @param monParcours objet parcours contenant l'id du parcours à supprimer
//     */
//    public void effacerParcours(Parcours monParcours) {
//        
//        debug("Suppression d'un parcours existant.");
//        this.parcoursDAO.effacer(monParcours);
//    }

    /**
     * Permet d'obtenir l'identifiant unique du parcours
     *
     * @return l'id unique de type <code>int</code>
     */
    public int getId() {
        return id;
    }

    /**
     * Permet de définir l'identifiant unique du parcours
     * @param id l'identifiant unique du parcours de type <code>int</code>
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet d'obtenir le libellé du parcours
     *
     * @return le libellé de type <code>String</code>
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Permet d'obtenir la description du parcours
     *
     * @return la description de type <code>String</code>
     */
    public String getDescription() {
        return description;
    }

    /**
     * Permet de définir le libellé du parcours
     *
     * @param libelle le libellé de type <code>String</code>
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Permet de définir la description du parcours
     *
     * @param description la description de type <code>String</code>
     */
    public void setDescription(String description) {
        this.description = description;
    }

    public void setListPointInterets(List<PointsInterets> pointInterets) {
        this.listePointsInterets = pointInterets;
    }
    
    public List<PointsInterets> getListPointsInterets() {
        return listePointsInterets;
    }
    
    
    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Parcours -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Parcours -> " + message);
    }
    
    
    /**
     * Méthode d'affichage de l'objet Parcours
     *
     * @return <code>null</code>
     */
    @Override
    public String toString() {
        debug("Objet -> Identifiant : " + this.getId() + " | Pseudo : " + this.getLibelle() + " | Mot de passe : " + this.getDescription());
        return null;
    }

}
