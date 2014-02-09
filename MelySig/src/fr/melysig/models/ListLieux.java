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
public class ListLieux extends Observable {
    
    private List<Lieux> lieux = new ArrayList<Lieux>();
    
    public void add(Lieux theme) {
        this.lieux.add(theme);
        setChanged();
    }
    
    public void delete(Lieux theme) {
        this.lieux.remove(theme);
        setChanged();
    }
    
    public List<Lieux> getList(){
        return lieux;
    }
}
