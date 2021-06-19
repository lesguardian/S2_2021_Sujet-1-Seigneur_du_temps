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


public class PanelHead extends JPanel
{
  private Controleur   ctrl;

	public PanelHead ( Controleur ctrl, Joueur player )
	{
    this.ctrl = ctrl;

    Border lineborder = BorderFactory.createLineBorder(Color.black, 2);
    this.setBorder(lineborder);

    this.add(new JLabel("Joueur "+ player.getiNumJoueur() + " : " + player.getsNomJoueur()));
	}
}
