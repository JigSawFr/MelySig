/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.mappages;

import fr.melysig.models.Actualites;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO pour les <b>actualités</b>
 * <br />Permet de faire la liaison entre la couches de données et les objets des actualités.
 *
 * @author Gérold M., Sébastien R.
 * @since 0.4
 * @version 0.2
 */
public class ActualitesDAO extends DAO<Actualites> {

    /**
     * Permet charger les informations d'une actualité
     *
     * @param id l'identifiant de type <code>int/</code>
     * @return
     */
    @Override
    public Actualites chercher(int id) {

        PreparedStatement requetePreparee;
        Actualites monActualite = new Actualites();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM actualites WHERE idActualite = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, id);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monActualite = new Actualites(
                        id,
                        resultats.getString("libelleActualite"),
                        resultats.getString("descriptionActualite"),
                        resultats.getInt("idUtilisateurActualite")
                );
                this.debug("Recherche -> Actualité localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucune actualité ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monActualite;
    }

    /**
     * Permet de lister différents thèmes
     *
     * @param nb nombre de thèmes de type <code>int</code>
     * @return
     */
    @Override
    public ArrayList<Actualites> lister(int nb) {

        PreparedStatement requetePreparee;
        ArrayList<Actualites> mesActualites = new ArrayList<>();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM actualites LIMIT 0,?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, nb);

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            while (resultats.next()) {
                Actualites monActualite = new Actualites(
                        resultats.getInt("idActualite"),
                        resultats.getString("libelleActualite"),
                        resultats.getString("descriptionActualite"),
                        resultats.getInt("idUtilisateurActualite")
                );
                mesActualites.add(monActualite);
                this.debug("Listing -> Ajout de l'actualité n°" + monActualite.getId() + " à la liste avec succès.");
            } /*else {
             throw new SQLException("Aucune actualités d'intérêt à lister !");
             }*/

        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesActualites;
    }

    public ArrayList<ArrayList<String>> listerSimple(int nb) {
        PreparedStatement requetePreparee;
        ArrayList<ArrayList<String>> mesActualites = new ArrayList<>();
        int i = 0;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM actualites LIMIT 0,?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, nb);

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            while (resultats.next()) {
                ArrayList<String> monActualite = new ArrayList<>();
                String libelle = resultats.getString("libelleActualite");
                String description = resultats.getString("descriptionActualite");
                monActualite.add(libelle);
                monActualite.add(description);
                mesActualites.add(monActualite);
                this.debug("Listing -> Ajout de l'actualité n°" + resultats.getInt("idActualite") + " à la liste avec succès.");
            } /*else {
             throw new SQLException("Aucune actualités d'intérêt à lister !");
             }*/

        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesActualites;
    }
    
     public ArrayList<Actualites> listerObjets(int nb) {
        PreparedStatement requetePreparee;
        ArrayList<Actualites> mesActualites = new ArrayList<>();
        int i = 0;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM actualites LIMIT 0,?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, nb);

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            while (resultats.next()) {
                Actualites monActualite = new Actualites(
                        resultats.getInt("idActualite"),
                        resultats.getString("libelleActualite"),
                        resultats.getString("descriptionActualite"),
                        resultats.getInt("idUtilisateurActualite")
                );
                mesActualites.add(monActualite);
//                mon
//                String libelle = resultats.getString("libelleActualite");
//                String description = resultats.getString("descriptionActualite");
//                monActualite.add(libelle);
//                monActualite.add(description);
//                mesActualites.add(monActualite);
                this.debug("Listing -> Ajout de l'actualité n°" + resultats.getInt("idActualite") + " à la liste avec succès.");
            } /*else {
             throw new SQLException("Aucune actualités d'intérêt à lister !");
             }*/

        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesActualites;
    }

    /**
     * Permet de créer une nouvelle actualité
     *
     * @param nouvelleActualite Objet de type <code>Actualites</code>
     * @return Objet de type <code>Actualites</code> muni de l'ID de la base suite à l'insertion
     */
    @Override
    public Actualites creer(Actualites nouvelleActualite) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO actualites (libelleActualite, descriptionActualite, idUtilisateurActualite) VALUES (?,?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, nouvelleActualite.getLibelle());
            requetePreparee.setString(2, nouvelleActualite.getDescription());
            requetePreparee.setInt(3, nouvelleActualite.getUtilisateur());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucun lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique de l'actualité.");
                nouvelleActualite.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouvelle actualité ajoutée avec succès (N°" + nouvelleActualite.getId() + ").");
        return nouvelleActualite;
    }

    /**
     * Permet de mettre à jour une actualité existante
     *
     * @param monActualite Objet de type <code>Actualites</code>
     * @return Objet de type <code>Actualites</code> muni des nouvelles informations
     */
    @Override
    public Actualites mettreAjour(Actualites monActualite) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "UPDATE actualites SET libelleActualite = ?, descriptionActualite = ? WHERE idActualite = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                    );
            requetePreparee.setString(1, monActualite.getLibelle());
            requetePreparee.setString(2, monActualite.getDescription());
            requetePreparee.setInt(3, monActualite.getId());

            this.debug("Mise à jour -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Mise à jour -> L'actualité n'as pas été mise à jour.");
            }
            this.debug("Mise à jour -> L'actualité a été modifiée avec succès.");
        } catch (SQLException erreur) {
            this.erreur("Mise à jour -> Erreur SQL !", erreur);
        }
        return monActualite;
    }

    /**
     * Permet de supprimer un parcours existant
     *
     * @param monActualite Objet de type <code>Parcours</code>
     */
    @Override
    public void effacer(Actualites monActualite) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "DELETE FROM actualites WHERE idActualite = ?"
                    );
            requetePreparee.setInt(1, monActualite.getId());
            this.debug("Suppression -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("L'actualité n'as pas été supprimée.");
            } else if (lignes == 1) {
                this.debug("Suppression -> L'actualité n°" + monActualite.getId() + " a bien été supprimée !");
            } else if (lignes > 1) {
                throw new SQLException("Plusieurs actualités ont été supprimées ?? Pas normal tout ça...");
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
        super.gestionDebug.debug("DAO", "Actualités -> " + message);
    }

    /**
     * Affichage des erreurs du en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Actualités -> " + message, erreur);
    }
}
