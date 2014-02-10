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
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO pour les <b>parcours</b>
 * <br />Permet de faire la liaison entre la couches de données et les objets des parcours.
 *
 * @author Sébastien R., Julien P.
 * @since 0.3
 * @version 0.1.5
 */
public class ParcoursDAO extends DAO<Parcours> {

    /**
     * Permet charger les informations d'un parcours
     *
     * @param id l'identifiant de type <code>int/</code>
     * @return
     */
    @Override
    public Parcours chercher(int id) {

        PreparedStatement requetePreparee;
        Parcours monParcours = new Parcours();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM parcours WHERE idParcours = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, id);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monParcours = new Parcours(
                        id,
                        resultats.getString("libelleParcours"),
                        resultats.getString("descriptionParcours")
                );
                this.debug("Recherche -> Parcours localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucun parcours ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monParcours;
    }

    /**
     * Permet de lister différents parcours
     *
     * @param nb nombre de parcours de type <code>int</code>
     * @return
     */
    @Override
    public ArrayList<Parcours> lister(int nb) {
        
        PreparedStatement requetePreparee;
        ArrayList<Parcours> mesParcours = new ArrayList<>();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM parcours LIMIT 0,?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, nb);

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();
            
            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            if (resultats.next()) {
                Parcours monParcours = new Parcours(
                        resultats.getInt("idParcours"),
                        resultats.getString("libelleParcours"),
                        resultats.getString("descriptionParcours")
                );
                mesParcours.add(monParcours);
                this.debug("Listing -> Ajout du parcours n°" + monParcours.getId() + " à la liste avec succès.");
            } else {
                throw new SQLException("Aucun parcours à lister !");
            }

        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesParcours;
    }
    

    /**
     * Permet de créer un nouveau parcours
     *
     * @param nouveauParcours Objet de type <code>Parcours</code>
     * @return Objet de type <code>Parcours</code> muni de l'ID de la base suite à l'insertion
     */
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

    /**
     * Permet de mettre à jour un parcours existant
     *
     * @param monParcours Objet de type <code>Parcours</code>
     * @return Objet de type <code>Parcours</code> muni des nouvelles informations
     */
    @Override
    public Parcours mettreAjour(Parcours monParcours) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "UPDATE parcours SET libelleParcours = ?, descriptionParcours = ? WHERE idParcours = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                    );
            requetePreparee.setString(1, monParcours.getLibelle());
            requetePreparee.setString(2, monParcours.getDescription());
            requetePreparee.setInt(3, monParcours.getId());
            this.debug("Mise à jour -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Mise à jour -> Le parcours n'as pas été mis à jour.");
            }
            this.debug("Mise à jour -> Le parcours a été modifié avec succès.");
        } catch (SQLException erreur) {
            this.erreur("Mise à jour -> Erreur SQL !", erreur);
        }
        return monParcours;
    }

    /**
     * Permet de supprimer un parcours existant
     *
     * @param monParcours Objet de type <code>Parcours</code>
     */
    @Override
    public void effacer(Parcours monParcours) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "DELETE FROM parcours WHERE idParcours = ?"
                    );
            requetePreparee.setInt(1, monParcours.getId());
            this.debug("Suppression -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Le parcours n'as pas été supprimé.");
            } else if (lignes == 1) {
                this.debug("Suppression -> Le parcours n°" + monParcours.getId() + " a bien été supprimé !");
            } else if (lignes > 1) {
                throw new SQLException("Plusieurs parcours ont été supprimés ?? Pas normal tout ça...");
            }
        } catch (SQLException erreur) {
            this.erreur("Suppression -> Erreur SQL !", erreur);
        }
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private void debug(String message) {
        super.gestionDebug.debug("DAO", "Parcours -> " + message);
    }

    /**
     * Affichage des erreurs du en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Parcours -> " + message, erreur);
    }
}
