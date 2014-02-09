/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.process;

import fr.melysig.main.Debug;
import fr.melysig.main.MVC;
import fr.melysig.mappages.LieuxDAO;
import fr.melysig.mappages.PointsInteretsDAO;
import fr.melysig.mappages.ThemesDAO;
import fr.melysig.models.Lieux;
import fr.melysig.models.ListPointsInterets;
import fr.melysig.models.ListThemes;
import fr.melysig.models.PointsInterets;
import fr.melysig.models.Themes;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PoooK
 */
public class PointInteretProcess {
   
   //singleton
   private static PointInteretProcess pointInteretProcess;
   
   public static PointInteretProcess getInstance() {
       if (pointInteretProcess == null) {
           pointInteretProcess = new PointInteretProcess();
       }
       return pointInteretProcess;
   }
    
   private PointInteretProcess () {
   }
   
   public void creerPointInteret(Lieux lieu, String libellePointInteret, int x, int y){
       Debug.obtenirGestionDebug().debug("MDL", "Points d'intérêts -> créer un point d'intérêt avec libelle " + libellePointInteret);
       ListThemes themes = ThemesDAO.getInstance().listerThemes();
       PointsInterets pointInteret = new PointsInterets();
       pointInteret.setLibelle(libellePointInteret);
       pointInteret.setX(x);
       pointInteret.setY(y);
       pointInteret.setLieu(lieu);
       pointInteret.setTheme((themes.getList().size() > 0)? themes.getList().get(0) : null);
       pointInteret.setUtilisateur(MVC.obtenirMVC().getIdUtilisateur());
       
       lieu.setCurrentPointInteret(pointInteret);
       ListPointsInterets pi = lieu.getPointsInterets();
       pi.add(pointInteret);
       
       PointsInteretsDAO.getInstance().creer(pointInteret);
       LieuProcess.getInstance().mettreAjourLieu(lieu);
       pi.notifyObservers();
       lieu.notifyObservers();
   }
   
   /**
     * Chargement des <b>informations concernant un POI existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du POI
     * @return objet de type <code>PointsInterets</code>
     */
    public PointsInterets chargerPointInteret(int id) {

        Debug.obtenirGestionDebug().debug("MDL", "Points d'intérêts -> Recherche d'un point d'intérêt existant...Chargement du n° " + id);
        PointsInterets resultat = PointsInteretsDAO.getInstance().chercher(id);
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
    public PointsInterets mettreAjourPointInteret(Lieux lieu, PointsInterets monPointInteret) {

        Debug.obtenirGestionDebug().debug("MDL","Modification d'un point d'intérêt existant.");
        PointsInterets resultat = PointsInteretsDAO.getInstance().mettreAjour(monPointInteret);
        lieu.setCurrentPointInteret(monPointInteret);
        lieu.notifyObservers();
        return resultat;
    }

    public void filtre(Lieux lieu, String cherche) {
        if( cherche == null || cherche.trim().equals("")){
            lieu.setPointsInteretsFiltre(null);
        }
        List<PointsInterets> list = PointsInteretsDAO.getInstance().chercherContenue(lieu, cherche);
        lieu.setPointsInteretsFiltre(list);
                
        lieu.getPointsInterets().notifyObservers();
        lieu.notifyObservers();
    }
}
