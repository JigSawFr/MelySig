/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.process;

import fr.melysig.main.Debug;
import fr.melysig.mappages.ThemesDAO;
import fr.melysig.models.Lieux;
import fr.melysig.models.Parcours;
import fr.melysig.models.Themes;
import java.util.Observer;

/**
 *
 * @author PoooK
 */
public class ParcourProcess {
    private static ParcourProcess instance;
    private ParcourProcess () {}
    
    public static ParcourProcess getInstance() {
        if ( instance == null ) {
            instance = new ParcourProcess();
        }
        return instance;
    }
    
    
    public void chargerParcourCourant(Observer observer, Lieux lieu,String theme) {
        Debug.obtenirGestionDebug().debug("MDL","Recherche d'un parcour existant...Chargement du parcour " + theme);
        Themes themeObject = ThemeProcess.getInstance().chargerThemes(theme);
        Parcours parcour = new Parcours();
        parcour.setDescription(themeObject.getDescription());
        parcour.setLibelle(themeObject.getLibelle());
        parcour.setListPointInterets(ThemesDAO.getInstance().listerPointInteret(themeObject, lieu));
        lieu.setCurrentParcour(parcour);
        lieu.setModificationParcourCourant();
        lieu.notifyObservers();
    }
    
    
    
}
