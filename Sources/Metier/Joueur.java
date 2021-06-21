package equipe_32.Sources.Metier;

public class Joueur
{
	private String     sNomJoueur;
	private static int iNbJoueur;
	private int        iNumJoueur;

	/** Il s'agit du constructeur Joueur.
	* @param nom Prend le nom du joueur entré dans la FrameMenu
	*/
	public Joueur(String nom)
	{
		this.sNomJoueur = sNomJoueur;
		this.iNumJoueur   = ++this.iNbJoueur;
	}

	/**Retourne le nom du joueur entré à partir de la FrameMenu.
	*    @return getsNomJoueur Le nom du joueur courrant
	*/
	public String getsNomJoueur() { return this.sNomJoueur       ;}

	/** Retourne le numéro du joueur courrant.
	*    @return Le numéro du joueur courrant
	*/
	public int    getiNumJoueur() { return this.iNumJoueur ;}
	/** Retourne le nombre de joueurs.
	*    @return Le nombre de joueurs
	*/
	public int    getiNbJoueur()  { return this.iNbJoueur  ;}

	/** Permet d'attribuer le nom donné en paramètre au joueur courrant..
	*    @param sNomJoueur Nom du joueur
	*/
	public void setNewNom(String sNomJoueur){this.sNomJoueur =sNomJoueur; }

	public void setRenitialise(){this.iNbJoueur =0; }
}
