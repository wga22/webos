package com.willallen.GWTBigDeuce.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.*;
import com.willallen.GWTBigDeuce.client.*;

public class GWTBigDeuce implements EntryPoint
{
	private Hand northHand;
	private Hand westHand;
	private Hand eastHand;
	private Hand southHand;
	private Label lblMsg;
	private Frame frame;
	private MenuBar mb;
	private MenuItem[] menu;
	private MenuItem[] itmFile;
	private MenuItem[] itmPlay;
	private MenuItem[] itmHelp;
	private Button btnNew;
	private Button btnPass;
	private Button btnSend;
	private Button btnExit;
	private Button[] btnNorthPanel;
	private Button[] btnWestPanel;
	private Button[] btnEastPanel;
	private Button[] btnSouthPlayPanel;
	private Button[] btnSouthTempPanel;
	private Panel northPanel;
	private Panel westPanel;
	private Panel eastPanel;
	private Panel southPanel;
	private Panel southPlayPanel;
	private Panel southTempPanel;
	private Panel cntrPanel;
	private Panel cntrTopPanel;
	private Panel cntrHandPanel;
	private Panel cntrMsgPanel;
	private Grid cntrBottomPanel;
	private int[] arySouthPlayPanelSelected = new int[13];
	private int[] arySouthTempPanelSelectedSuit = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int[] arySouthPlayPanelSelectedSuit = { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
	private int currSouthTempPanelSelected = -1;
	private int currSouthPlayPanelSelected = -1;
	private int currTurn;
	private int currLeader;
	private tableHand currHand;
	private BigDHandler big2handler = null;
	// private static final Color YELLOW = Color.yellow;
	// private static Color DEFAULTCOLOR;
	private PopupPanel popup = new PopupPanel();

	GWTBigDeuce()
	{
		big2handler = new BigDHandler();
		initHand();
		//TODO: reinitGUI();
		// DEFAULTCOLOR = getBackground();
		//TODO: initPlay();
	}

	/*
	 * public void actionPerformed(ActionEvent paramActionEvent) { Object
	 * localObject = paramActionEvent.getSource(); String str; if ((localObject
	 * instanceof Button)) { str = paramActionEvent.getActionCommand();
	 * 
	 * if (str.equals("New Game")) { newGame(); } else if
	 * (str.equals("Pass Turn")) { passTurn(); } else if (str.equals("Send Up"))
	 * { sendUp(); } else if (str.equals("Exit Game")) { exitGame(); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[0]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[0], 0); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[1]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[1], 1); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[2]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[2], 2); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[3]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[3], 3); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[4]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[4], 4); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[5]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[5], 5); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[6]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[6], 6); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[7]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[7], 7); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[8]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[8], 8); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[9]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[9], 9); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[10]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[10], 10); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[11]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[11], 11); } else if
	 * (paramActionEvent.getSource() == this.btnSouthTempPanel[12]) {
	 * setSouthTempPanelState(this.btnSouthTempPanel[12], 12); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[0]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[0], 0); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[1]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[1], 1); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[2]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[2], 2); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[3]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[3], 3); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[4]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[4], 4); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[5]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[5], 5); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[6]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[6], 6); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[7]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[7], 7); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[8]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[8], 8); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[9]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[9], 9); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[10]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[10], 10); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[11]) {
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[11], 11); } else if
	 * (paramActionEvent.getSource() == this.btnSouthPlayPanel[12])
	 * setSouthPlayPanelState(this.btnSouthPlayPanel[12], 12); } else if
	 * ((localObject instanceof MenuItem)) { str = ((MenuItem)
	 * paramActionEvent.getSource()).getText(); if (str.equals("Exit")) {
	 * exitGame(); } else if (str.equals("New Game")) { newGame(); } else if
	 * (str.equals("Pass Turn")) { passTurn(); } else if (str.equals("Send Up"))
	 * { sendUp(); } else if (str.equals("About")) { popup.center();
	 * popup.setTitle("BigDeuce By Will Allen"); popup.show();
	 * //OptionPane.showMessageDialog(this.frame,
	 * "<html><b><center>BigDeuce By Will Allen</b></html>","Information", 1); }
	 * } }
	 */
	private void centerOnScreen(Frame paramJFrame)
	{
		// Dimension localDimension1 =
		// Toolkit.getDefaultToolkit().getScreenSize();
		// Dimension localDimension2 = paramJFrame.getSize();
		// int i = localDimension1.width / 2;
		// int j = localDimension1.height / 2;
		// paramJFrame.setLocation(i - localDimension2.width / 2, j -
		// localDimension2.height / 2);
	}

	private void exitGame()
	{
		// windowClosing(null);
	}

	private void freezeSouthHand(boolean paramBoolean)
	{
		for (int i = 0; i < this.btnSouthPlayPanel.length; i++)
			if (paramBoolean == true)
			{
				this.btnSouthPlayPanel[i].setEnabled(false);
				this.btnSouthTempPanel[i].setEnabled(false);
			} else
			{
				this.btnSouthPlayPanel[i].setEnabled(true);
				this.btnSouthTempPanel[i].setEnabled(true);
			}
	}

	//@Override
	public void onModuleLoadOld()
	{

		Label lastUpdatedLabel = new Label("this is a test");
		VerticalPanel mainPanel = new VerticalPanel();
		// Assemble Main panel.
		mainPanel.add(lastUpdatedLabel);
		
		//RootPanel.get()("oakTable").add(mainPanel);
		RootPanel.get().add(mainPanel);
		
	}
	@Override
	public void onModuleLoad()
	{

		// UIManager.getSystemLookAndFeelClassName();

		// this.frame = new Frame(paramString);

		//setMenuBar();

		LayoutPanel localJPanel = new LayoutPanel();
		// localJPanel.setBackground(getBackground());
		localJPanel.setStylePrimaryName("be cool");
		// localJPanel.setForeground(getForeground());
		// localJPanel.setFont(getFont());
		// localJPanel.setLayout(new BorderLayout());

		card[] arrayOfcard = this.northHand.getHand();
		this.northPanel = new VerticalPanel();
		// this.northPanel.setLayout(new Grid(1, arrayOfcard.length));
		this.btnNorthPanel = new Button[arrayOfcard.length];
		for (int i = 0; i < arrayOfcard.length; i++)
		{
			this.northPanel.add(this.btnNorthPanel[i] = new Button(arrayOfcard[i].getSuit() + "-"
					+ arrayOfcard[i].getValue()));
		}
		// this.northPanel.setPreferredSize(new Dimension(1000, 30));
		this.northPanel.setSize("1000px", "30px");

		arrayOfcard = this.westHand.getHand();
		this.westPanel = new VerticalPanel();
		// this.westPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnWestPanel = new Button[arrayOfcard.length];
		for (int j = 0; j < arrayOfcard.length; j++)
		{
			this.westPanel.add(this.btnWestPanel[j] = new Button(arrayOfcard[j].getSuit() + "-"
					+ arrayOfcard[j].getValue()));
		}
		// this.westPanel.setPreferredSize(new Dimension(80, 520));
		this.westPanel.setSize("80px", "520px");

		arrayOfcard = this.eastHand.getHand();
		this.eastPanel = new VerticalPanel();
		// this.eastPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnEastPanel = new Button[arrayOfcard.length];
		for (int k = 0; k < arrayOfcard.length; k++)
			this.eastPanel.add(this.btnEastPanel[k] = new Button(arrayOfcard[k].getSuit() + "-"
					+ arrayOfcard[k].getValue()));
		// this.eastPanel.setPreferredSize(new Dimension(80, 520));
		this.eastPanel.setSize("80px", "520px");

		arrayOfcard = this.southHand.getHand();
		this.southPanel = new HorizontalPanel();

		// this.southPanel.setLayout(new GridLayout(2, 1));
		this.southPlayPanel = new HorizontalPanel();
		// this.southPlayPanel.setBorder(BorderFactory.createTitledBorder("Cards To Rearrange OR For Send Up"));
		this.southPlayPanel.setTitle("Cards To Rearrange OR For Send Up");
		this.southTempPanel = new HorizontalPanel();
		this.southTempPanel.setTitle("Cards On Hand");

		this.btnSouthPlayPanel = new Button[arrayOfcard.length];
		for (int m = 0; m < arrayOfcard.length; m++)
		{
			this.southPlayPanel.add(this.btnSouthPlayPanel[m] = new Button("  "));
			// this.btnSouthPlayPanel[m].addActionListener(this);
			// ClickHandler handler = ClickHandler.;
			this.btnSouthPlayPanel[m].addClickHandler(big2handler);
		}
		// this.southTempPanel.setLayout(new GridLayout(1, arrayOfcard.length));

		this.btnSouthTempPanel = new Button[arrayOfcard.length];
		for (int n = 0; n < arrayOfcard.length; n++)
		{
			int i1 = arrayOfcard[n].getSuit();
			int i2 = arrayOfcard[n].getValue();
			String str = "";
			Image localImageIcon1 = null;
			Image localImageIcon2 = null;
			Image localImageIcon3 = null;
			Image localImageIcon4 = null;
			switch (i2)
			{
			case 11:
				str = "J";
				break;
			case 12:
				str = "Q";
				break;
			case 13:
				str = "K";
				break;
			case 1:
				str = "A";
				break;
			case 2:
				str = "2";
				break;
			case 3:
				str = "3";
				break;
			case 4:
				str = "4";
				break;
			case 5:
				str = "5";
				break;
			case 6:
				str = "6";
				break;
			case 7:
				str = "7";
				break;
			case 8:
				str = "8";
				break;
			case 9:
				str = "9";
				break;
			case 10:
				str = "10";
				break;
			}
			switch (i1)
			{
			case 1:
				localImageIcon1 = new Image("./spade.gif");
				this.southTempPanel.add(this.btnSouthTempPanel[n] = new Button(str));
				this.arySouthTempPanelSelectedSuit[n] = 1;
				break;
			case 2:
				localImageIcon2 = new Image("./heart.gif");
				this.southTempPanel.add(this.btnSouthTempPanel[n] = new Button(str));
				this.arySouthTempPanelSelectedSuit[n] = 2;
				break;
			case 3:
				localImageIcon3 = new Image("./club.gif");
				this.southTempPanel.add(this.btnSouthTempPanel[n] = new Button(str));
				this.arySouthTempPanelSelectedSuit[n] = 3;
				break;
			case 4:
				localImageIcon4 = new Image("./diamond.gif");
				this.southTempPanel.add(this.btnSouthTempPanel[n] = new Button(str));
				this.arySouthTempPanelSelectedSuit[n] = 4;
				break;
			}
			// this.btnSouthTempPanel[n].addActionListener(this);
			this.btnSouthTempPanel[n].addClickHandler(big2handler);

		}
		// this.southTempPanel.setPreferredSize(new Dimension(1000, 30));
		this.southTempPanel.setSize("1000px", "30px");
		this.southPanel.add(this.southPlayPanel);
		this.southPanel.add(this.southTempPanel);

		this.cntrPanel = new HorizontalPanel();
		// this.cntrPanel.setLayout(new BoxLayout(this.cntrPanel, 1));
		this.cntrTopPanel = new FlowPanel();
		this.cntrTopPanel.setSize("700px", "150px");
		// this.cntrTopPanel.setLayout(new FlowLayout());

		this.cntrHandPanel = new SimplePanel();
		// this.cntrHandPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrHandPanel.setSize("700px", "50px");
		this.cntrHandPanel.add(new Label("Current Hand"));

		this.cntrMsgPanel = new SimplePanel();
		// this.cntrMsgPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrMsgPanel.setSize("700px", "50px");
		this.cntrMsgPanel.add(this.lblMsg = new Label(""));

		this.cntrBottomPanel = new Grid(1, 5); // 1,5
		// this.cntrBottomPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrBottomPanel.setSize("700px", "50px");
		// this.cntrBottomPanel.setLayout(new GridLayout(1, 5));
		this.cntrBottomPanel.setTitle("needs border");
		
		Button newBtn = new Button("New Game");
		newBtn.addClickHandler(big2handler);
		this.cntrBottomPanel.setWidget(0, 0, newBtn);
		
		this.btnPass = new Button("Pass Turn");
		this.btnPass.addClickHandler(big2handler);
		this.cntrBottomPanel.setWidget(0,1,this.btnPass);
		
		this.btnSend = new Button("Send Up");
		this.btnSend.addClickHandler(big2handler);
		this.cntrBottomPanel.setWidget(0,2,this.btnSend);

		this.btnExit = new Button("Exit Game");
		this.btnExit.addClickHandler(big2handler);
		this.cntrBottomPanel.setWidget(0,3,this.btnExit );

		this.cntrPanel.add(this.cntrTopPanel);
		this.cntrPanel.add(this.cntrHandPanel);
		this.cntrPanel.add(this.cntrMsgPanel);
		this.cntrPanel.add(this.cntrBottomPanel);

		// localJPanel.add("North", this.northPanel);
		localJPanel.add(this.northPanel);
		// localJPanel.add("West", this.westPanel);
		localJPanel.add(this.westPanel);
		// localJPanel.add("East", this.eastPanel);
		localJPanel.add(this.eastPanel);
		// localJPanel.add("South", this.southPanel);
		localJPanel.add(this.southPanel);
		// localJPanel.add("Center", this.cntrPanel);
		localJPanel.add(this.cntrPanel);

		localJPanel.setSize("1000px", "420px");

		// this.frame.getContentPane().setLayout(new BorderLayout());
		// this.frame.getContentPane().add(localJPanel, "Center");
		// this.frame.getContentPane().add(localJPanel);
		// this.frame.setSize(new Dimension(1000, 420));
		// this.frame.addWindowListener(this);
		// this.frame.pack();
		// this.frame.setVisible(true);
		//TODO centerOnScreen(this.frame);
		RootPanel.get().add(localJPanel);
	}

	private Object getBackground()
	{
		// TODO Auto-generated method stub
		return null;
	}

	private void initHand()
	{
		deck localdeck = new deck();
		this.northHand = new Hand(localdeck.getNextN(13));
		this.westHand = new Hand(localdeck.getNextN(13));
		this.eastHand = new Hand(localdeck.getNextN(13));
		this.southHand = new Hand(localdeck.getNextN(13));
	}

	private void initPlay()
	{
		if (this.southHand.getLead() == true)
		{
			this.lblMsg.setText("You start play first!");
			this.currTurn = 0;
			this.currLeader = 0;
		}
		card[] arrayOfcard;
		int i;
		if (this.eastHand.getLead() == true)
		{
			this.lblMsg.setText("East start play first!");
			this.currTurn = 2;
			this.currLeader = 1;
			arrayOfcard = this.eastHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("East Hand");
			this.eastPanel.clear();
			arrayOfcard = this.eastHand.getHand();
			// this.eastPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
			this.btnEastPanel = new Button[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.eastPanel.add(this.btnEastPanel[i] = new Button(arrayOfcard[i].getSuit() + "-"
						+ arrayOfcard[i].getValue()));
			// this.eastPanel.validate();
		}
		if (this.northHand.getLead() == true)
		{
			this.lblMsg.setText("North start play first!");
			this.currTurn = 3;
			this.currLeader = 2;
			arrayOfcard = this.northHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("North Hand");
			this.northPanel.clear();
			arrayOfcard = this.northHand.getHand();
			// this.northPanel.setLayout(new GridLayout(1, arrayOfcard.length));
			this.btnNorthPanel = new Button[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.northPanel.add(this.btnNorthPanel[i] = new Button(arrayOfcard[i].getSuit() + "-"
						+ arrayOfcard[i].getValue()));
			// this.northPanel.validate();
		}
		if (this.westHand.getLead() == true)
		{
			this.lblMsg.setText("West start play first!");
			this.currTurn = 0;
			this.currLeader = 3;
			arrayOfcard = this.westHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("West Hand");
			// this.westPanel.removeAll();
			this.westPanel.clear();
			arrayOfcard = this.westHand.getHand();
			// this.westPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
			this.btnWestPanel = new Button[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.westPanel.add(this.btnWestPanel[i] = new Button(arrayOfcard[i].getSuit() + "-"
						+ arrayOfcard[i].getValue()));
			// this.westPanel.validate();
		}
		startPlay();
	}

	private void newGame()
	{
		reinitGUI();
		initPlay();
	}

	private void passTurn()
	{
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;
		if (this.southHand.getLead() == true)
		{
			// TODO: show message
			// OptionPane.showMessageDialog(this.frame,
			// "You must select 3 of Diamond to start play!", "Error", 0);
		} else
		{
			freezeSouthHand(true);
			this.currTurn = 1;
			startPlay();
		}
	}

	private Button[] playHand(card[] arrayOfCard, Panel p)
	{
		p.clear();
		Button[] buttons = new Button[arrayOfCard.length];
		for (int j = 0; j < arrayOfCard.length; j++)
		{
			p.add(buttons[j] = new Button(arrayOfCard[j].getSuit() + "-" + arrayOfCard[j].getValue()));
		}
		return buttons;
	}

	private void reinitGUI()
	{
		int i = this.southHand.getHandSize();

		initHand();
		this.btnPass.setEnabled(true);
		this.btnSend.setEnabled(true);
		this.itmPlay[1].setEnabled(true);
		this.itmPlay[2].setEnabled(true);

		// this.northPanel.removeAll();
		this.btnNorthPanel = playHand(this.northHand.getHand(), this.northPanel);
		this.btnWestPanel = playHand(this.westHand.getHand(), this.westPanel);
		this.btnEastPanel = playHand(this.eastHand.getHand(), this.eastPanel);

		for (int n = 0; n < this.btnSouthTempPanel.length; n++)
		{
			// this.btnSouthTempPanel[n].setIcon(null);
			this.btnSouthTempPanel[n].setText("  ");
			// this.btnSouthTempPanel[n].setBackground(DEFAULTCOLOR);
		}
		for (int i1 = 0; i1 < this.btnSouthPlayPanel.length; i1++)
		{
			// this.btnSouthPlayPanel[i1].setIcon(null);
			this.btnSouthPlayPanel[i1].setText("  ");
			// this.btnSouthPlayPanel[i1].setBackground(DEFAULTCOLOR);
			this.arySouthPlayPanelSelected[i1] = 0;
		}

		freezeSouthHand(false);
		card[] playersCards = this.southHand.getHand();
		for (int i2 = 0; i2 < playersCards.length; i2++)
		{
			int i3 = playersCards[i2].getSuit();
			int i4 = playersCards[i2].getValue();
			String str = "";
			switch (i4)
			{
			case 11:
				str = "J";
				break;
			case 12:
				str = "Q";
				break;
			case 13:
				str = "K";
				break;
			case 1:
				str = "A";
				break;
			case 2:
				str = "2";
				break;
			case 3:
				str = "3";
				break;
			case 4:
				str = "4";
				break;
			case 5:
				str = "5";
				break;
			case 6:
				str = "6";
				break;
			case 7:
				str = "7";
				break;
			case 8:
				str = "8";
				break;
			case 9:
				str = "9";
				break;
			case 10:
				str = "10";
				break;
			}
			this.btnSouthTempPanel[i2].setText(str);

			switch (i3)
			{
			case 1:
				// localImageIcon1 = new ImageIcon("./spade.gif");
				// this.btnSouthTempPanel[i2].setIcon(localImageIcon1);
				this.arySouthTempPanelSelectedSuit[i2] = 1;
				break;
			case 2:
				// localImageIcon2 = new ImageIcon("./heart.gif");
				// this.btnSouthTempPanel[i2].setIcon(localImageIcon2);
				this.arySouthTempPanelSelectedSuit[i2] = 2;
				break;
			case 3:
				// localImageIcon3 = new ImageIcon("./club.gif");
				// this.btnSouthTempPanel[i2].setIcon(localImageIcon3);
				this.arySouthTempPanelSelectedSuit[i2] = 3;
				break;
			case 4:
				// localImageIcon4 = new ImageIcon("./diamond.gif");
				// this.btnSouthTempPanel[i2].setIcon(localImageIcon4);
				this.arySouthTempPanelSelectedSuit[i2] = 4;
			}
		}

		// this.southPanel.validate();
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;

		// this.cntrTopPanel.clear();
		// this.cntrHandPanel.clear();
		this.cntrHandPanel.add(new Label("Current Hand"));
		// this.cntrTopPanel.validate();
		// this.cntrHandPanel.validate();
		// this.cntrHandPanel.repaint();
		// this.cntrTopPanel.repaint();
	}

	private void sendUp()
	{
		int i = this.southHand.getHandSize();
		Vector localVector = new Vector();
		boolean[] arrayOfBoolean = new boolean[1];
		int j = 1;
		for (int k = 0; k < 13; k++)
		{
			if (this.arySouthPlayPanelSelected[k] == 1)
				if (!this.btnSouthPlayPanel[k].getText().equals("  "))
				{
					localVector.addElement(new Integer(k));
				} else
				{
					j = 0;
					break;
				}
		}
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;

		if ((localVector.size() == 0) || (j == 0))
		{
			// TODO: OptionPane.showMessageDialog(this.frame,
			// "Error in your selection!", "Error", 0);
			return;
		}
		card[] arrayOfcard1 = null;
		int n;
		if (localVector.size() != 0)
		{
			arrayOfcard1 = new card[localVector.size()];
			for (int m = 0; m < localVector.size(); m++)
			{
				n = 0;
				String str = this.btnSouthPlayPanel[((Integer) localVector.elementAt(m)).intValue()].getText();
				if (str.equals("A"))
					n = 1;
				else if (str.equals("J"))
					n = 11;
				else if (str.equals("Q"))
					n = 12;
				else if (str.equals("K"))
					n = 13;
				else
				{
					n = new Integer(str).intValue();
				}
				arrayOfcard1[m] = new card(
						this.arySouthPlayPanelSelectedSuit[((Integer) localVector.elementAt(m)).intValue()], n);
			}
		}
		card[] arrayOfcard2;
		if (this.currLeader == 0)
		{
			if (this.southHand.getLead() == true)
				arrayOfcard2 = this.southHand.playerSelected3DiamondCard(arrayOfcard1, arrayOfBoolean);
			else
				arrayOfcard2 = this.southHand.playerSelectedCard(arrayOfcard1, arrayOfBoolean);
		} else
			arrayOfcard2 = this.southHand.playerSelectedCard(arrayOfcard1, arrayOfBoolean, this.currHand);
		if (arrayOfcard2 != null)
		{
			this.southHand.setLead(false);
			this.currLeader = 0;
			this.currHand = new tableHand(arrayOfcard2);
			updateCenterPanel("You");

			for (n = 0; n < 13; n++)
				if (this.arySouthPlayPanelSelected[n] == 1)
				{
					this.arySouthPlayPanelSelected[n] = 0;
					// this.btnSouthPlayPanel[n].setBackground(DEFAULTCOLOR);
					// this.btnSouthPlayPanel[n].setIcon(null);
					this.btnSouthPlayPanel[n].setText("  ");
				}
			if (this.southHand.getHandSize() == 0)
			{
				// TODO: OptionPane.showMessageDialog(this.frame,
				// "Congratulations! You have won!", "Information", 1);
				this.lblMsg.setText("Congratulations! You have won!");
				this.btnPass.setEnabled(false);
				this.btnSend.setEnabled(false);
				this.itmPlay[1].setEnabled(false);
				this.itmPlay[2].setEnabled(false);
			} else
			{
				this.currTurn = 1;
				startPlay();
			}
		} else
		{
			// TODO: OptionPane.showMessageDialog(this.frame,
			// "Error in your selection!", "Error", 0);
		}
	}

	private MenuBar setMenuBar()
	{
		this.mb = new MenuBar();

		this.menu = new MenuItem[3];
		this.menu[0] = new MenuItem("File", this.mb);
		// this.menu[0].setMnemonic(70);
		this.menu[1] = new MenuItem("Play", this.mb);
		// this.menu[1].setMnemonic(80);
		this.menu[2] = new MenuItem("Help", this.mb);
		// this.menu[2].setMnemonic(72);

		this.itmFile = new MenuItem[1];
		this.itmFile[0] = new MenuItem("Exit", this.mb);
		// this.itmFile[0].setMnemonic(88);
		// for (int i = 0; i < this.itmFile.length; i++)
		{
			// this.itmFile[i].addActionListener(this);
			// this.itmFile[i].(this);
			// this.menu[0].add(this.itmFile[i]);
			// this.menu[0].setSubMenu(this.itmFile[i]);
		}

		this.itmPlay = new MenuItem[3];
		this.itmPlay[0] = new MenuItem("New Game", this.mb);
		// this.itmPlay[0].setMnemonic(71);
		this.itmPlay[1] = new MenuItem("Pass Turn", this.mb);
		// this.itmPlay[1].setMnemonic(84);
		this.itmPlay[2] = new MenuItem("Send Up", this.mb);
		// this.itmPlay[2].setMnemonic(85);
		/*
		 * for (int j = 0; j < this.itmPlay.length; j++) {
		 * this.itmPlay[j].addActionListener(this);
		 * this.menu[1].add(this.itmPlay[j]); }
		 */
		this.itmHelp = new MenuItem[1];
		this.itmHelp[0] = new MenuItem("About", this.mb);
		// this.itmHelp[0].setMnemonic(65);
		// for (int k = 0; k < this.itmHelp.length; k++)
		{
			// this.itmHelp[k].addActionListener(this);
			// this.menu[2].add(this.itmHelp[k]);
		}

		// for (int m = 0; m < this.menu.length; m++)
		// this.mb.add(this.menu[m]);
		// TODO: this.frame.(this.mb);
		return this.mb;
	}

	private void setSouthPlayPanelState(Button btnSouthPlayPanel2, int paramInt)
	{
		// TODO: let users know
		/*
		 * this.currSouthPlayPanelSelected = paramInt; if
		 * (this.currSouthTempPanelSelected != -1) {
		 * swapSouthPanel(btnSouthPlayPanel2, paramInt,
		 * this.btnSouthTempPanel[this.currSouthTempPanelSelected],
		 * this.currSouthTempPanelSelected); } else if
		 * (btnSouthPlayPanel2.getBackground() == YELLOW) {
		 * btnSouthPlayPanel2.setBackground(DEFAULTCOLOR);
		 * this.arySouthPlayPanelSelected[paramInt] = 0; } else {
		 * btnSouthPlayPanel2.setBackground(YELLOW);
		 * this.arySouthPlayPanelSelected[paramInt] = 1; }
		 */
	}

	private void setSouthTempPanelState(Button btnSouthTempPanel2, int paramInt)
	{
		// todo:
		/*
		 * this.currSouthTempPanelSelected = paramInt; if
		 * (this.currSouthPlayPanelSelected != -1) {
		 * swapSouthPanel(this.btnSouthPlayPanel
		 * [this.currSouthPlayPanelSelected], this.currSouthPlayPanelSelected,
		 * btnSouthTempPanel2, paramInt); } else {
		 * btnSouthTempPanel2.setBackground(YELLOW); for (int i = 0; i < 13;
		 * i++) { if (i == paramInt) continue;
		 * this.btnSouthTempPanel[i].setBackground(DEFAULTCOLOR); } }
		 */
	}

	private void startPlay()
	{
		while (this.currTurn != 0)
		{
			boolean[] arrayOfBoolean = new boolean[1];
			int i = 1;

			int j = this.currHand.getNoCard();
			boolean bool;
			card[] arrayOfcard1;
			card[] arrayOfcard2;
			int k;
			switch (this.currTurn)
			{
			case 1:
				if ((this.currLeader != 0) && (this.currLeader != 1))
				{
					this.currTurn = 2;
				} else
				{
					if (this.southHand.getHandSize() == 1)
					{
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2)
					{
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5)
					{
						bool = true;
						i = 2;
					} else
					{
						bool = false;
						i = 5;
					}

					if (this.currLeader == 1)
						arrayOfcard1 = this.eastHand.computerSelectedCard(arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.eastHand.computerSelectedCard(arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null)
					{
						this.currLeader = 1;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("East Hand");

						if (this.eastHand.getHandSize() == 0)
						{
							// this.eastPanel.removeAll();
							// this.eastPanel.validate();
							// this.eastPanel.repaint();
							this.currTurn = 0;

							//TODO: Option.showMessageDialog(this.frame, "Congratulations! East Hand has won!", "Information",1);
							this.lblMsg.setText("Congratulations! East Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else
						{
							//this.eastPanel.removeAll();
							this.eastPanel.clear();
							arrayOfcard2 = this.eastHand.getHand();

							//this.eastPanel.setLayout(new Grid(arrayOfcard2.length, 1));
							this.btnEastPanel = new Button[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++)
							{
								this.eastPanel.add(this.btnEastPanel[k] = new Button(arrayOfcard2[k].getSuit() + "-"
										+ arrayOfcard2[k].getValue()));
							}
							//this.eastPanel.validate();
							this.currTurn = 2;
						}
					} else
					{
						this.currTurn = 2;
					}
				}
				break;
			case 2:
				if ((this.currLeader != 0) && (this.currLeader != 2))
				{
					this.currTurn = 3;
				} else
				{
					if (this.southHand.getHandSize() == 1)
					{
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2)
					{
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5)
					{
						bool = true;
						i = 2;
					} else
					{
						bool = false;
						i = 5;
					}

					if (this.currLeader == 2)
						arrayOfcard1 = this.northHand.computerSelectedCard(arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.northHand.computerSelectedCard(arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null)
					{
						this.currLeader = 2;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("North Hand");
						
						this.northPanel.clear();
						if (this.northHand.getHandSize() == 0)
						{
							//this.northPanel.removeAll();
							//this.northPanel.validate();
							//this.northPanel.repaint();
							this.currTurn = 0;

							//TODO: messageOptionPane.showMessageDialog(this.frame, "Congratulations! North Hand has won!",	"Information", 1);
							this.lblMsg.setText("Congratulations! North Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else
						{
							//this.northPanel.removeAll();
							arrayOfcard2 = this.northHand.getHand();

							//this.northPanel.setLayout(new Grid(1, arrayOfcard2.length));
							this.btnNorthPanel = new Button[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++)
							{
								this.northPanel.add(this.btnNorthPanel[k] = new Button(arrayOfcard2[k].getSuit() + "-"
										+ arrayOfcard2[k].getValue()));
							}
							//this.northPanel.validate();
							this.currTurn = 3;
						}
					} else
					{
						this.currTurn = 3;
					}
				}
				break;
			case 3:
				if ((this.currLeader != 0) && (this.currLeader != 3))
				{
					freezeSouthHand(false);
					this.currTurn = 0;
				} else
				{
					if (this.southHand.getHandSize() == 1)
					{
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2)
					{
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5)
					{
						bool = true;
						i = 2;
					} else
					{
						bool = false;
						i = 5;
					}
					if (this.currLeader == 3)
						arrayOfcard1 = this.westHand.computerSelectedCard(arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.westHand.computerSelectedCard(arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null)
					{
						this.currLeader = 3;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("West Hand");
						this.westPanel.clear();
						if (this.westHand.getHandSize() == 0)
						{
							//this.westPanel.removeAll();
							//this.westPanel.validate();
							//this.westPanel.repaint();
							this.currTurn = 0;

							//TODO: OptionPane.showMessageDialog(this.frame, "Congratulations! West Hand has won!",	"Information", 1);
							this.lblMsg.setText("Congratulations! West Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else
						{
							//this.westPanel.removeAll();
							arrayOfcard2 = this.westHand.getHand();

							//this.westPanel.setLayout(new Grid(arrayOfcard2.length, 1));
							this.btnWestPanel = new Button[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++)
							{
								this.westPanel.add(this.btnWestPanel[k] = new Button(arrayOfcard2[k].getSuit() + "-"
										+ arrayOfcard2[k].getValue()));
							}
							//this.westPanel.validate();
							this.currTurn = 0;
						}
					} else
					{
						if (this.currLeader == 0)
							this.lblMsg.setText("You call the shots now!");
						this.currTurn = 0;
					}
					freezeSouthHand(false);
				}
			case 0:
			}
		}
	}

	private void swapSouthPanel(Button paramJButton1, int paramInt1, Button paramJButton2, int paramInt2)
	{
		String str1 = paramJButton1.getText();
		int i = this.arySouthPlayPanelSelectedSuit[paramInt1];

		String str2 = paramJButton2.getText();
		int j = this.arySouthTempPanelSelectedSuit[paramInt2];

		if ((!str1.equals("  ")) || (!str2.equals("  ")))
		{
			if ((str1.equals("  ")) && (!str2.equals("  ")))
			{
				this.arySouthPlayPanelSelectedSuit[paramInt1] = this.arySouthTempPanelSelectedSuit[paramInt2];
				// paramJButton1.setIcon(paramJButton2.getIcon());
				paramJButton1.setText(paramJButton2.getText());
				// paramJButton2.setIcon(null);
				paramJButton2.setText("  ");
			} else if ((!str1.equals("  ")) && (str2.equals("  ")))
			{
				this.arySouthTempPanelSelectedSuit[paramInt2] = this.arySouthPlayPanelSelectedSuit[paramInt1];
				// paramJButton2.setIcon(paramJButton1.getIcon());
				paramJButton2.setText(paramJButton1.getText());
				// paramJButton1.setIcon(null);
				paramJButton1.setText("  ");
			} else
			{
				int k = this.arySouthPlayPanelSelectedSuit[paramInt1];
				// Button localIcon = paramJButton1.getIcon();

				this.arySouthPlayPanelSelectedSuit[paramInt1] = this.arySouthTempPanelSelectedSuit[paramInt2];
				this.arySouthTempPanelSelectedSuit[paramInt2] = k;

				// paramJButton1.setIcon(paramJButton2.getIcon());
				paramJButton1.setText(str2);

				// paramJButton2.setIcon(localIcon);
				paramJButton2.setText(str1);
			}
		}
		// paramJButton1.setBackground(DEFAULTCOLOR);
		// paramJButton2.setBackground(DEFAULTCOLOR);
		this.arySouthPlayPanelSelected[paramInt1] = 0;
		this.currSouthPlayPanelSelected = -1;
		this.currSouthTempPanelSelected = -1;
	}

	private void updateCenterPanel(String paramString)
	{
		// Component[] arrayOfComponent =
		// this.cntrHandPanel.getElement().getChildNodes();
		// for (int i = 1; i < arrayOfComponent.length; i++)
		// this.cntrTopPanel.add(arrayOfComponent[i]);
		// this.cntrTopPanel.validate();
		// this.cntrTopPanel.repaint();
		// this.cntrHandPanel.removeAll();
		this.cntrHandPanel.add(new Label("Current Hand (" + paramString + ")"));

		card[] arrayOfcard = this.currHand.getCards();
		for (int j = 0; j < arrayOfcard.length; j++)
		{
			int k = arrayOfcard[j].getSuit();
			int m = arrayOfcard[j].getValue();
			String str = "";
			Image localImageIcon1 = null;
			Image localImageIcon2 = null;
			Image localImageIcon3 = null;
			Image localImageIcon4 = null;
			switch (m)
			{
			case 11:
				str = "J";
				break;
			case 12:
				str = "Q";
				break;
			case 13:
				str = "K";
				break;
			case 1:
				str = "A";
				break;
			case 2:
				str = "2";
				break;
			case 3:
				str = "3";
				break;
			case 4:
				str = "4";
				break;
			case 5:
				str = "5";
				break;
			case 6:
				str = "6";
				break;
			case 7:
				str = "7";
				break;
			case 8:
				str = "8";
				break;
			case 9:
				str = "9";
				break;
			case 10:
				str = "10";
				break;
			}
			switch (k)
			{
			case 1:
				localImageIcon1 = new Image("./spade.gif");
				this.cntrHandPanel.add(new Label(str));
				break;
			case 2:
				localImageIcon2 = new Image("./heart.gif");
				this.cntrHandPanel.add(new Label(str));
				break;
			case 3:
				localImageIcon3 = new Image("./club.gif");
				this.cntrHandPanel.add(new Label(str));
				break;
			case 4:
				localImageIcon4 = new Image("./diamond.gif");
				this.cntrHandPanel.add(new Label(str));
			}
		}

		// this.cntrHandPanel.validate();
		// this.cntrHandPanel.repaint();

		switch (this.currHand.getCombination())
		{
		case 0:
			this.lblMsg.setText("Now playing ONE CARD!");
			break;
		case 1:
			this.lblMsg.setText("Now playing PAIR!");
			break;
		case 2:
			this.lblMsg.setText("Now playing TWO PAIRS!");
			break;
		case 3:
			this.lblMsg.setText("Now playing STRAIGHT!");
			break;
		case 4:
			this.lblMsg.setText("Now playing FLUSH!");
			break;
		case 5:
			this.lblMsg.setText("Now playing FULL HOUSE!");
			break;
		case 6:
			this.lblMsg.setText("Now playing FOUR OF A KIND!");
			break;
		case 7:
			this.lblMsg.setText("Now playing STRAIGHT FLUSH!");
			break;
		case 8:
			this.lblMsg.setText("Now playing ROYAL FLUSH!");
			break;
		}
	}
	

	class BigDHandler implements ClickHandler, KeyUpHandler {
		/**
		 * Fired when the user clicks on the sendButton.
		 */
		public void onClick(ClickEvent event) {
			sendNameToServer();
		}

		/**
		 * Fired when the user types in the nameField.
		 */
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				sendNameToServer();
			}
		}

		/**
		 * Send the name from the nameField to the server and wait for a response.
		 */
		private void sendNameToServer() {
			// First, we validate the input.
			/*
			errorLabel.setText("");
			String textToServer = nameField.getText();
			if (!FieldVerifier.isValidName(textToServer)) {
				errorLabel.setText("Please enter at least four characters");
				return;
			}

			// Then, we send the input to the server.
			sendButton.setEnabled(false);
			textToServerLabel.setText(textToServer);
			serverResponseLabel.setText("");
			greetingService.greetServer(textToServer,
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
							// Show the RPC error message to the user
							dialogBox
									.setText("Remote Procedure Call - Failure");
							serverResponseLabel
									.addStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(SERVER_ERROR);
							dialogBox.center();
							closeButton.setFocus(true);
						}

						public void onSuccess(String result) {
							dialogBox.setText("Remote Procedure Call");
							serverResponseLabel
									.removeStyleName("serverResponseLabelError");
							serverResponseLabel.setHTML(result);
							dialogBox.center();
							closeButton.setFocus(true);
						}
					});
		}
		*/
	}

	// Add a handler to send the name to the server
	//MyHandler handler = new MyHandler();
	//sendButton.addClickHandler(handler);
	//nameField.addKeyUpHandler(handler);
}
	
	
	
}