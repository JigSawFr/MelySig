/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.models;

import fr.melysig.main.Debug;
import fr.melysig.main.Erreurs;
import fr.melysig.mappages.DAO;
import fr.melysig.mappages.ActualitesDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 * Classe de <b>traitement des Actualités</b>
 * Permet le traitement des différents actualités
 *
 * @author Gérold M., Sébastien R.
 * @since 0.4
 * @version 0.2
 */
public class Actualites extends Observable {

    /**
     * Identifiant Unique de l'actualité
     */
    private int id;
    /**
     * Libéllé de l'actualité
     */
    private String libelle;
    /**
     * Description de l'actualité
     */
    private String description;
    /**
     * Utilisateur qui a ajouté la news
     */
    private int utilisateur;
    /**
     * Tableau contenant la liste des points d'intérêts d'un parcours
     */
    public ListModel<Actualites> listeModeleActualites = null;
    private ArrayList<Actualites> listeActualites = new ArrayList<>();

    /**
     * Création de l'objet DAO pour les parcours
     */
    DAO<Actualites> actualitesDAO;
    ActualitesDAO accessDirectDAO;

    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    /**
     * Constructeur de la classe Parcours (Polymorphisme)
     */
    public Actualites() {
        this.id = 0;
        this.libelle = null;
        this.description = null;
        this.utilisateur = 0;
        this.actualitesDAO = new ActualitesDAO();
        this.accessDirectDAO = new ActualitesDAO();
    }

    /**
     * Constructeur de la classe Actualités (Polymorphisme)
     *
     * @param id
     * @param libelle
     * @param description
     * @param utilisateur
     */
    public Actualites(int id, String libelle, String description, int utilisateur/*, ArrayList<Actualites> listeActualites*/) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.utilisateur = utilisateur;
        /*this.listeActualites = listeActualites;*/
        this.actualitesDAO = new ActualitesDAO();
        this.accessDirectDAO = new ActualitesDAO();
    }

    /**
     * Chargement des <b>informations concernant une actualité existante</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> de l'actualité
     * @return objet de type <code>actualités</code>
     */
    public Actualites chargerActualite(int id) {

        debug("Recherche d'une actualité existante...Chargement du n° " + id);
        Actualites resultat = this.actualitesDAO.chercher(id);
        return resultat;
    }

    /**
     * Chargement des <b>informations concernant une actualité existante</b>
     * dans la base de données
     *
     * @param id Identifiant <b>unique</b> de l'actualité
     * @return objet de type <code>actualités</code>
     */
    public ListModel<Actualites> listerActualite(int nb) {

        debug("Sélection des dernières actualités avec un maximum de " + id);
        
        ArrayList<Actualites> resultat = accessDirectDAO.listerObjets(nb);
        ListModel<Actualites> listeModeleActualites = new ListeModeleActualite(resultat);
 
        
        this.setChanged();
        return listeModeleActualites;
    }

    /**
     * Ajouter une <b>nouvelle actualité/b> dans la base de données
     *
     * @param nouvelleActualite objet actualites contenant les données à ajouter
     * @return objet actualites avec l'identifiant unique en base de données
     */
    public Actualites creerActualite(Actualites nouvelleActualite) {

        debug("Ajout d'un nouveau parcours.");
        Actualites resultat = this.actualitesDAO.creer(nouvelleActualite);
        return resultat;
    }

    /**
     * Modifier une <b>actualite existante</b> dans la base de données
     *
     * @param monActualite objet actualites contenant les données à modifier
     * @return objet actualites avec l'identifiant unique en base de données
     */
    public Actualites mettreAjourActualite(Actualites monActualite) {

        debug("Modification d'une actualité existante.");
        Actualites resultat = this.actualitesDAO.mettreAjour(monActualite);
        return resultat;
    }

    /**
     * Permet de <b>supprimer une actualité existante</b>
     *
     * @param monActualite objet actualites contenant l'id d l'actualité à supprimer
     */
    public void effacerActualite(Actualites monActualite) {

        debug("Suppression d'une actualité existante.");
        this.actualitesDAO.effacer(monActualite);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ArrayList<Actualites> getListeActualites() {
        return listeActualites;
    }

    public void setListeActualites(ArrayList<Actualites> listeActualites) {
        this.listeActualites = listeActualites;
    }

    /**
     * Affichage des erreurs en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void erreur(String message, Exception erreur) {
        gestionErreurs.erreur("MDL", "Actualités -> " + message, erreur);
    }

    /**
     * Affichage d'informations de débuggage en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("MDL", "Actualités -> " + message);
    }

    /**
     * Méthode d'affichage de l'objet Parcours
     *
     * @return <code>null</code>
     */
    @Override
    public String toString() {
        debug("Objet -> Identifiant : " + this.getId() + " | Libellé : " + this.getLibelle() + " | Description : " + this.getDescription() + " | Utilisateur : " + this.getUtilisateur());
        return libelle;
    }
    public class ListeModeleActualite extends AbstractListModel<Actualites> {
        List<Actualites> resultat;
        public ListeModeleActualite (List<Actualites> actus) {
            resultat = actus;
        }
    
        @Override
        public int getSize() {
            return resultat.size();
        
        }

        @Override
        public Actualites getElementAt(int i) {
            return resultat.get(i);
        }
        
        public void remove(JList list, int i)
        {
            resultat.remove(i);
            this.fireContentsChanged(list, 0, getSize());
        }
        
        public void add(JList list, Actualites actu) {
            resultat.add(actu);
            this.fireContentsChanged(list, 0, getSize());
        }
    }
}
