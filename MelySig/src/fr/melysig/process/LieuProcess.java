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
import fr.melysig.models.ListLieux;
import fr.melysig.models.ListThemes;
import fr.melysig.models.PointsInterets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author PoooK
 */
public class LieuProcess {
    
    private static LieuProcess instance;
    
    private LieuProcess () {}
    
    public static LieuProcess getInstance() {
        if ( instance == null ) {
            instance = new LieuProcess();
        }
        return instance;
    }
    
    /**
     * Chargement des <b>informations concernant un lieux existant</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> du lieux
     * @return <b>true</b> si le lieux existe
     * <br/><b>false</b> dans le cas contraire
     */
    
     public Lieux chargerLieux(int id) {

        Debug.obtenirGestionDebug().debug("MDL","Recherche d'un lieux existant...Chargement du n° " + id);
//        Lieux resultat = this.lieuxDAO.chercher(id);
//        setId(resultat.getId());
//        setNom(resultat.getNom());
//        setCarte(resultat.getCarte());
//        setDescription(resultat.getDescription());
//        setIDUtilisateur(resultat.getIDUtilisateur());
        
        Lieux resultat = LieuxDAO.getInstance().chercher(id);
        return resultat;
    }

    public Lieux creerLieux(String nom, String carte , String description, int idUtilisateur) {

        Debug.obtenirGestionDebug().debug("MDL","Ajout d'un nouveau lieux");
        Lieux resultat = LieuxDAO.getInstance().creer(nom, carte, description, idUtilisateur);
        return resultat;
    }
    
    public Lieux mettreAjourLieu(Lieux monLieu) {

        Debug.obtenirGestionDebug().debug("MDL","Modification d'un point d'intérêt existant.");
        Lieux resultat = LieuxDAO.getInstance().mettreAjour(monLieu);
        return resultat;
    }
    
    public void setCurentPointInteret(Lieux lieu, int x,int y) {
        lieu.setCurrentPointsInterets(x, y);
        lieu.notifyObservers();
    }
    
    public void setCurentPointInteret(Lieux lieu, String libelle) {
        Iterator<PointsInterets> pois = lieu.getPointsInterets().getList().iterator();
        boolean find = false;
        while ( pois.hasNext() && !find) {
            PointsInterets poi = pois.next();
            if (poi.getLibelle().equals(libelle)) {
                lieu.setCurrentPointInteret(poi);
                find =true;
                lieu.notifyObservers();
            }
        }
        
    }
    
    public ListLieux getListLieu() {
       return LieuxDAO.getInstance().listerLieux();
    }
    
    public List<String> getListNomLieu() {
        ListLieux listLieux = getListLieu();
        List<String> listLibelles = new ArrayList<>();
        for ( Lieux lieu : listLieux.getList()) {
            listLibelles.add(lieu.getNom());
        }
        return listLibelles;
    }
    
    public void supprimerCurentPointInteret (Lieux lieux){
        
        Debug.obtenirGestionDebug().debug("MDL","Suppression du point courant" );
        PointsInterets pointCourant = lieux.getPointInteretCourant();
        lieux.getPointsInterets().delete(pointCourant);
        lieux.setCurrentPointInteret(null);
        PointsInteretsDAO.getInstance().effacer(pointCourant);
        lieux.getPointsInterets().notifyObservers();
        lieux.notifyObservers();
        
    }
    
    public ListLieux getTousLieux() {
        Debug.obtenirGestionDebug().debug("MDL","liste tous les thèmes existant.");
        return LieuxDAO.getInstance().listerLieux();
    }
    
    public Lieux chargerLieux(String lieu) {
        Debug.obtenirGestionDebug().debug("MDL","charge le thèmes existant " + lieu);
        Lieux resultat = LieuxDAO.getInstance().chercher(lieu);
        return resultat;
    }
    
    public void effacerLieu(Lieux monLieu) {

        Debug.obtenirGestionDebug().debug("MDL","Suppression d'un lieu existant.");
        LieuxDAO.getInstance().effacer(monLieu);
    }
}
