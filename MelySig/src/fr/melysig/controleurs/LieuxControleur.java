/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */

package fr.melysig.controleurs;

import fr.melysig.models.Lieux;
///import fr.melysig.vues.xxxx;


/**
 * Classe de <b>mise en relation de la vue et le modèle des Lieux</b>
 * Permet le traitement des différents actions nécessaires
 *
 * @author Julien P.
 * @since 0.4
 * @version 0.1.1
 */
public class LieuxControleur {
    
    private Lieux modele;
    //private final xxxx vue; 
    
    /**
     * Constructeur de la classe
     */
    public LieuxControleur() {

        this.modele = new Lieux();
        ///this.vue = new xxxx();
    }
    
//    public Lieux chargerLieux(int id) {
//        this.modele = modele.chargerLieux(id);
//        return modele;
//    }
//    
//    public Lieux creerLieux(String nom, String carte , String description, int idUtilisateur) {
//        this.modele = new Lieux();
//        this.modele.setNom(nom);
//        this.modele.setCarte(carte);
//        this.modele.setDescription(description);
//        this.modele.setIDUtilisateur(idUtilisateur);
//        return modele.creerLieux(this.modele);
//    }
     public int getId() {
        return modele.getId();
    }

    public String getNom() {
        return modele.getNom();
    }
    
    public String getCarte() {
        return modele.getCarte();
    }

    public String getDescription() {
        return modele.getDescription();
    }
    
    public int getIDUtilisateur() {
        return modele.getIDUtilisateur();
    }
    
    public void setNom(String nom) {
        modele.setNom(nom);
    }
    
    public void setCarte(String carte) {
        modele.setCarte(carte);
    }
    
     public void setDescription(String description) {
        modele.setDescription(description);
    }
     
     public void setIDUtilisateur(int idUtilisateur) {
        modele.setIDUtilisateur(idUtilisateur);
    }
     
     /*public void afficherDetails() {
        vue.afficherDetails(modele.getId(), modele.getNom(), modele.getCarte(), modele.getDescription(), modele.getIDUtilisateur());
    }
     */
     
    @Override
    public String toString() {
        return modele.toString();
    } 
    
}
