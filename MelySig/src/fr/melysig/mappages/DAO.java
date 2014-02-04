/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.mappages;

import fr.melysig.bdd.MaBase;
import fr.melysig.main.Debug;
import fr.melysig.main.Erreurs;
import java.sql.Connection;

/**
 * Classe DAO mère
 * <br />Permet de faire la liaison entre la couches de données et les objets.
 *
 * @author Sébastien R.
 * @param <T> Objet concerné
 * @since 0.3
 * @version 0.1
 */
public abstract class DAO<T> {

    /**
     * Object de connexion contenant l'instance de connexion à la base de données
     */
    public Connection connexion;
    protected Debug gestionDebug = Debug.obtenirGestionDebug();
    protected Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    public DAO() {
        gestionDebug.debug("DAO", "Récupération de l'instance de connexion");
        this.connexion = MaBase.obtenirConnexion();
    }

    /**
     * Permet de récupérer un objet via son ID
     *
     * @param id ID de l'objet
     * @return
     */
    public abstract T chercher(int id);

    /**
     * Permet de créer une nouvelle entrée dans la base de données vis à vis d'un objet
     *
     * @param objet Objet concerné
     * @return
     */
    public abstract T creer(T objet);

    /**
     * Permet de mettre à jour les données d'une entrée dans la base vis à vis d'un objet
     *
     * @param objet Objet concerné
     * @return
     */
    public abstract T mettreAjour(T objet);

    /**
     * Permet la supression d'une entrée de la base de données vis à vis d'un objet
     *
     * @param objet Objet concerné
     */
    public abstract void effacer(T objet);
}
