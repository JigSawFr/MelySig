/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

import fr.melysig.controleurs.*;

/**
 * <b>Controleur principal - Initialisation du MVC</b>
 * Permet la mise en relation des controleurs et des vues
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1
 */
public class MVC {

    private final boolean debug = true;
    private final boolean erreurs = true;

    private final Debug gestionDebug;
    private final Erreurs gestionErreurs;

    /**
     * Déclaration des sous-controleurs
     */
    private final UtilisateurControleur gestionUtilisateur;
    private final ParcoursControleur gestionParcours;
//    private final Parcours monParcours;

    public MVC() {

        /**
         * Initalisation de la gestion des informations de débuggage
         */
        this.gestionDebug = Debug.obtenirGestionDebug();
        this.gestionDebug.setDebug(this.debug);

        /**
         * Initialisation de la gestion des erreurs
         */
        this.gestionErreurs = Erreurs.obtenirGestionErreurs();
        this.gestionErreurs.setErreurs(this.erreurs);

        /**
         * Information de l'initialisation
         */
        this.gestionDebug.debug("MVC", "Initialisation du MVC en cours...");

        /**
         * Instanciation des sous-controleurs
         */
        this.gestionUtilisateur = new UtilisateurControleur();
        this.gestionParcours = new ParcoursControleur();

        /**
         * Information de l'initialisation
         */
        this.gestionDebug.debug("MVC", "Initialisation du MVC terminée.");

        /**
         * Notre Application
         */
        //gestionParcours.setLibelle("blabla");
        //System.out.println(gestionParcours.toString());
        //gestionParcours.chargerParcours(20);
        //System.out.println(gestionParcours.toString());
        //gestionParcours.afficherDetails();
        //gestionParcours.chargerParcours(3);
        //System.out.println(gestionParcours.toString());
        //gestionParcours.afficherDetails();
        //gestionParcours.creerParcours("Musée de Lorraine", "Y'a rien, c'est le désert numérique !");
        //System.out.println(gestionParcours.getId());
        //System.out.println(gestionParcours.toString());

        /**
         * Listing des différents parcours 
         */
        /*String mesParcours = "";
        for (int i = 1; i <= 21; i++) {
            gestionParcours.chargerParcours(i);
            mesParcours += "\n-> Parcours n°" + gestionParcours.getId() + "  - " + gestionParcours.getLibelle() + ", " + gestionParcours.getDescription();
        }
        System.out.println(mesParcours);*/
    }

}
