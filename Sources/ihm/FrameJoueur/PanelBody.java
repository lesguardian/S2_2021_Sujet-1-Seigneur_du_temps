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


public class PanelBody extends JPanel implements ItemListener, ActionListener
{
	private Controleur   ctrl;
  private Joueur       joueur;
	private static int nbTour = 0;
	private int iTemp            = 0;

	private ButtonGroup grpChoix;

	private JComboBox<Object>  comboSystem;
	private JButton[]  				 btnPlanet;
  private JPanel             panelUpdateButton, panelSelection ;
  private JRadioButton rbPrendre, rbLiberer;
	private String sModeDeJeu;

	/** Constructeur de la class PanelBody.
	*    @param ctrl Controleur
	*    @param joueur Joueur
	*/
	public PanelBody (Controleur ctrl, Joueur player, String sModeDeJeu )
	{
    this.ctrl = ctrl;
    this.joueur = player;
		this.sModeDeJeu = sModeDeJeu;

    this.setLayout(new BorderLayout());

    // Variable locale

    JPanel panelListSystem ;
		this.panelSelection = new JPanel();
    JLabel lblNomSystem = new JLabel("System :");
    JLabel lblNomPlanet = new JLabel("Planet :");

    this.rbPrendre = new JRadioButton("Prendre");
    this.rbLiberer = new JRadioButton("Liberer");

    Border lineborder = BorderFactory.createLineBorder(Color.black, 2);

    // initialisation

    panelListSystem = new JPanel();
    this.panelUpdateButton = new JPanel();
    this.comboSystem = new JComboBox<Object>();


    // ajout des systeme


    panelSelection.setBorder(lineborder);


    grpChoix = new ButtonGroup();
    grpChoix.add( this.rbPrendre);
    grpChoix.add( this.rbLiberer);

    panelSelection.add(this.rbPrendre);
    panelSelection.add(this.rbLiberer);

		panelListSystem.add(lblNomSystem);
		panelListSystem.add(this.comboSystem);


		this.add(panelListSystem, BorderLayout.NORTH);
    this.add(this.panelUpdateButton, BorderLayout.CENTER);
    this.add(panelSelection, BorderLayout.SOUTH);

    // listener

		this.comboSystem.addItemListener(this);
		this.rbPrendre.addActionListener(this);
		this.rbLiberer.addActionListener(this);
		verifSenario();
	}


	public void setNewPlanet(int NumSystem)
	{
		this.remove(this.panelUpdateButton);
		this.panelUpdateButton = new JPanel();
		this.panelUpdateButton.setLayout(new FlowLayout());

		this.btnPlanet = new JButton[this.ctrl.getAllSystem().get(NumSystem).getiNbPlanete()];

		for(int cpt = 0; cpt < this.ctrl.getAllSystem().get(NumSystem).getiNbPlanete() ; cpt++ )
		{
			if(!this.ctrl.getAllSystem().get(NumSystem).getOnePlanet(cpt).getPlanetSelec())
			{
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+this.sModeDeJeu+"/Planet/"+this.ctrl.getOneSystem(NumSystem).getOnePlanet(cpt).getsCoulPlanete()+".png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
				this.btnPlanet[cpt]= new JButton(imageIcon);
				this.btnPlanet[cpt].addActionListener(this);
				this.panelUpdateButton.add(this.btnPlanet[cpt]);
			}
		}
		this.add(this.panelUpdateButton, BorderLayout.CENTER);
		this.validate();
	}

