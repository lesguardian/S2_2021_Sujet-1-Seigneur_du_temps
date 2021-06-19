package equipe_32.Sources.Metier;

public class Planete
{
	private String  sCoulPlanete;
	private Boolean boolPlanetSelec;
	private Joueur  sNomJoueur;

	/** Il s'agit du constructeur Planète.
	* @param sCoulPlanete Correspond à la couleur de la planète courrante.
	*/
	public Planete(String sCoulPlanete)
	{
		this.sCoulPlanete = sCoulPlanete;
		this.boolPlanetSelec = false;
	}

	/** Méthode qui permet d'attribuer une variable du type bouléenne à une planète par un joueur donné en paramètre
	* @param bEstSelectionnee Correspond à la couleur de la planète courrante.
	* @param sNomJoueur Correspond au nom du joueur.
	*/

	public void setPlanetSelec(Boolean a, Joueur sNomJoueur )
	{
		if( a == true  ) { this.boolPlanetSelec = a; this.sNomJoueur = sNomJoueur; }
		if( a == false ) { this.boolPlanetSelec = a; this.sNomJoueur = null;}
	}

	/**Méthode qui permet de savoir si la plante courrante est sélectionnée
	*    @return boolPlanetSelec - Variable qui indique si la planète est sélectionnée ou non.
	*/
	public Boolean getPlanetSelec()
	{
		return this.boolPlanetSelec ;
	}

	public Boolean getPlanetSelecJoueur(Joueur a)
	{
		if( a == this.sNomJoueur && this.boolPlanetSelec == true) { return true; }
		return false;
	}

	/**Méthode qui permet de savoir si la plante courrante est sélectionnée
	*    @param joueur Joueur courrant
	*    @return true ou false si la planète est sélectionnée ou non par le joueur donné en paramètre
	*/
	public Joueur getJoueurSelectionne() { return this.sNomJoueur;}

	public String toString()
	{
		String str = "";

		str +=  this.sCoulPlanete;
		if (this.boolPlanetSelec != false ) { str+= " : selectionné par le joueur : "  + this.sNomJoueur.getsNomJoueur();}

		return str;
	}

	/** Méthode qui permet de vérifier si la couleur de la planète est sélectionnée par le joueur.
	* @param sCouleur Correspond à la couleur de la planète.
	* @param joueur Correspond au joueur.
	* @return true si c'est vrai, false si c'est faux
	*/
	public Boolean verificationCouletJ(String a, Joueur b){ if(a.equals(this.sCoulPlanete) && (b == this.sNomJoueur)){ return true;}
			                                                    return false;}

	/**Méthode qui permet d'obtenir la couleir de la planète courrante.
	*    @return sCoulPlanete - Retourne la couleur de la planète courrante.
	*/


	public String getsCoulPlanete(){ return this.sCoulPlanete   ;}

	/**Méthode qui permet d'obtenir la couleir de la planète courrante.
	*    @return sNomJoueur - Retourne le nom du joueur qui possède la planète courrante.
	*/
	public Joueur              getJoueurcoul       (){ return this.sNomJoueur   ;}


}
