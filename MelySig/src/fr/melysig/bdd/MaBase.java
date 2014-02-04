/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.bdd;

import fr.melysig.main.Debug;
import fr.melysig.main.Erreurs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>Singleton</b> de gestion de la base de données
 * <br />Permet d'intéragir avec la base de données avec une <b>instance unique</b>
 * <br /><b>ATTENTION !</b> <u>Le multi-threading n'est pas géré.</u>
 *
 * @author Sébastien R.
 * @since 0.2
 * @version 0.2
 */
public class MaBase {

    /**
     * Driver nécessaire pour la construction du lien
     */
    private static final String lien = "jdbc:mysql";
    /**
     * Driver bdd pour Java
     */
    private static final String driver = "com.mysql.jdbc.Driver";
    /**
     * Serveur ou hôte de la base
     */
    private static final String serveur = "localhost";
    /**
     * Port du serveur
     */
    private static final String port = "3306";
    /**
     * Nom de la base
     */
    private static final String base = "Melany";
    /**
     * Identifiant nécessaire pour l'accès
     */
    private static final String identifiant = "root";
    /**
     * Mot de passe nécessaire pour l'accès
     */
    private static final String motDePasse = "root";
    /**
     * DEBUG : Permet à la classe d'être plus bavarde sur son travail (true/false)
     *
     * @deprecated Remplacé
     */
    private static final boolean debug = true;

    /**
     * Objet Connection
     * <br />Membre statique ne contenant qu'une seule instance de la classe MaBase
     */
    private static Connection connexion = null;
    private static String construction;
    /**
     * Objet Déclaration
     */
    private Statement declaration;
    
    private static final Debug gestionDebug = Debug.obtenirGestionDebug();
    private static final Erreurs gestionErreurs = Erreurs.obtenirGestionErreurs();

    /**
     * Constructeur de la classe
     * <br />Celui-ci est vide afin d'empêcher MaBase d'être instancié par d'autres classes
     */
    private MaBase() {
    }

    /**
     * Chargement du driver nécessaire pour JAVA
     * <br /><i>Dans notre cas nous utilisons JDBC</i>
     */
    private static void chargerDriver() {

        try {
            debug("Chargement du driver...");
            Class.forName(driver);
        } catch (ClassNotFoundException erreur) {
            gestionErreur("Impossible de charger le driver " + driver, erreur);
        }
    }

    /**
     * Formatage du lien pour la connexion à la base
     * <br />Récupération de l'origine <code>lien</code>, du serveur <code>serveur</code>, du <code>port</code> ainsi que du nom de la base de données <code>base</code> afin de formater le lien nécessaire à la connexion.
     *
     * @return Un <code>String</code> contenant le lien formaté pour établir la connexion
     *
     */
    private static String obtenirLien() {
        construction = lien + "://" + serveur + ":" + port + "/" + base;
        debug("Création du lien de connexion... << " + construction + " >>");
        return construction;
    }

    /**
     * Chargement de la connexion
     */
    private static void chargerConnexion() {
        try {
            debug("Connexion à la base de données << " + base + " >>...");
            debug("Création d'une nouvelle instance de connexion.");
            connexion = DriverManager.getConnection(obtenirLien(), identifiant, motDePasse);
            debug("Connexion établie avec succès.");
        } catch (SQLException erreur) {
            gestionErreur("Impossible de se connecter à la base de données " + obtenirLien(), erreur);
        }
    }

    /**
     * Fermeture de la connexion à la base
     *
     * <br />Se charge de vérifier au préalable si une connexion est ouverte
     */
    public static void fermerConnexion() {

        if (null == connexion) {
            gestionErreur("Aucune connexion ouverte", null);
        } else {
            try {
                debug("Fermeture de la connexion...");
                connexion.close();
                connexion = null;
                debug("Connexion fermée avec succès.");
            } catch (SQLException erreur) {
                gestionErreur("Impossible de fermer la connexion", erreur);
            }
        }
    }

    /**
     * Retourne l'instance du singleton de la base de données
     * <br />La méthode vérifie si une instance de la connexion existe
     * <br />Dans le cas contraire, elle charge le driver via <code>chargerDriver()</code>
     * <br />Puis instancie la connexion via <code>chargerConnexion</code>
     *
     * @return L'objet de type <code>connexion</code> qui représente l'instance de connexion à la base de données
     *
     */
    public static Connection obtenirConnexion() {

        if (null == connexion) {
            debug("Aucune connexion existante.");
            debug("Tentative d'établissement d'une connexion...");
            chargerDriver();
            chargerConnexion();
        } else {
            debug("Une connexion a déjà été établie ...");
            debug("Renvoie de l'instance actuelle de la connexion.");
        }
        return connexion;
    }

    /**
     * Effectue une requête sur la base de données
     *
     * @param requete Requête de type <code>String</code> à exécuter
     * @return Un objet de type <code>ResultSet</code> contenant les résultats
     * <br /><code>Null</code> si aucun résultat
     * @throws SQLException
     */
    public ResultSet requete(String requete) throws SQLException {
        declaration = MaBase.connexion.createStatement();
        ResultSet resultat = declaration.executeQuery(requete);
        return resultat;
    }

    /**
     * Méthode d'insertion de données dans la base
     *
     * @param requeteInsertion Requête de type <code>String</code> à exécuter
     * @return <code>true</code> si la requête s'est bien éxecutée
     * <br /><code>false</code> si un problème est survenu
     * @throws SQLException Erreurs SQL
     */
    public int insertion(String requeteInsertion) throws SQLException {

        declaration = MaBase.connexion.createStatement();
        int resultat = declaration.executeUpdate(requeteInsertion);
        return resultat;

    }

    /**
     * Affichage des erreurs du Singleton en console
     *
     * @param message Message d'erreur
     * @param erreur Code d'erreur
     */
    private static void gestionErreur(String message, Exception erreur) {
        gestionErreurs.erreur("SQL", message, erreur);
    }

    /**
     * Affichage d'informations de débuggage du Singleton en console
     *
     * @param message Message de débuggage
     */
    private static void debug(String message) {
        gestionDebug.debug("SQL", message);
    }

}
