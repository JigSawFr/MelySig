/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.process;

import fr.melysig.main.Debug;
import fr.melysig.mappages.LieuxDAO;
import fr.melysig.mappages.PointsInteretsDAO;
import fr.melysig.mappages.ThemesDAO;
import fr.melysig.models.Lieux;
import fr.melysig.models.ListThemes;
import fr.melysig.models.PointsInterets;
import fr.melysig.models.Themes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PoooK
 */
public class ThemeProcess {
   
   //singleton
   private static ThemeProcess pointInteretProcess;
   
   public static ThemeProcess getInstance() {
       if (pointInteretProcess == null) {
           pointInteretProcess = new ThemeProcess();
       }
       return pointInteretProcess;
   }
    
   private ThemeProcess () {
   }
   
   public Themes creerTheme(String libelleTheme, String description){
       Debug.obtenirGestionDebug().debug("MDL", "Theme -> créer un théme avec libelle " + libelleTheme);
       
       Themes themes = ThemesDAO.getInstance().creer(libelleTheme, description);
       return themes;
   }
   
   /**
     * Chargement des <b>informations concernant un POI existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du POI
     * @return objet de type <code>PointsInterets</code>
     */
    public Themes chargerThemes(int id) {

        Debug.obtenirGestionDebug().debug("MDL", "Themes -> Recherche d'un thème existant...Chargement du n° " + id);
        Themes resultat = ThemesDAO.getInstance().chercher(id);
        return resultat;
    } 
  

    /**
     * Chargement des <b>informations de plusieurs POI existants</b>
     * dans la base de données
     *
     * @param nb Identifiant <b>unique</b> du POI
     * @return objet de type <code>PointsInterets</code>
     */
//    public PointsInterets chargerPointInteret(int nb) {
//
//        debug("Recherche d'un point d'intérêt existant...Chargement du n° " + id);
//        PointsInterets resultat = this.pointsInteretsDAO.chercher(id);
//        return resultat;
//    }

    /**
     * Ajouter un <b>nouveau POI</b> dans la base de données
     *
     * @param nouveauPointInteret objet PointsInterets contenant les données à ajouter
     * @return objet PointsInterets avec l'identifiant unique en base de données
     */
//    public PointsInterets creerPointInteret(PointsInterets nouveauPointInteret) {
//
//        Debug.obtenirGestionDebug().debug("MDL","Ajout d'un nouveau point d'intérêt.");
//        PointsInterets resultat = PointsInteretsDAO.getInstance().creer(nouveauPointInteret);
//        return resultat;
//    }

    /**
     * Modifier un <b>POI existant</b> dans la base de données
     *
     * @param monPointInteret objet PointsInterets contenant les données à modifier
     * @return objet PointsInterets avec l'identifiant unique en base de données
     */
    public Themes mettreAjourThemes(Themes monThemes) {

        Debug.obtenirGestionDebug().debug("MDL","Modification d'un point d'intérêt existant.");
        Themes resultat = ThemesDAO.getInstance().mettreAjour(monThemes);
        return resultat;
    }

    /**
     * Permet de <b>supprimer un POI existant</b>
     *
     * @param monPointInteret objet PointsInterets contenant l'id du POI à supprimer
     */
    public void effacerThemes(Themes monThemes) {

        Debug.obtenirGestionDebug().debug("MDL","Suppression d'un thème existant.");
        ThemesDAO.getInstance().effacer(monThemes);
    }
    
    public ListThemes getTousThemes() {
        Debug.obtenirGestionDebug().debug("MDL","liste tous les thèmes existant.");
        return ThemesDAO.getInstance().listerThemes();
    }

    public Themes chargerThemes(String theme) {
        Debug.obtenirGestionDebug().debug("MDL","charge le thèmes existant " + theme);
        Themes resultat = ThemesDAO.getInstance().chercher(theme);
        return resultat;
    }
}
