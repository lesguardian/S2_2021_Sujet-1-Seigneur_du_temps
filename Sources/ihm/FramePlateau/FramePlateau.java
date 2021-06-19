package equipe_32.Sources.ihm.FramePlateau;
import equipe_32.Sources.ihm.FrameMenu.*;
import equipe_32.Sources.ihm.FrameJoueur.*;
import equipe_32.Sources.ihm.FramePlateau.*;
import equipe_32.Sources.Metier.*;
import equipe_32.Sources.Controleur;


import javax.swing.*;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.Border;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class FramePlateau extends JFrame
{


	private Controleur   	 ctrl;
	private PanelSysteme[] panelSysteme;
	private JPanel panelFin;
	private String sModeDeJeu;



	public FramePlateau ( Controleur ctrl, String sModeDeJeu )
	{
		/* - - - - - - - - - - - - - */
		/* Instructions              */
		/* - - - - - - - - - - - - - */
		this.setTitle    ( "Les Seigneur du Temps" );
		this.setLocation ( 200,1 );
		this.ctrl = ctrl;
		this.sModeDeJeu = sModeDeJeu;
		this.setLayout(new GridLayout(1, this.ctrl.getAllSystem().size()+2));

		/* - - - - - - - - - - - - - - - */
		/* positionnement des composants */
		/* - - - - - - - - - - - - - - - */

		this.panelFin = new JPanel();
		this.panelFin.setLayout(new GridLayout(8,1));
		this.panelFin.add(new JLabel("Fin de la galaxie --->" , JLabel.CENTER));
		this.panelFin.add(new JLabel(new ImageIcon(new ImageIcon ("Images/"+sModeDeJeu+"/Systeme/end.gif").getImage().getScaledInstance(100, 200, Image.SCALE_DEFAULT))));
		this.panelSysteme = new PanelSysteme[this.ctrl.getAllSystem().size()];

		for( int cpt=0; cpt < this.ctrl.getAllSystem().size(); cpt++)
		{
			this.panelSysteme[cpt] = new PanelSysteme(ctrl, this.ctrl.getOneSystem(cpt), sModeDeJeu );
			this.add(panelSysteme[cpt]);
		}

		this.add(panelFin);






		/* - - - - - - - - - - - - - */
		/* Finalisation du frame     */
		/* - - - - - - - - - - - - - */

		this.setSize     ( 1236, 687 );
		this.addWindowListener   ( new GereWindow       () );
		this.addComponentListener( new GereDeplacerFrame() );
	}


	/** En détactant la fermeture de cette Frame, on va provoquer la fermeture des autres Frame(Joueur) */
	private class GereWindow extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			FramePlateau.this.ctrl.fermer();
		}
	}

 public void maj()
 {
	 for(int a = 0; a < this.panelSysteme.length; a++ )
	 {
		 this.remove(this.panelSysteme[a]);
	 }
	 this.remove(this.panelFin);

	 for( int cpt=0; cpt < this.ctrl.getAllSystem().size(); cpt++)
	 {
		 this.panelSysteme[cpt] = new PanelSysteme(ctrl, this.ctrl.getOneSystem(cpt), this.sModeDeJeu );
		 this.add(panelSysteme[cpt]);
	 }
	 this.add(this.panelFin);
	 this.validate();
	 this.repaint();

	 if (this.ctrl.getAllSystem().get(0).getPlaneteAllSelected() && this.ctrl.getAllSystem().get(1).getPlaneteAllSelected() && this.ctrl.getAllSystem().get(2).getPlaneteAllSelected()  && this.ctrl.getAllSystem().get(3).getPlaneteAllSelected()  )
	 {
		 ImageIcon icon = new ImageIcon("Images/"+this.sModeDeJeu+"/Winner/Winner.gif");
		 JOptionPane.showMessageDialog(this, this.ctrl.getAllSystem().get(this.ctrl.getAllSystem().size()-1).getGagnant(this.ctrl.getAllJoueur().get(0), this.ctrl.getAllJoueur().get(1)));
		 JOptionPane.showMessageDialog(this, icon);
		 this.ctrl.fermer();
	 }
 }


	/** Lors du déplacement de cette Frame, les autres Frame(Joueur) vont suivre */
	private class GereDeplacerFrame extends ComponentAdapter
	{
		public void componentMoved(ComponentEvent e)
		{
			FramePlateau.this.ctrl.moveFrame("P");
		}
	}

	public void ouvreFramePlateau() { this.setVisible(true);}
	public void fermeFramePlateau() { this.setVisible(false);}



	/** Permet de mettre à jour la partie dessiner sur le PanelPlateau.
	    il y a sur ce Panel que des informations dessinées. */

}
