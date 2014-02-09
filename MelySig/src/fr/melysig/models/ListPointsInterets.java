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

/**
 *
 * @author PoooK
 */
public class ListPointsInterets extends Observable{
    private List<PointsInterets> pointsInterets = new ArrayList<PointsInterets>();
    
    public void add(PointsInterets theme) {
        this.pointsInterets.add(theme);
        setChanged();
    }
    
    public void delete(PointsInterets theme) {
        this.pointsInterets.remove(theme);
        setChanged();
    }
    
    public List<PointsInterets> getList(){
        return pointsInterets;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }
    
    public void setChanged() {
        super.setChanged();
    }
}
