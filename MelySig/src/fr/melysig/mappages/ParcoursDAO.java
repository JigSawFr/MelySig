/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.mappages;

import fr.melysig.models.Parcours;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe DAO pour les <b>parcours</b>
 * <br />Permet de faire la liaison entre la couches de données et les objets des parcours.
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class ParcoursDAO extends DAO<Parcours> {

    @Override
    public Parcours chercher(int id) {

        Parcours monParcours = new Parcours();
        try {
            ResultSet resultats = this.connexion
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            //ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    ).executeQuery(
                            "SELECT * FROM parcours WHERE idParcours = " + id
                    );
            if (resultats.first()) {
                monParcours = new Parcours(
                        id,
                        resultats.getString("libelleParcours"),
                        resultats.getString("descriptionParcours")
                );
                this.debug("Recherche -> Parcours localisé dans la base avec succès.");
            }
            else
            {
                throw new SQLException("Aucun parcours ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monParcours;
    }

    @Override
    public Parcours creer(Parcours nouveauParcours) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO parcours (libelleParcours, descriptionParcours) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, nouveauParcours.getLibelle());
            requetePreparee.setString(2, nouveauParcours.getDescription());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucun lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du parcours.");
                nouveauParcours.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau parcours ajouté avec succès (N°" + nouveauParcours.getId() + ").");
        return nouveauParcours;
    }

    @Override
    public Parcours mettreAjour(Parcours objet) {
        throw new UnsupportedOperationException("Fonction non implémenté."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void effacer(Parcours objet) {
        throw new UnsupportedOperationException("Fonction non implémenté."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private void debug(String message) {
        super.gestionDebug.debug("DAO", "Parcours -> " + message);
    }

    /**
     * Affichage des erreurs du Singleton en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Parcours -> " + message, erreur);
    }
}
