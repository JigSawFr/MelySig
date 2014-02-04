/*
 * MelySig - Geolocalisation de points d'interets sur vos endroits favoris !
 * MelySig est systeme d'information geographique qui animera vos decouvertes du monde :)
 * Copyright 2014 - melysig.exia-nancy.com
 * Auteurs : Pocecco Julien, Mougenel Gerold, Gaudenot Guillaume & Robert Sebastien.
 */
package fr.melysig.main;

/**
 * Classe principale du programme.
 *
 * @author Sébastien R.
 * @since 0.1
 * @version 0.3
 */
public class MelySig {

    /*private boolean debug = true;
     private boolean erreurs = true;*/

    /*private static Utilisateur modeleMembre;
     private static UtilisateurVue vueMembre;
     private static UtilisateurControleur controleurMembre;*/
    //private static Parcours monParcours;
    /*private static String libelle;
     private static String description;*/
    /*private Parcours modeleParcours;
     private ParcoursVue vueParcours;
     private ParcoursControleur controleurParcours;*/
    /**
     * Méthode principale permettant le lancement du programme. Etant statique, celle-ci est initialisée dès le démarrage.
     *
     * @param args Arguments de la ligne de commande
     */
    public static void main(String[] args) {

        MVC mainMVC = new MVC();

        /* modeleParcours = new Parcours();
         vueParcours = new ParcoursVue();*/

        /* Instanciation d'un objet utilisateur */
        //modeleMembre = new Utilisateur();

        /* Instanciation d'une vue d'utilisateur */
        //vueMembre = new UtilisateurVue();

        /* Instanciation du controleur avec un modèle et une vue définie */
        //controleurMembre = new UtilisateurControleur(modeleMembre, vueMembre);

        /* Chargement des informations de l'utilisateur possédant l'identifiant 1 */
        //controleurMembre.chargerUtilisateur(1);

        /* Affichage des informations */
        //controleurMembre.miseAjourVue();

        /* Mise à jour des données du membre */
        //controleurMembre.setMotDePasse("GoodJob!");

        /* Actualisation des informations affichées */
        //controleurMembre.miseAjourVue();

        /* Saisie clavier console */
        /*Scanner sc = new Scanner(System.in);
         System.out.println("Veuillez saisir le libellé du parcours :");
         libelle = sc.nextLine();
         System.out.println("Veuillez saisir la description du parcours :");
         description = sc.nextLine();*/
        /* Ajout en BDD */
        /*try {
         monParcours.ajouterParcours(libelle, description);
         } catch (SQLException e) {

         System.out.println(e.getMessage());

         }*/
        /*System.out.println("SYSTEM ON");
         monParcours = new Parcours(); // #1T
         System.out.println(monParcours.toString());
         System.out.println("Nos parcours:");
         for (int i = 1; i <= 3; i++) {
         monParcours = monParcours.chargerParcours(i);
         System.out.println("Parcours n°" + monParcours.getId() + "  - " + monParcours.getLibelle() + " " + monParcours.getDescription());
         }
         System.out.println(monParcours.toString());*/
    }
}
