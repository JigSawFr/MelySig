/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.mappages;

import fr.melysig.models.Lieux;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Classe DAO pour les <b>parcours</b>
 * <br />Permet de faire la liaison entre la couches de données et les objets des parcours.
 *
 * @author Julien P.
 * @since 0.4
 * @version 0.1.1
 */
public class LieuxDAO extends DAO<Lieux> {
    
       @Override
    public Lieux chercher(int id) {

        Lieux monLieux = new Lieux();
        try {
            ResultSet resultats = this.connexion
                    .createStatement(
                            ResultSet.TYPE_SCROLL_INSENSITIVE, // Le curseur peut être déplacé dans les deux sens.
                            //ResultSet.CONCUR_UPDATABLE // Possibilité modifier les données de la base via le ResultSet.
                            ResultSet.CONCUR_READ_ONLY // Lecture Uniquement
                    ).executeQuery(
                            "SELECT * FROM lieux WHERE idLieu = " + id
                    );
            if (resultats.first()) {
                monLieux = new Lieux(
                        id,
                        resultats.getString("nomLieu"),
                        resultats.getString("carteLieu"),
                        resultats.getString("descriptionLieu"),
                        resultats.getInt("idUtilisateurLieu")
                );
                this.debug("Recherche -> Lieux localisé dans la base avec succès.");
            }
            else
            {
                throw new SQLException("Aucun lieux ne correspond à l'identifiant n° " + id + " !");
            }

        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
        return monLieux;
    }

    @Override
    public Lieux creer(Lieux nouveauLieux) {

        try {
            PreparedStatement requetePreparee = this.connexion
                    .prepareStatement(
                            "INSERT INTO lieu (nomLieu,carteLieu, descriptionLieu,idUtilisateur) VALUES (?,?,?,?)",
                            Statement.RETURN_GENERATED_KEYS // Retourne les lignes affectés
                    );
            requetePreparee.setString(1, nouveauLieux.getNom());
            requetePreparee.setString(2, nouveauLieux.getCarte());
            requetePreparee.setString(3, nouveauLieux.getDescription());
            requetePreparee.setInt(4, nouveauLieux.getIDUtilisateur());

            this.debug("Ajout -> Exécution de la requete SQL...");
            int lignes = requetePreparee.executeUpdate();

            if (lignes == 0) {
                throw new SQLException("Aucun lignes affectées");
            }

            ResultSet clesGenerees = requetePreparee.getGeneratedKeys();
            if (clesGenerees.next()) {
                this.debug("Ajout -> Définition de l'identifiant unique du lieux.");
                nouveauLieux.setId(clesGenerees.getInt(1));
            } else {
                throw new SQLException("Aucune clées générées.");
            }

        } catch (SQLException erreur) {
            this.erreur("Ajout -> Erreur SQL !", erreur);
        }
        this.debug("Ajout -> Nouveau lieux ajouté avec succès (N°" + nouveauLieux.getId() + ").");
        return nouveauLieux;
    }
    
 
       @Override
        public boolean effacer(int id) {

        try {
             this.connexion
                    .createStatement()
                        .executeUpdate(
                            "DELETE * FROM lieux WHERE idLieu = " + id
                    );
        } catch (SQLException erreur) {
            this.erreur("Recherche -> Erreur SQL !", erreur);
        }
      return true;
    }
    
    
    @Override
    public Lieux mettreAjour(Lieux objet) {
        throw new UnsupportedOperationException("Fonction non implémenté."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private void debug(String message) {
        super.gestionDebug.debug("DAO", "Lieux -> " + message);
    }

    /**
     * Affichage des erreurs du Singleton en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private void erreur(String message, Exception erreur) {
        super.gestionErreurs.erreur("DAO", "Lieux -> " + message, erreur);
    }
}
