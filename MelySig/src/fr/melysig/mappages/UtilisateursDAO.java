/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.mappages;

import fr.melysig.models.Utilisateurs;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO pour les <b>utilisateurs</b>
 * <br />Permet de faire la liaison entre la couche de données et les objets des utilisateurs.
 *
 * @author Sébastien R.
 * @since 0.4
 * @version 0.1.0
 */
public class UtilisateursDAO extends DAO<Utilisateurs> {

    /**
     * Permet de vérifier si les identifications d'un utilisateur sont corrects
     *
     * @param identifiant
     * @param motDePasse
     * @return
     */
    public int verifier(String identifiant, String motDePasse) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM utilisateurs WHERE pseudoUtilisateur = ? AND motDePasseUtilisateur = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setString(1, identifiant);
            requetePreparee.setString(2, motDePasse);

            this.debug("Vérification -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                this.debug("Vérification -> Utilisateur " + resultats.getInt("idUtilisateur") + " localisé dans la base avec succès.");
                return resultats.getInt("idUtilisateur");
            } else {
                this.debug("Vérification -> Login / Mot de passe erronnés.");
                return 0;
            }

        } catch (SQLException erreur) {
            this.erreur("Vérification -> Erreur SQL !", erreur);
        }
        return 0;
    }

    /**
     * Permet charger les informations d'un utilisateur
     *
     * @param id l'identifiant de type <code>int/</code>
     * @return
     */
    @Override
    public Utilisateurs chercher(int id) {

        PreparedStatement requetePreparee;
        Utilisateurs monUtilisateur = new Utilisateurs();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM utilisateurs WHERE idUtilisateur = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, id);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monUtilisateur = new Utilisateurs(
                        id,
                        resultats.getString("pseudoUtilisateur"),
                        resultats.getString("motDePasseUtilisateur"),
                        resultats.getString("nomUtilisateur"),
                        resultats.getString("prenomUtilisateur"),
                        resultats.getString("emailUtilisateur")
                );
                this.debug("Recherche -> Utilisateur localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucun utilisateur ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monUtilisateur;
    }

    /**
     * Permet de lister différents utilisateurs
     *
     * @param nb nombre d'utilisateurs de type <code>int</code>
     * @return
     */
    
    @Override
    public ArrayList<Utilisateurs> lister(int nb) {
        /* A FAIRE */
        return null;
    }
//    @Override
    
    /**
     * Permet de créer un nouvel utilisateur
     *
     * @param nouvelUtilisateur Objet de type <code>Utilisateurs</code>
     * @return Objet de type <code>Utilisateurs</code> muni de l'ID de la base suite à l'insertion
     */
    @Override
    public Utilisateurs creer(Utilisateurs nouvelUtilisateur) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO utilisateurs (pseudoUtilisateur, motDePasseUtilisateur, nomUtilisateur, prenomUtilisateur, emailUtilisateur) VALUES (?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, nouvelUtilisateur.getPseudo());
            requetePreparee.setString(2, nouvelUtilisateur.getMotDePasse());
            requetePreparee.setString(3, nouvelUtilisateur.getNom());
            requetePreparee.setString(4, nouvelUtilisateur.getPrenom());
            requetePreparee.setString(5, nouvelUtilisateur.getEmail());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucune lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique de l'utilisateur.");
                nouvelUtilisateur.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouvel utilisateur ajouté avec succès (N°" + nouvelUtilisateur.getId() + ").");
        return nouvelUtilisateur;
    }

    /**
     * Permet de mettre à jour un utilisateur existant
     *
     * @param monUtilisateur Objet de type <code>Utilisateurs</code>
     * @return Objet de type <code>Utilisateurs</code> muni des nouvelles informations
     */
    @Override
    public Utilisateurs mettreAjour(Utilisateurs monUtilisateur) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "UPDATE utilisateurs SET pseudoUtilisateur = ?, motDePasseUtilisateur = ?, nomUtilisateur = ?, prenomUtilisateur = ?, emailUtilisateur = ? WHERE idUtilisateur = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                    );
            requetePreparee.setString(1, monUtilisateur.getPseudo());
            requetePreparee.setString(2, monUtilisateur.getMotDePasse());
            requetePreparee.setString(3, monUtilisateur.getNom());
            requetePreparee.setString(4, monUtilisateur.getPrenom());
            requetePreparee.setString(5, monUtilisateur.getEmail());
            requetePreparee.setInt(6, monUtilisateur.getId());
            this.debug("Mise à jour -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Mise à jour -> L'utilisateur n'as pas été mis à jour.");
            }
            this.debug("Mise à jour -> L'utilisateur a été modifié avec succès.");
        } catch (SQLException erreur) {
            this.erreur("Mise à jour -> Erreur SQL !", erreur);
        }
        return monUtilisateur;
    }

    /**
     * Permet de supprimer un utilisateur existant
     *
     * @param monUtilisateur Objet de type <code>Utilisateur</code>
     */
    @Override
    public void effacer(Utilisateurs monUtilisateur) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "DELETE FROM utilisateurs WHERE idUtilisateur = ?"
                    );
            requetePreparee.setInt(1, monUtilisateur.getId());
            this.debug("Suppression -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("L'utilisateur n'as pas été supprimé.");
            } else if (lignes == 1) {
                this.debug("Suppression -> L'utilisateur n°" + monUtilisateur.getId() + " a bien été supprimé !");
            } else if (lignes > 1) {
                throw new SQLException("Plusieurs utilisateurs ont été supprimés ?? Pas normal tout ça...");
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
        super.gestionDebug.debug("DAO", "Utilisateurs -> " + message);
    }

    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Utilisateurs -> " + message, erreur);
    }
}
