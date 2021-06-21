package equipe_32.Sources;

import equipe_32.Sources.ihm.FrameMenu.*;
import equipe_32.Sources.ihm.FrameJoueur.*;
import equipe_32.Sources.ihm.FramePlateau.*;
import equipe_32.Sources.Metier.*;
import equipe_32.Sources.Controleur;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.io.FileInputStream;

public class Controleur
{
    private Metier           metier     ;
    private FrameMenu        ihmMenu    ;
    private FramePlateau     ihmPlateau ;
    private FrameJoueur[]    ihmJoueur  ;
    private static String iScenario = "";
    private  String sModeDeJeu ;


    public Controleur ()
    {
      this.ihmMenu          = new FrameMenu(this);
      this.metier           = new Metier(iScenario);
    }

    /** Permet de lier la partie IHM et la partie Métier. Il met à jour le métier et l'IHM.
  	*    @param iNumJoueur Numéro du joueur
  	*    @param cAction Caractère désignant l'action en cours
  	*    @param iNumPlanete Numéro de la planète
  	*    @param iNumSystem Numéro du système
  	*/
    public void action (  int numJoueur, char action, int iNumPlanete, int iNumSystem )
    {
      this.metier.action ( numJoueur, action, iNumPlanete, iNumSystem );

      if(action == 'L')
      {
        this.ihmPlateau.maj();
        this.ihmJoueur[numJoueur].maj();
      }

      else
      {
        this.ihmPlateau.maj();
        this.ihmJoueur[0].maj();
        this.ihmJoueur[1].maj();
      }
    }


    /** Permet de lier la partie IHM et la partie Métier. Il met à jour le métier et l'IHM.
  	*    @param orig Origine du point
  	*/

    public void moveFrame ( String orig )
    {
      java.awt.Point p;

      if ( orig.equals ("P") && this.ihmPlateau != null && this.ihmJoueur[0]!= null && this.ihmJoueur[1]!= null )
      {
        p = this.ihmPlateau.getLocation();
        this.ihmJoueur[0].setLocation ( p.x      ,p.y + 690 );
        this.ihmJoueur[1].setLocation ( p.x + 620,p.y + 690 );
      }
    }


    /** Ouvre le jeu.
  	*/
    public void ouvrir()
    {
      this.ihmJoueur = new FrameJoueur[getAllJoueur().size()];
      this.ihmPlateau   = new FramePlateau(this, this.sModeDeJeu);
      this.ihmPlateau.ouvreFramePlateau();

      for(int cpt =0; cpt < getAllJoueur().size(); cpt++ )
      {
        this.ihmJoueur[cpt] = new FrameJoueur(this, getOneJoueur(cpt), this.sModeDeJeu);
      }
    }


    /** Ferme le jeu.
  	*/
    public void fermer()
    {
      this.metier.fermerMetier();
      this.ihmPlateau.fermeFramePlateau();
      if ( this.ihmJoueur[0] != null && this.ihmJoueur[1]!= null )
      {
        this.ihmJoueur[0].fermeFrameJoueur();
        this.ihmJoueur[1].fermeFrameJoueur();
      }
      this.ihmMenu          = new FrameMenu(this);
      this.metier           = new Metier(iScenario);
      this.ihmPlateau.maj();
    }

    //methode Systeme solaire

  	/** retourne tous les systèmes dans une ArrayList
  	*@return Une ArrayList de Système
  	*/

    public ArrayList<SystemeSolaire> getAllSystem()          { return this.metier. getAllSystem();}

    /** Retourne le système à l'indice iNumSystem
    * @param iNumSystem : Numéro du système solaire
    * @return Un Système solaire
    */
    public SystemeSolaire            getOneSystem(int iNumS) { return this.metier.getOneSystem(iNumS);}

    /** Retourne le nombre de systèmes
    * @return un entier
    */
    public int                       getiNbSystem()          { return this.metier.getiNbSystem() ;}

    /** Retourne le nombre de systèmes
    * @param sNomJoueur : nom du jouueur 1 et 2
    */
    public void setJoueur (String sNomJoueur , String sNomJoueur2){ this.metier.setJoueur(sNomJoueur, sNomJoueur2  );}


    public ArrayList<Joueur> getAllJoueur() { return this.metier.getAllJoueur();}

    /** Retourne le joueur à l'indice iNomJ
  	*@param iNomJ Indice du joueur
  	*@return Un joueur
  	*/
    public Joueur getOneJoueur(int sNomJ)  { return this.metier.getOneJoueur( sNomJ);}

    /** Retourne un bouléen, si le nom de la planète a été sélectionné précédemment.
  	*@return Si une planète a été sélectionné précédemment, retourne true, sinon false.
  	*/
    public Boolean   getPlanetSelecPrec() { return this.metier.getPlanetSelecPrec();}

    /** Met le mode de jeu
  	*@param sModeDeJeu Mode de jeu
  	*/
    public void     setModeDeJeu(String sModeDeJeu) {this.sModeDeJeu = sModeDeJeu ;}



    /** Main du jeu.
  	*@param args[] Correspond aux arguments. Si il y a un argument, il permettra d'exécuter le jeu avec un scénario précis.
  	*/
    public static void main( String args[]  )
    {
      if(args.length != 0)
      {
        switch(args[0])
        {
            case "1"  : iScenario = "Scenario/Scenario1.data"; break;
            case "2"  : iScenario = "Scenario/Scenario2.data"; break;
            case "22" : iScenario = "Scenario/Scenario22.data"; break;
            case "3"  : iScenario = "Scenario/Scenario3.data"; break;
            default : break;
        }
      }

      new Controleur();
    }
}
