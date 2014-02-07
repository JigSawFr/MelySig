/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

import fr.melysig.controleurs.UtilisateursControleur;
import fr.melysig.models.Lieux;
import fr.melysig.process.LieuProcess;
import fr.melysig.vues.*;
import javax.swing.JOptionPane;

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
    private Debug gestionDebug;
    private Erreurs gestionErreurs;

    private static MVC monMVC;
    public ConsultationVue maConsultationVue;
    public UtilisateursControleur monControleurUtilisateur;

    public static MVC obtenirMVC() {
        if (monMVC == null) {
            monMVC = new MVC();
            monMVC.initialisationMVC();
        }
        return monMVC;
    }

    @Override
    public String toString() {
        return "MVC{" + "debug=" + debug + ", erreurs=" + erreurs + ", gestionDebug=" + gestionDebug + ", gestionErreurs=" + gestionErreurs + ", maConsultationVue=" + maConsultationVue.toString() + ", monUtilisateur=" + monControleurUtilisateur.toString() + '}';
    }

    
    /**
     * Déclaration des sous-controleurs
     */
    private void initialisationMVC() {

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
        monControleurUtilisateur = new UtilisateursControleur();
        PanelDeConnexion monPanel = new PanelDeConnexion();

        Lieux lieux = LieuProcess.getInstance().chargerLieux(1);
        // Appel de la vue.
        maConsultationVue = new ConsultationVue(lieux);

        /**
         * Information de l'initialisation
         */
        this.gestionDebug.debug("MVC", "Initialisation du MVC terminée.");

        /**
         * Notre Application
         */
        try {
            this.gestionDebug.debug("SPS", "Lancement du Splash Screen...");
            this.gestionDebug.debug("SPS", "Mise en pause ...5 secondes restantes");
            Thread.sleep(1000);
            this.gestionDebug.debug("SPS", "Mise en pause ...4 secondes restantes");
            Thread.sleep(1000);
            this.gestionDebug.debug("SPS", "Mise en pause ...3 secondes restantes");
            Thread.sleep(1000);
            this.gestionDebug.debug("SPS", "Mise en pause ...2 secondes restantes");
            Thread.sleep(1000);
            this.gestionDebug.debug("SPS", "Mise en pause ...1 seconde restante");
            Thread.sleep(1000);
            this.gestionDebug.debug("SPS", "C'est parti !");
        } catch (InterruptedException e) {
        }
        monPanel.setVisible(true);
    }

}
