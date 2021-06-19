package equipe_32.Sources.ihm.FrameJoueur;

import equipe_32.Sources.ihm.FrameMenu.*;
import equipe_32.Sources.ihm.FrameJoueur.*;
import equipe_32.Sources.ihm.FramePlateau.*;
import equipe_32.Sources.Metier.*;
import equipe_32.Sources.Controleur;


import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.*;


import java.awt.Image;
import java.awt.Color;
import javax.swing.border.Border;


public class FrameJoueur extends JFrame
{
	private Controleur   ctrl;

	private PanelHead  	 panelHead		;
	private PanelBody    panelBody		;


	public FrameJoueur ( Controleur ctrl, Joueur player, String sModeDeJeu )
	{
		this.ctrl      = ctrl;

		// Création des composants

		this.panelHead 	 = new PanelHead(ctrl, player);
		this.panelBody 	 = new PanelBody(ctrl, player, sModeDeJeu);

		// positionnement des composants

	  this.add(this.panelHead 	, BorderLayout.NORTH	);
		this.add(this.panelBody 	, BorderLayout.CENTER	);

		// Finalisation du frame

		this.setUndecorated(true);
		if ( this.ctrl.getAllJoueur().size() == 0 )	this.setLocation ( 210,680 ); else this.setLocation ( 820,680 );
		this.setSize     ( 600,200 );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.setVisible  ( true );
	}

	public void maj()
	{
		this.panelBody.maj();
	}

	public void fermeFrameJoueur(){ this.setVisible(false); this.removeAll(); }
}
