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
//import java.sql.SQLException;

/**
 * Classe de <b>traitement des Parcours</b>
 * Permet le traitement des différents parcours
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class Parcours {

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
    //private ArrayList<PointsInterets> listePointsInterets = new ArrayList<PointsInterets>();

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

    /**
     * Chargement des <b>informations concernant un parcours existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du parcours
     * @return <b>true</b> si le parcours existe
     * <br/><b>false</b> dans le cas contraire
     */
    public Parcours chargerParcours(int id) {

        debug("Recherche d'un parcours existant...Chargement du n° " + id);
        Parcours resultat = this.parcoursDAO.chercher(id);
        return resultat;
    }

    public Parcours creerParcours(Parcours nouveauParcours) {

        debug("Ajout d'un nouveau parcours");
        Parcours resultat = this.parcoursDAO.creer(nouveauParcours);
        return resultat;
    }

    /**
     * Ajouter un <b>nouveau parcours</b>
     * dans la base de données
     *
     * @param libelle libellé du parcours
     * @param description description du parcours
     * @return <b>int</b> qui est l'identifiant du nouveau parcours si le parcours a bien été ajouté
     * <br/><b>0</b> dans le cas contraire
     * @throws java.sql.SQLException
     */
//    public int ajouterParcours(String libelle, String description) throws SQLException {
//
//        /* Mise en variables des informations du parcours */
//        this.libelle = libelle;
//        this.description = description;
//        debug("Mise en variable du parcours : \n -> Libellé : " + this.libelle + "\n -> Description : " + this.description);
//
//        /* Mise en variable de la requête SQL */
//        /*this.requeteSql = "INSERT INTO parcours"
//         + "(libelleParcours, descriptionParcours) VALUES"
//         + "(?,?);";*/
//        debug("Création de la requête SQL.");
//        try {
//
//        /* On prépare notre requête */
//            this.requetePreparee = this.connexion.prepareStatement(this.requeteSql);
//        debug("Préparation de la requête SQL.");
//
//        /* On définit les variables de la requête préparée */
//            this.requetePreparee.setString(1, this.libelle);
//        this.requetePreparee.setString(2, this.description);
//        debug("Définition des variables de la requête préparée.");
//
//        /* Execution de la requête SQL */
//        this.requetePreparee.executeUpdate();
//        debug("Insertion du parcours avec succès.");
//        } catch (SQLException erreur) {
//        gestionErreur("Impossible de fermer la connexion", erreur);
//        } finally {
//        if (this.requetePreparee != null) {
//        this.requetePreparee.close();
//        debug("Fermeture de la requête préparée.");
//        }
//        if (this.connexion != null) {
//        MaBase.fermerConnexion();
//        }
//        }
//        return 0;
//    }
    /**
     * Permet d'obtenir l'identifiant unique du parcours
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

    /**
     * Affichage des erreurs du Singleton en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Parcours -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Parcours -> " + message);
    }

    /**
     * Méthode d'affichage de l'objet Parcours
     *
     * @return
     */
    @Override
    public String toString() {
        String affichage = "\n---- Affichage de l'objet PARCOURS ----\n";
        affichage += "Identifiant: " + this.getId() + "\n";
        affichage += "Libellé: " + this.getLibelle() + "\n";
        affichage += "Description: " + this.getDescription() + "\n";
        affichage += "---------------------------------------\n";
        return affichage;
    }

}
