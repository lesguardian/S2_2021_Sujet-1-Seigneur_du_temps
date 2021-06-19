package equipe_32.Sources.ihm.FrameMenu;

import equipe_32.Sources.ihm.FrameMenu.*;
import equipe_32.Sources.ihm.FrameJoueur.*;
import equipe_32.Sources.ihm.FramePlateau.*;
import equipe_32.Sources.Metier.*;
import equipe_32.Sources.Controleur;


import java.awt.BorderLayout			;
import java.awt.GridLayout			;
import java.awt.FlowLayout			;
import java.awt.Dimension			;
import java.awt.event.*				;


import java.io.InputStreamReader		;
import java.io.FileInputStream			;
import java.io.FileReader			;
import java.io.File				;
import java.io.IOException			;
import javax.sound.sampled.*			;

import javax.swing.*				;


import java.awt.Image;

import java.awt.Color;
import javax.swing.border.Border;

public class FrameMenu extends JFrame implements ActionListener
{
  Controleur ctrl;

  private JButton 	btnValider	;
  private JButton		btnQuitter	;
  private JRadioButton[]    rbModeJeu  ;


  private JTextField[]	txtJoueur	;

	public FrameMenu(Controleur ctrl)
	{
    /* - - - - - - - - - - - - - */
		/* Instructions              */
		/* - - - - - - - - - - - - - */

		this.setTitle    ( "Menu Principale" )	;
		this.setLocation ( 10, 10 )		;
		this.ctrl = ctrl			;

    this.setLayout(new GridLayout(4,0));


		/* - -  - - - - - */
		/* Variable Local */
		/* - - - - - - -- */

		ImageIcon	icnSeigneur;
		JPanel		panelEntete, panelTxt, panelModeJeu, panelChoixFinal	;
		JLabel		lblMenu, lblConsigne, lblSeigneur			;
		JLabel[]	lblJoueur						;
    ButtonGroup grpTheme;

		/* - - - - - - - - - - - - - */
		/* création des composants   */
		/* - - - - - - - - - - - - - */

		// locale :

		panelEntete     = new JPanel();
		panelTxt        = new JPanel();
		panelModeJeu    = new JPanel();
		panelChoixFinal = new JPanel();

		panelEntete    .setLayout(new GridLayout(3,0));
		panelTxt       .setLayout(new GridLayout(8,0));
		panelModeJeu   .setLayout(new FlowLayout())	;
		panelChoixFinal.setLayout(new FlowLayout())	;

		lblMenu     = new JLabel("Menu" , JLabel.CENTER)				;
		lblConsigne = new JLabel("Veuillez entrer le pseudonyme des deux joueurs")	;
		lblJoueur   = new JLabel[2]							;
		lblSeigneur  = new JLabel()							;
    grpTheme 			= new ButtonGroup();

    icnSeigneur	= new ImageIcon("Images/- Icon/Menu.png");
    lblSeigneur		= new JLabel();
    lblSeigneur		= new JLabel(icnSeigneur , JLabel.CENTER);


		// privée  :

		this.btnValider = new JButton("Valider");
		this.btnQuitter = new JButton("Quitter");
    this.rbModeJeu = new JRadioButton[2];


		this.txtJoueur  = new JTextField[2];

		this.ctrl = ctrl;

		for(int cpt = 0 ; cpt < this.txtJoueur.length ; cpt++ )
		{
			int a = cpt +1;
			this.txtJoueur[cpt]	= new JTextField();
			lblJoueur[cpt]		= new JLabel("Joueur " + a + " :");

			panelTxt.add(lblJoueur[cpt]);
			panelTxt.add(this.txtJoueur[cpt]);
		}

    this.rbModeJeu[0] = new JRadioButton("Default");
		this.rbModeJeu[1] = new JRadioButton("Theme01");
		/* - - - - - - - - - - - - - - - */
		/* positionnement des composants */
		/* - - - - - - - - - - - - - - - */

		// panel locale :

    panelEntete.add(lblSeigneur);
    panelEntete.add(lblMenu);
    panelEntete.add(lblConsigne);

    panelModeJeu.add(new JLabel("Theme de Jeu :"));
    for(int i = 0; i < this.rbModeJeu.length ; i++)
    {
      grpTheme.add(this.rbModeJeu[i]		 );
      panelModeJeu.add(this.rbModeJeu[i]);
    }

    panelChoixFinal.add(this.btnValider);
    panelChoixFinal.add(this.btnQuitter);


		// panel Privée :

		this.add(panelEntete    );
		this.add(panelTxt       );
    this.add(panelModeJeu);
		this.add(panelChoixFinal);


		/* - - - - - - - - - - - - - */
		/* Activation des composants */
		/* - - - - - - - - - - - - - */

		this.addWindowListener
		(
			new WindowAdapter()
			{
				public void windowClosing(WindowEvent evt)
				{
					System.exit(0);
				}
			}
		);

		this.btnValider.addActionListener(this);
		this.btnQuitter.addActionListener(this);




		/* - - - - - - - - - - - - - */
		/*   Finalisation du frame   */
		/* - - - - - - - - - - - - - */

		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE )	;
		this.setPreferredSize(new Dimension(600, 600))		;
		this.setSize(530, 500)					;
		this.setLocationRelativeTo(null)			;
		this.setVisible ( true )				;
		this.pack()						;
  }


  // Lance le Jeu ou quitte l'aplication

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() instanceof JButton)
    {
      if (e.getSource() == this.btnQuitter )
      {
        System.exit(0);
      }

      if (e.getSource() == this.btnValider )
      {
        if(!getsNomJoueur())
          JOptionPane.showMessageDialog(this ,"Veuillez entrer le pseudonyme des deux joueurs." );
        else
        {
          this.setVisible(false);
          this.ctrl.setJoueur(this.txtJoueur[0].getText(), this.txtJoueur[1].getText());
          if(this.rbModeJeu[0].isSelected()){ this.ctrl.setModeDeJeu(this.rbModeJeu[0].getText()); }
          if(this.rbModeJeu[1].isSelected()){ this.ctrl.setModeDeJeu(this.rbModeJeu[1].getText());}
          this.ctrl.ouvrir()	;
        }
      }
    }
  }

  public void ouvreFrameMenu(){ this.setVisible(true);}


  // recupere le nombre de joueur

  public Boolean getsNomJoueur()
  {
    for (int cptLig = 0; cptLig < this.txtJoueur.length; cptLig++)
    {
      if (this.txtJoueur[cptLig].getText().equals("")) { return false;}
    }
    return true;
  }
}
