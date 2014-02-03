/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

import fr.melysig.controleurs.UtilisateurControleur;
import fr.melysig.models.Parcours;
import fr.melysig.models.Utilisateur;
import fr.melysig.vues.UtilisateurVue;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Classe principale du programme.
 *
 * @author Sébastien R.
 * @since 0.1
 * @version 0.2
 */
public class MelySig {

    private static Utilisateur modeleMembre;
    private static UtilisateurVue vueMembre;
    private static UtilisateurControleur controleurMembre;
    private static Parcours monParcours;
    private static String libelle;
    private static String description;

    /**
     * Méthode principale permettant le lancement du programme. Etant statique,
     * celle-ci est initialisée dès le démarrage.
     *
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {

        /* Instanciation d'un objet utilisateur */
        modeleMembre = new Utilisateur();

        /* Instanciation d'une vue d'utilisateur */
        vueMembre = new UtilisateurVue();

        /* Instanciation du controleur avec un modèle et une vue définie */
        controleurMembre = new UtilisateurControleur(modeleMembre, vueMembre);

        /* Chargement des informations de l'utilisateur possédant l'identifiant 1 */
        controleurMembre.chargerUtilisateur(1);

        /* Affichage des informations */
        controleurMembre.miseAjourVue();

        /* Mise à jour des données du membre */
        controleurMembre.setMotDePasse("GoodJob!");

        /* Actualisation des informations affichées */
        controleurMembre.miseAjourVue();

        monParcours = new Parcours();

        /* Saisie clavier console */
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le libellé du parcours :");
        libelle = sc.nextLine();
        System.out.println("Veuillez saisir la description du parcours :");
        description = sc.nextLine();
        
        /* Ajout en BDD */
        try {
            monParcours.ajouterParcours(libelle, description);
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }

    }
}
