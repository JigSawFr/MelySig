/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

import fr.melysig.controleurs.*;
import fr.melysig.models.*;
import fr.melysig.process.LieuProcess;
import fr.melysig.vues.*;

/**
 * <b>Controleur principal - Initialisation du MVC</b>
 * Permet la mise en relation des controleurs et des vues
 *
 * @author Sébastien R.
 * @since 0.3
 * @version 0.1.1
 */
public class MVC {

    /**
     * Variables de choix d'affichage
     */
    private final boolean debug = true;
    private final boolean erreurs = true;

    /**
     * Objet Singleton issus de méthodes génériques
     */
    private final Debug gestionDebug;
    private final Erreurs gestionErreurs;

    /**
     * Déclaration des sous-controleurs
     */
//    private final UtilisateursControleur gestionUtilisateurs;
//    private final ParcoursControleur gestionParcours;

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
//        this.gestionUtilisateurs = new UtilisateursControleur();
//        this.gestionParcours = new ParcoursControleur();
        ParcoursVue vueParcours = new ParcoursVue();
        Parcours modeleParcours = new Parcours();
        ParcoursControleur controleurParcours = new ParcoursControleur(modeleParcours, vueParcours);
        
        UtilisateursVue vueUtilisateurs = new UtilisateursVue();
        Utilisateurs modeleUtilisateurs = new Utilisateurs();
        UtilisateursControleur controleurUtilisateurs = new UtilisateursControleur(modeleUtilisateurs, vueUtilisateurs);

        
        
        Lieux lieux = LieuProcess.getInstance().chargerLieux(1);
        // Appel de la vue.
        ConsultationVue consultationVue = new ConsultationVue(lieux);

        /**
         * Information de l'initialisation
         */
        this.gestionDebug.debug("MVC", "Initialisation du MVC terminée.");

        /**
         * Notre Application
         */
        
        /* ---- Commandes de test ---- */
//        gestionUtilisateurs.chargerUtilisateur(2);
//        gestionUtilisateurs.toString();
//        gestionUtilisateurs.toString();
//        gestionUtilisateurs.mettreAjourUtilisateur(gestionUtilisateurs.getPseudo(), "tete de gland", gestionUtilisateurs.getNom(), gestionUtilisateurs.getPrenom(), gestionUtilisateurs.getEmail());
//        gestionUtilisateurs.chargerUtilisateur(3);
//        gestionUtilisateurs.effacerUtilisateur();
//        gestionParcours.setLibelle("blabla");
//        System.out.println(gestionParcours.toString());
//        gestionParcours.creerParcours("Musées des canailles", "On passe tout au karcher !");
//        gestionParcours.mettreAjourParcours(gestionParcours.getLibelle(), "Les suceurs de bites !");
//        System.out.println(gestionParcours.toString());
//        gestionParcours.chargerParcours(5);
//        System.out.println(gestionParcours.toString());
//        gestionParcours.effacerParcours();
//        System.out.println(gestionParcours.toString());
        
//        gestionParcours.afficherDetails();
//        gestionParcours.chargerParcours(3);
//        System.out.println(gestionParcours.toString());
//        gestionParcours.afficherDetails();
//        gestionParcours.creerParcours("Musées du JUIF", "Take your mask");
//        System.out.println(gestionParcours.getId());
//        System.out.println(gestionParcours.toString());
//
//        /**
//         * Listing des différents parcours 
//         */
//        String mesParcours = "";
//        for (int i = 1; i <= 21; i++) {
//            gestionParcours.chargerParcours(i);
//            mesParcours += "\n-> Parcours n°" + gestionParcours.getId() + "  - " + gestionParcours.getLibelle() + ", " + gestionParcours.getDescription();
//        }
//        System.out.println(mesParcours);
    }

}
