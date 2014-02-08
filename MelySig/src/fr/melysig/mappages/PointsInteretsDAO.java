/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.mappages;

import fr.melysig.models.Lieux;
import fr.melysig.models.PointsInterets;
import fr.melysig.models.Themes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe DAO pour les <b>points d'intérêts</b>
 * <br />Permet de faire la liaison entre la couche de données et les objets des POI.
 *
 * @author Sébastien R.
 * @since 0.4
 * @version 0.1
 */
public class PointsInteretsDAO extends DAO<PointsInterets> {
    //singleton
    private static PointsInteretsDAO instance;
    private PointsInteretsDAO() {}
    
    public static PointsInteretsDAO getInstance() {
        if ( instance == null) {
            instance = new PointsInteretsDAO();
        }
        return instance;
    }
    /**
     * Permet charger les informations d'un POI
     *
     * @param id l'identifiant de type <code>int</code>
     * @return
     */
    @Override
    public PointsInterets chercher(int id) {

        PreparedStatement requetePreparee;
        PointsInterets monPointInteret = new PointsInterets();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM pointsInterets WHERE idPointInteret = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, id);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monPointInteret = new PointsInterets(
                        id,
                        resultats.getInt("coordonneeXPointInteret"),
                        resultats.getInt("coordonneeYPointInteret"),
                        resultats.getString("libellePointInteret"),
                        resultats.getString("descriptionPointInteret"),
                        LieuxDAO.getInstance().chercher(resultats.getInt("idLieuPointInteret")),
                        resultats.getInt("idUtilisateurPointInteret"),
                        ThemesDAO.getInstance().chercher(resultats.getInt("idThemePointInteret"))
                );
                this.debug("Recherche -> Point d'intérêt localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucun point d'intérêt ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monPointInteret;
    }

