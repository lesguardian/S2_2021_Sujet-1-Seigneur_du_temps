package equipe_32.Sources.Metier;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Collections;


import java.util.Scanner;
import java.io.FileInputStream;

public class Metier
{
	private ArrayList<SystemeSolaire> alSysteme;
	private ArrayList<Joueur>         alJoueur;

	/** Constructeur de la classe Metier
	* @param sScenario Correspond nom du scénario .
	*/
	public Metier(String sScenario)
	{
		alSysteme = new ArrayList<SystemeSolaire>();
		alJoueur  = new ArrayList<Joueur>();

		this.alJoueur.add(new Joueur("Medhi"));
		this.alJoueur.add(new Joueur("Benameur"));

		Scanner scFic;
		int     iNbSysteme;
		String  sSystem , sLigne;

		if(sScenario.equals(""))
 	 {
 		 try
 		 {
 			 scFic = new Scanner ( new FileInputStream( "Images/Default/Data/System.data" ), "utf-8"  );

 			 while ( scFic.hasNextLine() )
 			 {
 				 sLigne      = scFic.nextLine();
 				 sSystem    = sLigne.substring  (1,18).trim();
 				 iNbSysteme = Integer.parseInt (sLigne.substring(19,20));
 				 alSysteme.add ( new SystemeSolaire ( sSystem, iNbSysteme));
 			 }
 		 }
 		 catch(Exception e)
 		 {
 			 e.printStackTrace();
 		 }

		 Collections.shuffle(this.alSysteme);
		 this.alSysteme.get(0).setiNumSystem2(1);
		 this.alSysteme.get(1).setiNumSystem2(2);
		 this.alSysteme.get(2).setiNumSystem2(3);
		 this.alSysteme.get(3).setiNumSystem2(4);
 	 }


 	 else
 	 {
 		 try
 		 {
 			 int a = 0;
 			 scFic = new Scanner ( new FileInputStream(sScenario), "utf-8"  );
 			 while ( scFic.hasNextLine() )
 			 {

 				 sLigne      	= scFic.nextLine();
 				 sSystem    	= sLigne.substring  (1,18).trim();
 				 iNbSysteme 	= Integer.parseInt (sLigne.substring(19,20));
 				 alSysteme.add ( new SystemeSolaire ( sSystem, iNbSysteme));
 				 for(int cpt = 0; cpt < iNbSysteme; cpt++)
 				 {
 					 int iNumJoueur;
 					 sLigne = scFic.nextLine();
 					 iNumJoueur = Integer.parseInt(sLigne.substring  (1,2).trim());
 					 if(iNumJoueur != 2){this.alSysteme.get(a).selectionPlanete(cpt, this.alJoueur.get(iNumJoueur));}
 					}
 					a++;
 				 }
 			 }
 		 catch(Exception e)
 		 {
 			 e.printStackTrace();
 		 }
	}
}

/** Permet de trier le système en fonction de l'action faite..
* @param iNumJoueur Correspond numéro du joueur .
* @param cAction Correspond à l'action
* @param iNumPlanete numéro de la planète
* @param iNumSystem numéro du système
*/

	public void action (int iNumJoueur, char cAction, int iNumPlanete, int iNumSystem )
	{
		switch(cAction)
		{
			case 'P':
			{
					this.alSysteme.get(iNumSystem).selectionPlanete(iNumPlanete, this.alJoueur.get(iNumJoueur));
					trierSysteme(iNumSystem);
					break;
			}
			case 'L':
			{
					this.alSysteme.get(iNumSystem).enleverPlanete(iNumPlanete, this.alJoueur.get(iNumJoueur));
					break;
			}
			case 'p':
			{
					this.alSysteme.get(iNumSystem).selectionPlanete(iNumPlanete, this.alJoueur.get(iNumJoueur));
					break;
			}
			default : break;
		}

	}


	/** Permet de trier le système en fonction de l'action faite..
	* @return ArrayList  de tous les systèmes solaires .
	*/
	public ArrayList<SystemeSolaire> getAllSystem()          { return this.alSysteme;}

	/** Renvoie le système à l'indice donné en parametre..
	* @return ArrayList  de tous les systèmes solaires .
	* @param iNumS numéro système
	*/
	public SystemeSolaire            getOneSystem(int iNumS) { return this.alSysteme.get(iNumS);}

	/** Renvoie le nombre de systèmes.
	* @return nombre de systèmes .
	*/
	public int                       getiNbSystem()          { return this.alSysteme.size();}

	/** Renvoie vrai si la planète précédente a été selectionnée .
	* @return nombre de systèmes .
	*/
	public Boolean   getPlanetSelecPrec(){
		for(SystemeSolaire u : this.alSysteme.subList(1, this.alSysteme.size()))
		{ if(u.getPlaneteOneSelected()){ return true; }  } return false; }

	// trier System
	/** Trie le système  .
	* @param iNumSystem Numéro du système
	*/
	public void trierSysteme(int iNumSystem )
	{
		int passe = 0;
		String sMsg = this.alSysteme.get(iNumSystem).getsNom();

		ListIterator<SystemeSolaire> here = this.alSysteme.listIterator();
		here = this.alSysteme.listIterator();
		here.next();

		for(SystemeSolaire s: this.alSysteme)
		{
			SystemeSolaire systemTmp, systemTmp2;

			if(sMsg.equals(s.getsNom()) && here.hasNext() && passe != 1)
			{
				systemTmp  = this.alSysteme.get(s.getiNumSystem()-1); // actuelle
				systemTmp2 = this.alSysteme.get(s.getiNumSystem());   // suivant

				this.alSysteme.set(here.previous().getiNumSystem()-1, systemTmp2);
				here.next();

				this.alSysteme.set(here.next().getiNumSystem()-1, systemTmp);
				here.previous();

				here.previous().setiNumSystem(-1);  //actuelle
				here.next();
				here.next().setiNumSystem(+1);
				here.previous();

				passe =1;
			}

			if(here.hasNext()) { here.next(); }
		}
	}

	/** Attribue les noms des joueurs
	* @param sNomJoueur Joueur 1
	* @param sNomJoueur2 Joueur 2
	*/
	public void setJoueur (String sNomJoueur , String sNomJoueur2 ) { this.alJoueur.get(0).setNewNom(sNomJoueur); this.alJoueur.get(1).setNewNom(sNomJoueur2);}

	/** Renvoie tous les joueurs
	* @return Renvoie tous les joueurs sous forme d'ArrayList
	*/
	public ArrayList<Joueur> getAllJoueur ()  { return this.alJoueur;}

	/** Attribue le nom du joueur
	* @param sNomJ Nom du joueur
	* @return Retourne le joueur à l'indice donné en parametre
	*/
	public Joueur getOneJoueur(int sNomJ)  { return this.alJoueur.get(sNomJ);}

	public void fermerMetier() {this.alJoueur.get(0).setRenitialise();this.alJoueur.clear(); this.alSysteme.clear();}
}
