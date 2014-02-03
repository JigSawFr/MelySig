/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.models;

import fr.melysig.bdd.MaBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

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
     * Objet de connexion
     * <br />Obtention de l'instance de connexion
     */
    Connection connexion = MaBase.obtenirConnexion();
    /**
     * Objet de requête préparée
     */
    PreparedStatement requetePreparee = null;
    /**
     * Requête SQL
     */
    String requeteSql = null;
    private static final Principal modelePrincipal = new Principal();

    /**
     * Chargement des <b>informations concernant un parcours existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du parcours
     * @return <b>true</b> si le parcours existe
     * <br/><b>false</b> dans le cas contraire
     */
    public boolean chargerParcours(int id) {

        /* Requête de récupération des informations sur le parcours */
        this.id = 1;
        this.libelle = "Musée";
        this.description = "Découverte des musées";
        /* Fin de la requête */

        return true;
    }

    /**
     * Ajouter un <b>nouveau parcours</b>
     * dans la base de données
     *
     * @param libelle libellé du parcours
     * @param description description du parcours
     * @return <b>int</b> qui est l'identifiant du nouveau parcours si le
     * parcours a bien été ajouté
     * <br/><b>0</b> dans le cas contraire
     * @throws java.sql.SQLException
     */
    public int ajouterParcours(String libelle, String description) throws SQLException {

        /* Mise en variables des informations du parcours */
        this.libelle = libelle;
        this.description = description;
        debug("Mise en variable du parcours : \n -> Libellé : " + this.libelle + "\n -> Description : " + this.description);

        /* Mise en variable de la requête SQL */
        this.requeteSql = "INSERT INTO parcours"
                + "(libelleParcours, descriptionParcours) VALUES"
                + "(?,?);";
        debug("Création de la requête SQL.");

        try {

            /* On prépare notre requête */
            this.requetePreparee = this.connexion.prepareStatement(this.requeteSql);
            debug("Préparation de la requête SQL.");

            /* On définit les variables de la requête préparée */
            this.requetePreparee.setString(1, this.libelle);
            this.requetePreparee.setString(2, this.description);
            debug("Définition des variables de la requête préparée.");

            /* Execution de la requête SQL */
            this.requetePreparee.executeUpdate();

            debug("Insertion du parcours avec succès.");

        } catch (SQLException erreur) {

            gestionErreur("Impossible de fermer la connexion", erreur);
        } finally {
            if (this.requetePreparee != null) {
                this.requetePreparee.close();
                debug("Fermeture de la requête préparée.");
            }

            if (this.connexion != null) {
                MaBase.fermerConnexion();
            }
        }

        return 0;
    }

    /**
     * Permet d'obtenir l'identifiant unique du parcours
     *
     * @return l'id unique de type <code>int</code>
     */
    public int getId() {
        return id;
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
    private static void gestionErreur(String message, Exception erreur) {

        System.out.println(message);
        if (erreur != null) {

            System.out.println("ERREUR : " + erreur.getMessage());
        }
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        Parcours.modelePrincipal.debug("Parcours", message);
    }
}
