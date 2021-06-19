package equipe_32.Sources.Metier;

import java.util.ArrayList;

public class SystemeSolaire
{
	private int        iNbPlanete = 0;
	private String     sNomSystem;
	private static int iNbSystem;
	private int        iNumSystem;
	private ArrayList<Planete> alPlanete;
	private String[] tabPlanete = {"Bleue", "Jaune", "Verte", "Rouge", "Violet", "Orange", "Rose"  };

	public SystemeSolaire(String sNomSystem, int iNbPlanete )
	{
		this.alPlanete = new ArrayList<Planete>();
		this.sNomSystem = sNomSystem ;
		this.iNumSystem = ++this.iNbSystem;
		this.iNbPlanete = iNbPlanete;
		creerPlanete(iNbPlanete);
	}

	public Boolean verificationPlanet( String sMsg )
	{
		for(Planete o: this.alPlanete)
		{
			if ((sMsg.equals(o.getsCoulPlanete())) && (o.getJoueurcoul() == null)) { return  true;} ;
		}
		return false;
	}

	public Boolean verificationPlanetCoulJ( String sMsg, Joueur j)
	{
		for(Planete p: this.alPlanete)
		{
			if (p.verificationCouletJ(sMsg, j)) { return  true;} ;
		}
		return false;
	}


	public String toString()
	{
		String str = "";
		str += this.sNomSystem + " Num univers:  " + this.iNumSystem;
		str += "\n";

		for(Planete u : this.alPlanete )
		{
		  str += " - " + u.toString() + "\n";
		}
		return str;
	}



	public void creerPlanete (int a)
	{
		String sNom;
		for( int cpt = 0 ; cpt< a; cpt++)
		{
			this.alPlanete.add(new Planete( (tabPlanete[cpt])));
		}
	}

	public void selectionPlanete(int a, Joueur j) { this.alPlanete.get(a).setPlanetSelec(true, j);}

	public void enleverPlanete(int nomPlanete, Joueur j)
	{
		 this.alPlanete.get(nomPlanete).setPlanetSelec(false, j);
	}

	public Boolean getPlaneteAllSelected()
	{
		for(Planete p : this.alPlanete)
		{
			if (!p.getPlanetSelec())
			{
				return false;
			}
		}
		return true;
	}

	public String affichagePlanetSelectionner(Joueur j)
	{
		String gg = "Planete Selectionner";

		for(Planete p : this.alPlanete)
		{
			if (p.getPlanetSelecJoueur(j)) { gg += "  - Planete selectionne :" + p.getsCoulPlanete() + "\n"; }
		}
		return gg;
	}


	public Boolean getPlaneteOneSelectedJoueurA(Joueur j)
	{
		for(Planete p : this.alPlanete)
		{
			if(p.getJoueurcoul() == j) { return true;}
		}
		return false;
	}

	public Boolean getPlaneteOneSelected()
	{
		for(Planete p : this.alPlanete)
		{
			if (p.getPlanetSelec())
			{
				 return true;
			}
		}
		return false;
	}


	public Boolean getPlaneteOneSelectedJoueur(Joueur j)
	{
		for(Planete p : this.alPlanete)
		{
			if(p.getPlanetSelecJoueur(j))
			{
				return true;
			}
		}
		return false;
	}

	public String getGagnant(Joueur P1, Joueur P2)
	{
			int iJoueurBlanc = 0;
			int iJoueurNoir  = 0;

			for (Planete p : this.alPlanete)
			{
				if (p.getJoueurSelectionne() == P1)
					iJoueurBlanc++;

				if (p.getJoueurSelectionne() == P2)
					iJoueurNoir++;
			}

			if      (iJoueurBlanc > iJoueurNoir) { return "Et le gagnant est... " + P1.getsNomJoueur();}
			else if (iJoueurBlanc < iJoueurNoir) { return "Et le gagnant est... " + P2.getsNomJoueur();}

			return null;
	}


	public String              getsNom        (){ return this.sNomSystem   ;}
	public int                 getiNbPlanete  (){ return this.iNbPlanete   ;}
	public int                 getiNbSystem   (){ return this.iNbPlanete   ;}
	public int                 getiNumSystem  (){ return this.iNumSystem   ;}

	public Planete            getOnePlanet(int iNumPlanete ){ return this.alPlanete.get(iNumPlanete);}

	public void               setiNumSystem   (int a)    { this.iNumSystem+= a;}
	public void               setiNumSystem2  (int a)    { this.iNumSystem = a;}
	public void               setsNomSystem   (String a) { this.sNomSystem = a;}

}
