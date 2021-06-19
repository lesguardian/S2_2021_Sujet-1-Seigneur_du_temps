package equipe_32.Sources.ihm.FramePlateau;
import equipe_32.Sources.ihm.FrameMenu.*;
import equipe_32.Sources.ihm.FrameJoueur.*;
import equipe_32.Sources.ihm.FramePlateau.*;
import equipe_32.Sources.Metier.*;
import equipe_32.Sources.Controleur;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.*;

import java.awt.Image;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.*;


public class PanelSysteme  extends JPanel
{
	Controleur ctrl;


	public PanelSysteme( Controleur ctrl, SystemeSolaire systemNum, String sModeDeJeu)
	{

		/*----------------------------*/
		/* Variables locales          */
		/*----------------------------*/
		this.ctrl = ctrl;
		

		this.setLayout(new GridLayout(8, 1 ));

		Border lineborderSystem = BorderFactory.createLineBorder(Color.blue, 5);
		Border lineborderJoueur = BorderFactory.createLineBorder(Color.white, 10);
		Border lineborderJouer2 = BorderFactory.createLineBorder(Color.black, 10);

		this.setBorder(lineborderSystem);

		JLabel lblNomSystem = new JLabel (systemNum.getsNom(), JLabel.CENTER);
		this.add(lblNomSystem);

		JLabel[] lblPlanet = new JLabel[systemNum.getiNbSystem()];
		for(int cpt = 0; cpt < systemNum.getiNbSystem() ; cpt++ )
		{
			ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+sModeDeJeu+"/Planet/"+systemNum.getOnePlanet(cpt).getsCoulPlanete()+".png").getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT));
			lblPlanet[cpt] = new JLabel (imageIcon);

			if(systemNum.getOnePlanet(cpt).getJoueurSelectionne() == this.ctrl.getAllJoueur().get(0))
			{
				lblPlanet[cpt].setBorder(lineborderJoueur);
			}

			if(systemNum.getOnePlanet(cpt).getJoueurSelectionne() == this.ctrl.getAllJoueur().get(1))
			{
				lblPlanet[cpt].setBorder(lineborderJouer2);
			}

		  this.add(lblPlanet[cpt]);
		}

		/*---------------------------*/
		/* Instructions              */
		/*---------------------------*/

		/* - - - - - - - - - - - - - */
		/* création des composants   */
		/* - - - - - - - - - - - - - */

		/* - - - - - - - - - - - - - - - */
		/* positionnement des composants */
		/* - - - - - - - - - - - - - - - */


		/* - - - - - - - - - - - - - */
		/* Activation des composants */
		/* - - - - - - - - - - - - - */

	}

}
