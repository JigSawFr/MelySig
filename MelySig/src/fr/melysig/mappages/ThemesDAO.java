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
 *
 * @author Coonax
 */
public class ThemesDAO extends DAO<Themes> {

    private static ThemesDAO instance;
    
    public static ThemesDAO getInstance() {
        if ( instance == null) {
            instance = new ThemesDAO();
        }
        return instance;
    }
    
    private ThemesDAO() {}
    
    /**
     * Permet charger les informations d'un theme
     *
     * @param id l'identifiant de type <code>int/</code>
     * @return
     */
    @Override
    public Themes chercher(int id) {

        PreparedStatement requetePreparee;
        Themes monTheme = new Themes();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM themes WHERE idTheme = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setInt(1, id);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monTheme = new Themes(
                        id,
                        resultats.getString("libelleTheme"),
                        resultats.getString("descriptionTheme")
                );
                this.debug("Recherche -> Theme localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucun theme ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monTheme;
    }
    
    public Themes chercher(String libelle) {

        PreparedStatement requetePreparee;
        Themes monTheme = new Themes();
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "SELECT * FROM themes WHERE libelleTheme = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    );
            requetePreparee.setString(1, libelle);

            this.debug("Recherche -> Exécution de la requete SQL...");
            ResultSet resultats = requetePreparee.executeQuery();

            if (resultats.first()) {
                monTheme = new Themes(
                        resultats.getInt("idTheme"),
                        resultats.getString("libelleTheme"),
                        resultats.getString("descriptionTheme")
                );
                this.debug("Recherche -> Theme localisé dans la base avec succès.");
            } else {
                throw new SQLException("Aucun theme ne correspond au libelle " + libelle + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monTheme;
    }
    
    /**
     * Permet de lister différents thèmes
     *
     * @param nb nombre de thèmes de type <code>int</code>
     * @return
     */
    @Override
    public List<Themes> lister(int nb) {
        /* A FAIRE */
        return null;
    }

    public List<Themes> listerThemes() {
                
        PreparedStatement requetePreparee;
               List<Themes> mesThemes = new ArrayList<>();
               try {
                   requetePreparee = this.connexion
                           .prepareStatement(
                                   "SELECT * FROM themes",
                                   ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                                   ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                           );

                   this.debug("Listing -> Exécution de la requete SQL...");
                   ResultSet resultats = requetePreparee.executeQuery();

                   /* On redifinié le pointeur sur l'enregistrement 0*/
                   resultats.beforeFirst();

                   while (resultats.next()) {
                       Themes monThemes = new Themes(
                               resultats.getInt("idTheme"),
                               resultats.getString("libelleTheme"),
                               resultats.getString("descriptionTheme")
                       );
                       mesThemes.add(monThemes);
                       this.debug("Listing -> Ajout du theme n°" + monThemes.getId() + " à la liste avec succès.");
                   } 

               } catch (SQLException erreur) {
                   this.erreur("Listing -> Erreur SQL !", erreur);
               }
               return mesThemes;
    }
    
    public List<PointsInterets> listerPointInteret(Themes theme, Lieux lieu) {
                
        PreparedStatement requetePreparee;
               List<PointsInterets> mesPoints = new ArrayList<>();
               try {
                   requetePreparee = this.connexion
                           .prepareStatement(
                                   "SELECT * FROM pointsInterets WHERE idLieuPointInteret = ? AND idThemePointInteret = ? ", 
                                   ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                                   ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                           );
                   requetePreparee.setInt(1, lieu.getId());
                   requetePreparee.setInt(2, theme.getId());
                   
                   this.debug("Listing -> Exécution de la requete SQL...");
                   ResultSet resultats = requetePreparee.executeQuery();
                   

                   /* On redifinié le pointeur sur l'enregistrement 0*/
                   resultats.beforeFirst();

                   while (resultats.next()) {
                       PointsInterets monPoint = new PointsInterets(
                               resultats.getInt("idPointInteret"),
                        resultats.getInt("coordonneeXPointInteret"),
                        resultats.getInt("coordonneeYPointInteret"),
                        resultats.getString("libellePointInteret"),
                        resultats.getString("descriptionPointInteret"),
                        lieu,
                        resultats.getInt("idUtilisateurPointInteret"),
                        theme
                       );
                       mesPoints.add(monPoint);
                       this.debug("Listing -> Ajout du point "+monPoint.getLibelle()+ " dans le parcourt " + theme.getLibelle() + " avec succès.");
                   } 

               } catch (SQLException erreur) {
                   this.erreur("Listing -> Erreur SQL !", erreur);
               }
               return mesPoints;
    }
    
    /**
     * Permet de créer un nouvel theme
     *
     * @param nouveauTheme Objet de type <code>Themes</code>
     * @return Objet de type <code>Themes</code> muni de l'ID de la base suite à l'insertion
     */
    @Override
    public Themes creer(Themes nouveauTheme) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO themes (libelleTheme, descriptionTheme) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, nouveauTheme.getLibelle());
            requetePreparee.setString(2, nouveauTheme.getDescription());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucune lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du theme.");
                nouveauTheme.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau theme ajouté avec succès (N°" + nouveauTheme.getId() + ").");
        return nouveauTheme;
    }
    
     public Themes creer(String libelle, String description) {
         Themes nouveauTheme = new Themes();
        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO themes (libelleTheme, descriptionTheme) VALUES (?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, libelle);
            requetePreparee.setString(2, description);

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucune lignes affectées");
            }

            nouveauTheme.setDescription(description);
            nouveauTheme.setLibelle(libelle);
            
            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du theme.");
                nouveauTheme.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau theme ajouté avec succès (N°" + nouveauTheme.getId() + ").");
        return nouveauTheme;
    }

    /**
     * Permet de mettre à jour un theme existant
     *
     * @param monTheme Objet de type <code>Themes</code>
     * @return Objet de type <code>Themes</code> muni des nouvelles informations
     */
    @Override
    public Themes mettreAjour(Themes monTheme) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "UPDATE themes SET libelleTheme = ?, descriptionTheme = ? WHERE idTheme = ?",
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                    );
            requetePreparee.setString(1, monTheme.getLibelle());
            requetePreparee.setString(2, monTheme.getDescription());
            requetePreparee.setInt(3, monTheme.getId());
            this.debug("Mise à jour -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Mise à jour -> Le theme n'as pas été mis à jour.");
            }
            this.debug("Mise à jour -> Le theme a été modifié avec succès.");
        } catch (SQLException erreur) {
            this.erreur("Mise à jour -> Erreur SQL !", erreur);
        }
        return monTheme;
    }

    /**
     * Permet de supprimer un theme existant
     *
     * @param monTheme Objet de type <code>Theme</code>
     */
    @Override
    public void effacer(Themes monTheme) {

        PreparedStatement requetePreparee;
        try {
            requetePreparee = this.connexion
                    .prepareStatement(
                            "DELETE FROM themes WHERE idTheme = ?"
                    );
            requetePreparee.setInt(1, monTheme.getId());
            this.debug("Suppression -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();
            if (lignes == 0) {
                throw new SQLException("Le theme n'as pas été supprimé.");
            } else if (lignes == 1) {
                this.debug("Suppression -> Le theme n°" + monTheme.getId() + " a bien été supprimé !");
            } else if (lignes > 1) {
                throw new SQLException("Plusieurs themes ont été supprimés ?? Pas normal tout ça...");
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
        super.gestionDebug.debug("DAO", "Themes -> " + message);
    }

    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Themes -> " + message, erreur);
    }
}