    /**
     * Permet de lister différents POI
     *
     * @param nb nombre de POI de type <code>int</code>
     * @return
     */
    @Override
    public List<PointsInterets> lister(int nb) {

        PreparedStatement requetePreparee;
        List<PointsInterets> mesPointsInterets = new ArrayList<>();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM pointsInterets LIMIT 0,?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, nb);

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();
            
            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            if (resultats.next()) {
                PointsInterets monPointInteret = new PointsInterets(
                        resultats.getInt("idPointInteret"),
                        resultats.getInt("coordonneeXPointInteret"),
                        resultats.getInt("coordonneeYPointInteret"),
                        resultats.getString("libellePointInteret"),
                        resultats.getString("descriptionPointInteret"),
                        LieuxDAO.getInstance().chercher(resultats.getInt("idLieuPointInteret")),
                        resultats.getInt("idUtilisateurPointInteret"),
                        ThemesDAO.getInstance().chercher(resultats.getInt("idThemePointInteret"))
                );
                mesPointsInterets.add(monPointInteret);
                this.debug("Listing -> Ajout du point d'intérêt n°" + monPointInteret.getId() + " à la liste avec succès.");
            } else {
                throw new SQLException("Aucun point d'intérêt à lister !");
            }

        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesPointsInterets;
    }

    public List<PointsInterets> listerPOILieux(Lieux lieux) {

        PreparedStatement requetePreparee;
        List<PointsInterets> mesPointsInterets = new ArrayList<>();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM pointsInterets WHERE idlieuPointInteret = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, lieux.getId());

            this.debug("Listing -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();
            
            /* On redifinié le pointeur sur l'enregistrement 0*/
            resultats.beforeFirst();

            while (resultats.next()) {
                PointsInterets monPointInteret = new PointsInterets(
                        resultats.getInt("idPointInteret"),
                        resultats.getInt("coordonneeXPointInteret"),
                        resultats.getInt("coordonneeYPointInteret"),
                        resultats.getString("libellePointInteret"),
                        resultats.getString("descriptionPointInteret"),
                        lieux,
                        resultats.getInt("idUtilisateurPointInteret"),
                        ThemesDAO.getInstance().chercher(resultats.getInt("idThemePointInteret"))
                );
                mesPointsInterets.add(monPointInteret);
                this.debug("Listing -> Ajout du point d'intérêt n°" + monPointInteret.getId() + " à la liste avec succès.");
            } 
        } catch (SQLException erreur) {
            this.erreur("Listing -> Erreur SQL !", erreur);
        }
        return mesPointsInterets;
    }
    
    /**
     * Permet de créer un nouveau POI
     *
     * @param nouveauPointInteret Objet de type <code>PointsInterets</code>
     * @return Objet de type <code>PointsInterets</code> muni de l'ID de la base suite à l'insertion
     */
    @Override
    public PointsInterets creer(PointsInterets nouveauPointInteret) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO pointsInterets (coordonneeXPointInteret, coordonneeYPointInteret, libellePointInteret, descriptionPointInteret, idLieuPointInteret, idUtilisateurPointInteret, idThemePointInteret) VALUES (?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setInt(1, nouveauPointInteret.getX());
            requetePreparee.setInt(2, nouveauPointInteret.getY());
            requetePreparee.setString(3, nouveauPointInteret.getLibelle());
            requetePreparee.setString(4, nouveauPointInteret.getDescription());
            requetePreparee.setInt(5, nouveauPointInteret.getLieu().getId());
            requetePreparee.setInt(6, nouveauPointInteret.getUtilisateur());
            requetePreparee.setInt(7, nouveauPointInteret.getTheme().getId());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucune lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du point d'intérêt.");
                nouveauPointInteret.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau point d'intérêt ajouté avec succès (N°" + nouveauPointInteret.getId() + ").");
        return nouveauPointInteret;
    }

        public PointsInterets creer(int x, int y, String libelle, String description, Lieux lieu, int utilisateur, Themes themes) {
            PointsInterets nouveauPointInteret = new PointsInterets();
        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO pointsInterets (coordonneeXPointInteret, coordonneeYPointInteret, libellePointInteret, descriptionPointInteret, idLieuPointInteret, idUtilisateurPointInteret, idThemePointInteret) VALUES (?,?,?,?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setInt(1, x);
            requetePreparee.setInt(2, y);
            requetePreparee.setString(3, libelle);
            requetePreparee.setString(4, description);
            requetePreparee.setInt(5, lieu.getId());
            requetePreparee.setInt(6, utilisateur);
            requetePreparee.setInt(7, themes.getId());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucune lignes affectées");
            }

            nouveauPointInteret.setLibelle(libelle);
            nouveauPointInteret.setX(x);
            nouveauPointInteret.setY(y);
            nouveauPointInteret.setLieu(lieu);
            nouveauPointInteret.setTheme(themes);
            
            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du point d'intérêt.");
                nouveauPointInteret.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau point d'intérêt ajouté avec succès (N°" + nouveauPointInteret.getId() + ").");
        return nouveauPointInteret;
    }
    
    /**
     * Permet de mettre à jour un POI existant
     *
     * @param monPointInteret Objet de type <code>PointsInterets</code>
     * @return Objet de type <code>PointsInterets</code> muni des nouvelles informations
     */
    @Override
    public PointsInterets mettreAjour(PointsInterets monPointInteret) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "UPDATE pointsInterets SET coordonneeXPointInteret = ?, coordonneeYPointInteret = ?, libellePointInteret = ?, descriptionPointInteret = ?, idLieuPointInteret = ?, idUtilisateurPointInteret = ?, idThemePointInteret = ? WHERE idPointInteret = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                    );
            requetePreparee.setInt(1, monPointInteret.getX());
            requetePreparee.setInt(2, monPointInteret.getY());
            requetePreparee.setString(3, monPointInteret.getLibelle());
            requetePreparee.setString(4, monPointInteret.getDescription());
            requetePreparee.setInt(5, monPointInteret.getLieu().getId());
            requetePreparee.setInt(6, 1);
            requetePreparee.setInt(7, monPointInteret.getTheme().getId());
            requetePreparee.setInt(8, monPointInteret.getId());

            this.debug("Mise à jour -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Mise à jour -> Le point d'intérêt n'as pas été mis à jour.");
            }
            this.debug("Mise à jour -> Le point d'intérêt a été modifié avec succès.");
        } catch (SQLException erreur) {
            this.erreur("Mise à jour -> Erreur SQL !", erreur);
        }
        return monPointInteret;
    }

    /**
     * Permet de supprimer un utilisateur existant
     *
     * @param monPointInteret Objet de type <code>PointsInterets</code>
     */
    @Override
    public void effacer(PointsInterets monPointInteret) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "DELETE FROM pointsInterets WHERE idPointInteret = ?"
                    );
            requetePreparee.setInt(1, monPointInteret.getId());
            this.debug("Suppression -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Le point d'intérêt n'as pas été supprimé.");
            } else if (lignes == 1) {
                this.debug("Suppression -> Le point d'intérêt n°" + monPointInteret.getId() + " a bien été supprimé !");
            } else if (lignes > 1) {
                throw new SQLException("Plusieurs points d'intérêts ont été supprimés ?? Pas normal tout ça...");
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
        super.gestionDebug.debug("DAO", "Points d'intérêts -> " + message);
    }

    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Points d'intérêts -> " + message, erreur);
    }
}
