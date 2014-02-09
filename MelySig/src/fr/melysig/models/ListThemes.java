/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author PoooK
 */
public class ListThemes extends Observable {
    
    private List<Themes> themes = new ArrayList<Themes>();
    
    public void add(Themes theme) {
        this.themes.add(theme);
        setChanged();
    }
    
    public void delete(Themes theme) {
        this.themes.remove(theme);
        setChanged();
    }
    
    public List<Themes> getList(){
        return themes;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
}
