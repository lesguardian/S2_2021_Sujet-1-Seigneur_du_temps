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
  private JLabel lblModeDeJeu;
  private JPanel panelEntete;
  private Clip        clip ;


  private JTextField[]	txtJoueur	;

	public FrameMenu(Controleur ctrl)
	{
    /* - - - - - - - - - - - - - */
		/* Instructions              */
		/* - - - - - - - - - - - - - */

		this.setTitle    ( "Menu Principale" )	;
		this.setLocation ( 10, 10 )		;
		this.ctrl = ctrl			;
    this.setLayout(new BorderLayout());

		/* - -  - - - - - */
		/* Variable Local */
		/* - - - - - - -- */

		ImageIcon	icnSeigneur;
		JPanel		 panelBas, panelModeJeu, panelChoixFinal, panelSaisie	;
		JLabel		lblMenu, lblConsigne, lblSeigneur			;
		JLabel[]	lblJoueur 					;
    JLabel	lblModeDeJeu					;
    ButtonGroup grpTheme;

		/* - - - - - - - - - - - - - */
		/* création des composants   */
		/* - - - - - - - - - - - - - */

		// locale :

		this.panelEntete= new JPanel();
		panelModeJeu    = new JPanel();
		panelChoixFinal = new JPanel();
    panelBas        = new JPanel();
    panelSaisie     = new JPanel();

		panelEntete    .setLayout(new FlowLayout());
		panelModeJeu   .setLayout(new FlowLayout());
		panelChoixFinal.setLayout(new FlowLayout());
    panelSaisie       .setLayout(new GridLayout(8,0));

    panelBas.setLayout(new BorderLayout());

		lblMenu     = new JLabel("Menu" , JLabel.CENTER)				;
		lblConsigne = new JLabel("Veuillez entrer le pseudonyme des deux joueurs")	;
		lblJoueur   = new JLabel[2]							;
		lblSeigneur  = new JLabel()							;
    grpTheme 			= new ButtonGroup();

    this.lblModeDeJeu = new JLabel(new ImageIcon(new ImageIcon ("Images/Default/Menu/Menu.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));

    icnSeigneur	= new ImageIcon("Images/- Icon/Menu.png");
    lblSeigneur		= new JLabel();
    lblSeigneur		= new JLabel(icnSeigneur , JLabel.CENTER);


		// privée  :

		this.btnValider = new JButton("Valider");
		this.btnQuitter = new JButton("Quitter");
    this.rbModeJeu = new JRadioButton[4];


		this.txtJoueur  = new JTextField[2];

		this.ctrl = ctrl;

		for(int cpt = 0 ; cpt < this.txtJoueur.length ; cpt++ )
		{
			int a = cpt +1;
			this.txtJoueur[cpt]	= new JTextField();
			lblJoueur[cpt]		= new JLabel("Joueur " + a + " :");
      panelSaisie.add(lblJoueur[cpt]);
      panelSaisie.add(this.txtJoueur[cpt]);
		}

    this.rbModeJeu[0] = new JRadioButton("Default");
		this.rbModeJeu[1] = new JRadioButton("Theme01");
    this.rbModeJeu[2] = new JRadioButton("Theme02");
    this.rbModeJeu[3] = new JRadioButton("Theme03");

    this.rbModeJeu[0].setSelected(true);

		/* - - - - - - - - - - - - - - - */
		/* positionnement des composants */
		/* - - - - - - - - - - - - - - - */

		// panel locale :



    panelModeJeu.add(new JLabel("Theme de Jeu :"));
    for(int i = 0; i < this.rbModeJeu.length ; i++)
    {
      grpTheme.add(this.rbModeJeu[i]		 );
      panelModeJeu.add(this.rbModeJeu[i]);
    }

    panelChoixFinal.add(this.btnValider);
    panelChoixFinal.add(this.btnQuitter);

    panelBas.add(panelChoixFinal, BorderLayout.SOUTH);
    panelBas.add(panelModeJeu, BorderLayout.NORTH);


		// panel Privée :

    this.panelEntete.add(this.lblModeDeJeu);


		this.add(this.panelEntete, BorderLayout.NORTH);
    this.add(panelSaisie, BorderLayout.CENTER);
    this.add(panelBas, BorderLayout.SOUTH);



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


    for(int cpt = 0; cpt < this.rbModeJeu.length; cpt++)
    {
      this.rbModeJeu[cpt].addActionListener(this);
    }


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
          arretMusic();
          this.setVisible(false);
          this.ctrl.setJoueur(this.txtJoueur[0].getText(), this.txtJoueur[1].getText());
          this.ctrl.setModeDeJeu(getsModeJeu());
          this.ctrl.ouvrir()	;
        }
      }
    }

    if (e.getSource() instanceof JRadioButton)
    {
      arretMusic();
      JRadioButton rbTemp = (JRadioButton) e.getSource();
      if(rbTemp.getText().equals("Default")){this.lblModeDeJeu = new JLabel(new ImageIcon(new ImageIcon ("Images/"+rbTemp.getText()+"/Menu/Menu.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))); }
      else{this.lblModeDeJeu = new JLabel(new ImageIcon(new ImageIcon ("Images/"+rbTemp.getText()+"/Menu/Menu.gif").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT))); play();}
      this.panelEntete.removeAll();
      this.panelEntete.add(this.lblModeDeJeu);

      this.panelEntete.validate();
      this.panelEntete.repaint();
      this.repaint();
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

  public String getsModeJeu()
  {
    for (int cptLig = 0; cptLig < this.rbModeJeu.length; cptLig++)
    {
      if (this.rbModeJeu[cptLig].isSelected()) { return this.rbModeJeu[cptLig].getText();}
    }
    return "";
  }



  // recupere les fichier audio

   public	void playMusic() throws UnsupportedAudioFileException, IOException, LineUnavailableException
   {
     File file = new File("Images/"+getsModeJeu()+"/Music/Menu.wav");
     AudioInputStream audioStream =	AudioSystem.getAudioInputStream(file);
     this.clip = AudioSystem.getClip();
     this.clip.open(audioStream);
     this.clip.start();
   }

   public	void play()
   {
     try {
           playMusic();

         } catch (UnsupportedAudioFileException | IOException
         | LineUnavailableException e) {
      e.printStackTrace();
     }
   }

   public	void stop()
   {
     this.clip.stop();
   }

   public	void arretMusic()
   {
     if(this.clip  != null && this.clip.isRunning()) { stop();}
   }


 // joue le fichier Audio
}