	/** Supprime les planètes du système numéro au entré en paramètre
	*    @param iNumSystem Controleur
	*/
	public void setRemovePlanet(int NumSystem)
	{
		this.remove(this.panelUpdateButton);
		this.panelUpdateButton = new JPanel();
		this.panelUpdateButton.setLayout(new FlowLayout());


		this.btnPlanet = new JButton[this.ctrl.getAllSystem().get(NumSystem).getiNbPlanete()];

		for(int cpt = 0; cpt < this.ctrl.getAllSystem().get(NumSystem).getiNbPlanete() ; cpt++ )
		{
			if(this.ctrl.getAllSystem().get(NumSystem).getOnePlanet(cpt).getPlanetSelecJoueur(this.joueur))
			{
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("Images/"+this.sModeDeJeu+"/Planet/"+this.ctrl.getOneSystem(NumSystem).getOnePlanet(cpt).getsCoulPlanete()+".png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
				this.btnPlanet[cpt]= new JButton(imageIcon);
				this.btnPlanet[cpt].addActionListener(this);
				this.panelUpdateButton.add(this.btnPlanet[cpt]);
			}
		}
		this.add(this.panelUpdateButton, BorderLayout.CENTER);
		this.validate();
	}

	/** Cette méthode, en fonction de l'action, libère une planète ou en sélectionne une. Elle permet également de griser le bouton rbLibérer si l'utilisateur a déjà libérer une planète.
	*  @param btn Bouton sélectionné par le joueur
	*  @param cAction Action (Libérer ou Prendre)
	*/
  public void action(JButton b, char action)
  {
		String e = (String)this.comboSystem.getSelectedItem();
		int c = 0;
		int iNumSystemLib = 0;
		for(SystemeSolaire a : this.ctrl.getAllSystem())
		{
			if(e.equals(a.getsNom())){c = a.getiNumSystem()-1; iNumSystemLib = a.getiNumSystem()-1 ;}
		}

		if(action == 'L')
		{
			for(int a= 0; a< this.btnPlanet.length; a++)
			{
				if(this.btnPlanet[a] == b)
				{
					this.ctrl.action (this.joueur.getiNumJoueur()-1, action, a , c);
				}

			}
			this.rbLiberer.setEnabled(false);
			this.rbPrendre.setSelected(true);
			affichagePrendre2(iNumSystemLib);
			this.comboSystem.setSelectedIndex(0);
			this.rbPrendre.setEnabled(false);
		}


		else
		{
			for(int a= 0; a< this.btnPlanet.length; a++)
			{
				if(this.btnPlanet[a] == b)
				{
					this.ctrl.action (this.joueur.getiNumJoueur()-1, action, a , c);
					this.nbTour++;
					if(this.rbLiberer.isEnabled()){ this.rbLiberer.setEnabled(true);}
					if(!this.rbPrendre.isEnabled()){ this.rbPrendre.setEnabled(true);}
				}
			}
			this.rbLiberer.setEnabled(true);
		}
  }


	/** Fait la mise à jour du JComboBox
	*
	*/
	public void maj()
	{
		try{
			this.comboSystem.removeAllItems();
			this.panelSelection.add(this.rbPrendre);
			this.panelSelection.add(this.rbLiberer);
			this.validate();


		}catch(Exception e)
		{
			this.grpChoix.clearSelection();
			removeButton();
			if(this.rbLiberer.isEnabled() == true) { this.rbLiberer.setEnabled(false); }
			this.remove(this.panelSelection);
			this.panelSelection 	 = new JPanel();
			Border lineborder = BorderFactory.createLineBorder(Color.black, 2);
			this.panelSelection.setBorder(lineborder);
			this.add(panelSelection, BorderLayout.SOUTH);
			this.validate();
		}
	}



	/** Méthode obligatoire qui permet de faire la partie dynamique de l'IHM. Elle permet d'afficher les planètes dans le PanelBody, de vider le JComboBox et de retirer tous les boutons.
	*@param e Variable permettant de repérer l'évènement en cours.
	*/
	public void actionPerformed(ActionEvent e)
	{

		if(e.getSource() instanceof JButton )
		{
			JButton rbTemp = (JButton) e.getSource();
			if(this.rbPrendre.isSelected() && this.iTemp == 1) { action(rbTemp, 'p'); this.iTemp = 0;}
			if(this.rbPrendre.isSelected() && this.iTemp == 0) { action(rbTemp, 'P');}
			if(this.rbLiberer.isSelected()) { action(rbTemp, 'L'); this.iTemp = 1;}
		}

		if(e.getSource() instanceof JRadioButton )
		{
			JRadioButton rbTemp = (JRadioButton) e.getSource();
			try{
			if(this.rbPrendre.isSelected()) { removeButton(); this.comboSystem.removeAllItems(); affichagePrendre();}
			if(this.rbLiberer.isSelected()) { removeButton();this.comboSystem.removeAllItems(); affichageLiberer();}
			}catch(Exception u){}
		}
	}

	public void removeButton()
	{
		this.remove(this.panelUpdateButton);
	  this.panelUpdateButton = new JPanel();
		this.add(this.panelUpdateButton, BorderLayout.CENTER);
	}


	public void affichagePrendre()
	{
		this.comboSystem.removeAllItems();
		for(int cpt = 0; cpt < this.ctrl.getAllSystem().size(); cpt++)
		{
			if(!this.ctrl.getAllSystem().get(cpt).getPlaneteAllSelected())
			{
				this.comboSystem.addItem(this.ctrl.getAllSystem().get(cpt).getsNom());
			}
		}
	}

	/**  Cela vide le JComboBox et y ajoute tous les systèmes où le joueur peut libérer au moins une planète.
	*
	*/
	public void affichageLiberer()
	{
		this.comboSystem.removeAllItems();

		for( int cpt = 1; cpt < this.ctrl.getAllSystem().size() ; cpt++)
		{
			if (this.ctrl.getAllSystem().get(cpt).getPlaneteOneSelectedJoueurA(this.joueur))
			{
				for(int cpt1 = 0; cpt1 < cpt; cpt1++)
				{
					if(!this.ctrl.getAllSystem().get(cpt1).getPlaneteAllSelected()) {this.comboSystem.addItem(this.ctrl.getAllSystem().get(cpt).getsNom());}
				}
			}
		}
	}

	/** Cela vide le JComboBox et y ajoute tous les systèmes où le joueur peut prendre au moins une planète.
	* @param iNumSystemLib Numéro du système
	*/

	public void affichagePrendre2(int iNumSystemLib)
	{
		iNumSystemLib = iNumSystemLib+1;
		this.comboSystem.removeAllItems();

		for(SystemeSolaire d: this.ctrl.getAllSystem())
		{
			if((d.getiNumSystem() < iNumSystemLib) && (!d.getPlaneteAllSelected()))
			{
				this.comboSystem.addItem(d.getsNom());
			}
		}
	}

	/** Méthode obligatoire qui permet de faire la partie dynamique de l'IHM. Elle permet d'afficher les nouvelles planètes dans le PanelBody
	*@param e Variable permettant de repérer l'évènement en cours.
	*/

	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() instanceof JComboBox)
		{
			String b = (String)this.comboSystem.getSelectedItem();

			for(SystemeSolaire a : this.ctrl.getAllSystem())
			{
				if(b.equals(a.getsNom()) && this.rbPrendre.isSelected()){ setNewPlanet(a.getiNumSystem()-1);}
				if(b.equals(a.getsNom()) && this.rbLiberer.isSelected() ){ setRemovePlanet(a.getiNumSystem()-1);}
			}
		}
	}


	/** Permet la vérification des scénarios.
	*
	*/
	public void verifSenario()
	{
		int cpt =0;
		for(SystemeSolaire system : this.ctrl.getAllSystem())
		{
			for(int cpt1 = 0 ; cpt1 < system.getiNbPlanete() ; cpt1++)
			{
				if(system.verificationPlanetCoulJ(system.getOnePlanet(cpt1).getsCoulPlanete(), this.joueur))
				{
					cpt++;
				}
			}
		}
		if(cpt == 8)
		{
			this.grpChoix.clearSelection();
			removeButton();
			if(this.rbLiberer.isEnabled() == true) { this.rbLiberer.setEnabled(false); }
			this.remove(this.panelSelection);
			this.panelSelection 	 = new JPanel();
			Border lineborder = BorderFactory.createLineBorder(Color.black, 2);
			this.panelSelection.setBorder(lineborder);
			this.add(panelSelection, BorderLayout.SOUTH);
			this.validate();
		}
	}

}
