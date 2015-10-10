package com.willallen;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

class gui extends JFrame implements WindowListener, ActionListener {
	private hand northHand;
	private hand westHand;
	private hand eastHand;
	private hand southHand;
	private JLabel lblMsg;
	private JFrame frame;
	private JMenuBar mb;
	private JMenu[] menu;
	private JMenuItem[] itmFile;
	private JMenuItem[] itmPlay;
	private JMenuItem[] itmHelp;
	private JButton btnNew;
	private JButton btnPass;
	private JButton btnSend;
	private JButton btnExit;
	private JButton[] btnNorthPanel;
	private JButton[] btnWestPanel;
	private JButton[] btnEastPanel;
	private JButton[] btnSouthPlayPanel;
	private JButton[] btnSouthTempPanel;
	private JPanel northPanel;
	private JPanel westPanel;
	private JPanel eastPanel;
	private JPanel southPanel;
	private JPanel southPlayPanel;
	private JPanel southTempPanel;
	private JPanel cntrPanel;
	private JPanel cntrTopPanel;
	private JPanel cntrHandPanel;
	private JPanel cntrMsgPanel;
	private JPanel cntrBottomPanel;
	private int[] arySouthPlayPanelSelected = new int[13];
	private int[] arySouthTempPanelSelectedSuit = { -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1 };
	private int[] arySouthPlayPanelSelectedSuit = { -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1 };
	private int currSouthTempPanelSelected = -1;
	private int currSouthPlayPanelSelected = -1;
	private int currTurn;
	private int currLeader;
	private tableHand currHand;
	private static final Color YELLOW = Color.yellow;
	private static Color DEFAULTCOLOR;

	gui(String paramString) {
		initHand();
		initGUI(paramString);
		DEFAULTCOLOR = getBackground();
		initPlay();
	}

	public void actionPerformed(ActionEvent paramActionEvent) {
		Object localObject = paramActionEvent.getSource();
		String str;
		if ((localObject instanceof JButton)) {
			str = paramActionEvent.getActionCommand();

			if (str.equals("New Game")) {
				newGame();
			} else if (str.equals("Pass Turn")) {
				passTurn();
			} else if (str.equals("Send Up")) {
				sendUp();
			} else if (str.equals("Exit Game")) {
				exitGame();
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[0]) {
				setSouthTempPanelState(this.btnSouthTempPanel[0], 0);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[1]) {
				setSouthTempPanelState(this.btnSouthTempPanel[1], 1);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[2]) {
				setSouthTempPanelState(this.btnSouthTempPanel[2], 2);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[3]) {
				setSouthTempPanelState(this.btnSouthTempPanel[3], 3);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[4]) {
				setSouthTempPanelState(this.btnSouthTempPanel[4], 4);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[5]) {
				setSouthTempPanelState(this.btnSouthTempPanel[5], 5);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[6]) {
				setSouthTempPanelState(this.btnSouthTempPanel[6], 6);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[7]) {
				setSouthTempPanelState(this.btnSouthTempPanel[7], 7);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[8]) {
				setSouthTempPanelState(this.btnSouthTempPanel[8], 8);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[9]) {
				setSouthTempPanelState(this.btnSouthTempPanel[9], 9);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[10]) {
				setSouthTempPanelState(this.btnSouthTempPanel[10], 10);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[11]) {
				setSouthTempPanelState(this.btnSouthTempPanel[11], 11);
			} else if (paramActionEvent.getSource() == this.btnSouthTempPanel[12]) {
				setSouthTempPanelState(this.btnSouthTempPanel[12], 12);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[0]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[0], 0);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[1]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[1], 1);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[2]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[2], 2);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[3]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[3], 3);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[4]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[4], 4);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[5]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[5], 5);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[6]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[6], 6);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[7]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[7], 7);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[8]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[8], 8);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[9]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[9], 9);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[10]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[10], 10);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[11]) {
				setSouthPlayPanelState(this.btnSouthPlayPanel[11], 11);
			} else if (paramActionEvent.getSource() == this.btnSouthPlayPanel[12])
				setSouthPlayPanelState(this.btnSouthPlayPanel[12], 12);
		} else if ((localObject instanceof JMenuItem)) {
			str = ((JMenuItem) paramActionEvent.getSource()).getText();
			if (str.equals("Exit")) {
				exitGame();
			} else if (str.equals("New Game")) {
				newGame();
			} else if (str.equals("Pass Turn")) {
				passTurn();
			} else if (str.equals("Send Up")) {
				sendUp();
			} else if (str.equals("About")) {
				JOptionPane
						.showMessageDialog(
								this.frame,
								"<html><b><center>Chuadaidi Version 0.7!</center>Mail to sohguanh@hotmail.com for any feedbacks or complaints :)</b></html>",
								"Information", 1);
			}
		}
	}

	private void centerOnScreen(JFrame paramJFrame) {
		Dimension localDimension1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension localDimension2 = paramJFrame.getSize();
		int i = localDimension1.width / 2;
		int j = localDimension1.height / 2;
		paramJFrame.setLocation(i - localDimension2.width / 2, j
				- localDimension2.height / 2);
	}

	private void exitGame() {
		windowClosing(null);
	}

	private void freezeSouthHand(boolean paramBoolean) {
		for (int i = 0; i < this.btnSouthPlayPanel.length; i++)
			if (paramBoolean == true) {
				this.btnSouthPlayPanel[i].setEnabled(false);
				this.btnSouthTempPanel[i].setEnabled(false);
			} else {
				this.btnSouthPlayPanel[i].setEnabled(true);
				this.btnSouthTempPanel[i].setEnabled(true);
			}
	}

	private void initGUI(String paramString) {
		UIManager.getSystemLookAndFeelClassName();

		this.frame = new JFrame(paramString);

		setMenuBar();

		JPanel localJPanel = new JPanel();
		localJPanel.setBackground(getBackground());
		localJPanel.setForeground(getForeground());
		localJPanel.setFont(getFont());
		localJPanel.setLayout(new BorderLayout());

		card[] arrayOfcard = this.northHand.getHand();
		this.northPanel = new JPanel();
		this.northPanel.setLayout(new GridLayout(1, arrayOfcard.length));
		this.btnNorthPanel = new JButton[arrayOfcard.length];
		for (int i = 0; i < arrayOfcard.length; i++) {
			this.northPanel
					.add(this.btnNorthPanel[i] = new JButton(arrayOfcard[i]
							.getSuit() + "-" + arrayOfcard[i].getValue()));
		}
		this.northPanel.setPreferredSize(new Dimension(1000, 30));

		arrayOfcard = this.westHand.getHand();
		this.westPanel = new JPanel();
		this.westPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnWestPanel = new JButton[arrayOfcard.length];
		for (int j = 0; j < arrayOfcard.length; j++) {
			this.westPanel
					.add(this.btnWestPanel[j] = new JButton(arrayOfcard[j]
							.getSuit() + "-" + arrayOfcard[j].getValue()));
		}
		this.westPanel.setPreferredSize(new Dimension(80, 520));

		arrayOfcard = this.eastHand.getHand();
		this.eastPanel = new JPanel();
		this.eastPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnEastPanel = new JButton[arrayOfcard.length];
		for (int k = 0; k < arrayOfcard.length; k++)
			this.eastPanel
					.add(this.btnEastPanel[k] = new JButton(arrayOfcard[k]
							.getSuit() + "-" + arrayOfcard[k].getValue()));
		this.eastPanel.setPreferredSize(new Dimension(80, 520));

		arrayOfcard = this.southHand.getHand();
		this.southPanel = new JPanel();

		this.southPanel.setLayout(new GridLayout(2, 1));
		this.southPlayPanel = new JPanel();
		this.southPlayPanel.setBorder(BorderFactory
				.createTitledBorder("Cards To Rearrange OR For Send Up"));
		this.southTempPanel = new JPanel();
		this.southTempPanel.setBorder(BorderFactory
				.createTitledBorder("Cards On Hand"));

		this.btnSouthPlayPanel = new JButton[arrayOfcard.length];
		for (int m = 0; m < arrayOfcard.length; m++) {
			this.southPlayPanel.add(this.btnSouthPlayPanel[m] = new JButton(
					"  "));
			this.btnSouthPlayPanel[m].addActionListener(this);
		}
		this.southTempPanel.setLayout(new GridLayout(1, arrayOfcard.length));

		this.btnSouthTempPanel = new JButton[arrayOfcard.length];
		for (int n = 0; n < arrayOfcard.length; n++) {
			int i1 = arrayOfcard[n].getSuit();
			int i2 = arrayOfcard[n].getValue();
			String str = "";
			ImageIcon localImageIcon1 = null;
			ImageIcon localImageIcon2 = null;
			ImageIcon localImageIcon3 = null;
			ImageIcon localImageIcon4 = null;
			switch (i2) {
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
			switch (i1) {
			case 1:
				localImageIcon1 = new ImageIcon("./spade.gif");
				this.southTempPanel
						.add(this.btnSouthTempPanel[n] = new JButton(str,
								localImageIcon1));
				this.arySouthTempPanelSelectedSuit[n] = 1;
				break;
			case 2:
				localImageIcon2 = new ImageIcon("./heart.gif");
				this.southTempPanel
						.add(this.btnSouthTempPanel[n] = new JButton(str,
								localImageIcon2));
				this.arySouthTempPanelSelectedSuit[n] = 2;
				break;
			case 3:
				localImageIcon3 = new ImageIcon("./club.gif");
				this.southTempPanel
						.add(this.btnSouthTempPanel[n] = new JButton(str,
								localImageIcon3));
				this.arySouthTempPanelSelectedSuit[n] = 3;
				break;
			case 4:
				localImageIcon4 = new ImageIcon("./diamond.gif");
				this.southTempPanel
						.add(this.btnSouthTempPanel[n] = new JButton(str,
								localImageIcon4));
				this.arySouthTempPanelSelectedSuit[n] = 4;
				break;
			}
			this.btnSouthTempPanel[n].addActionListener(this);
		}
		this.southTempPanel.setPreferredSize(new Dimension(1000, 30));
		this.southPanel.add(this.southPlayPanel);
		this.southPanel.add(this.southTempPanel);

		this.cntrPanel = new JPanel();
		this.cntrPanel.setLayout(new BoxLayout(this.cntrPanel, 1));
		this.cntrTopPanel = new JPanel();
		this.cntrTopPanel.setPreferredSize(new Dimension(700, 150));
		this.cntrTopPanel.setLayout(new FlowLayout());

		this.cntrHandPanel = new JPanel();
		this.cntrHandPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrHandPanel.add(new JLabel("Current Hand"));

		this.cntrMsgPanel = new JPanel();
		this.cntrMsgPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrMsgPanel.add(this.lblMsg = new JLabel(""));

		this.cntrBottomPanel = new JPanel();
		this.cntrBottomPanel.setPreferredSize(new Dimension(700, 50));
		this.cntrBottomPanel.setLayout(new GridLayout(1, 5));
		this.cntrBottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5,
				5));
		this.cntrBottomPanel.add(this.btnNew = new JButton("New Game"));
		this.btnNew.addActionListener(this);
		this.cntrBottomPanel.add(this.btnPass = new JButton("Pass Turn"));
		this.btnPass.addActionListener(this);
		this.cntrBottomPanel.add(this.btnSend = new JButton("Send Up"));
		this.btnSend.addActionListener(this);
		this.cntrBottomPanel.add(this.btnExit = new JButton("Exit Game"));
		this.btnExit.addActionListener(this);

		this.cntrPanel.add(this.cntrTopPanel);
		this.cntrPanel.add(this.cntrHandPanel);
		this.cntrPanel.add(this.cntrMsgPanel);
		this.cntrPanel.add(this.cntrBottomPanel);

		localJPanel.add("North", this.northPanel);
		localJPanel.add("West", this.westPanel);
		localJPanel.add("East", this.eastPanel);
		localJPanel.add("South", this.southPanel);
		localJPanel.add("Center", this.cntrPanel);

		localJPanel.setPreferredSize(new Dimension(1000, 420));

		this.frame.getContentPane().setLayout(new BorderLayout());
		this.frame.getContentPane().add(localJPanel, "Center");
		this.frame.setSize(new Dimension(1000, 420));
		this.frame.addWindowListener(this);
		this.frame.pack();
		this.frame.setVisible(true);
		centerOnScreen(this.frame);
	}

	private void initHand() {
		deck localdeck = new deck();
		this.northHand = new hand(localdeck.getNextN(13));
		this.westHand = new hand(localdeck.getNextN(13));
		this.eastHand = new hand(localdeck.getNextN(13));
		this.southHand = new hand(localdeck.getNextN(13));
	}

	private void initPlay() {
		if (this.southHand.getLead() == true) {
			this.lblMsg.setText("You start play first!");
			this.currTurn = 0;
			this.currLeader = 0;
		}
		card[] arrayOfcard;
		int i;
		if (this.eastHand.getLead() == true) {
			this.lblMsg.setText("East start play first!");
			this.currTurn = 2;
			this.currLeader = 1;
			arrayOfcard = this.eastHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("East Hand");
			this.eastPanel.removeAll();
			arrayOfcard = this.eastHand.getHand();
			this.eastPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
			this.btnEastPanel = new JButton[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.eastPanel.add(this.btnEastPanel[i] = new JButton(
						arrayOfcard[i].getSuit() + "-"
								+ arrayOfcard[i].getValue()));
			this.eastPanel.validate();
		}
		if (this.northHand.getLead() == true) {
			this.lblMsg.setText("North start play first!");
			this.currTurn = 3;
			this.currLeader = 2;
			arrayOfcard = this.northHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("North Hand");
			this.northPanel.removeAll();
			arrayOfcard = this.northHand.getHand();
			this.northPanel.setLayout(new GridLayout(1, arrayOfcard.length));
			this.btnNorthPanel = new JButton[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.northPanel.add(this.btnNorthPanel[i] = new JButton(
						arrayOfcard[i].getSuit() + "-"
								+ arrayOfcard[i].getValue()));
			this.northPanel.validate();
		}
		if (this.westHand.getLead() == true) {
			this.lblMsg.setText("West start play first!");
			this.currTurn = 0;
			this.currLeader = 3;
			arrayOfcard = this.westHand.get3Diamond();

			this.currHand = new tableHand(arrayOfcard);
			updateCenterPanel("West Hand");
			this.westPanel.removeAll();
			arrayOfcard = this.westHand.getHand();
			this.westPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
			this.btnWestPanel = new JButton[arrayOfcard.length];
			for (i = 0; i < arrayOfcard.length; i++)
				this.westPanel.add(this.btnWestPanel[i] = new JButton(
						arrayOfcard[i].getSuit() + "-"
								+ arrayOfcard[i].getValue()));
			this.westPanel.validate();
		}
		startPlay();
	}

	private void newGame() {
		reinitGUI();
		initPlay();
	}

	private void passTurn() {
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;
		if (this.southHand.getLead() == true) {
			JOptionPane.showMessageDialog(this.frame,
					"You must select 3 of Diamond to start play!", "Error", 0);
		} else {
			freezeSouthHand(true);
			this.currTurn = 1;
			startPlay();
		}
	}

	private void reinitGUI() {
		int i = this.southHand.getHandSize();

		initHand();
		this.btnPass.setEnabled(true);
		this.btnSend.setEnabled(true);
		this.itmPlay[1].setEnabled(true);
		this.itmPlay[2].setEnabled(true);

		this.northPanel.removeAll();
		card[] arrayOfcard = this.northHand.getHand();
		this.northPanel.setLayout(new GridLayout(1, arrayOfcard.length));
		this.btnNorthPanel = new JButton[arrayOfcard.length];
		for (int j = 0; j < arrayOfcard.length; j++)
			this.northPanel
					.add(this.btnNorthPanel[j] = new JButton(arrayOfcard[j]
							.getSuit() + "-" + arrayOfcard[j].getValue()));
		this.northPanel.validate();

		this.westPanel.removeAll();
		arrayOfcard = this.westHand.getHand();
		this.westPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnWestPanel = new JButton[arrayOfcard.length];
		for (int k = 0; k < arrayOfcard.length; k++)
			this.westPanel
					.add(this.btnWestPanel[k] = new JButton(arrayOfcard[k]
							.getSuit() + "-" + arrayOfcard[k].getValue()));
		this.westPanel.validate();

		this.eastPanel.removeAll();
		arrayOfcard = this.eastHand.getHand();
		this.eastPanel.setLayout(new GridLayout(arrayOfcard.length, 1));
		this.btnEastPanel = new JButton[arrayOfcard.length];
		for (int m = 0; m < arrayOfcard.length; m++)
			this.eastPanel
					.add(this.btnEastPanel[m] = new JButton(arrayOfcard[m]
							.getSuit() + "-" + arrayOfcard[m].getValue()));
		this.eastPanel.validate();

		for (int n = 0; n < this.btnSouthTempPanel.length; n++) {
			this.btnSouthTempPanel[n].setIcon(null);
			this.btnSouthTempPanel[n].setText("  ");
			this.btnSouthTempPanel[n].setBackground(DEFAULTCOLOR);
		}
		for (int i1 = 0; i1 < this.btnSouthPlayPanel.length; i1++) {
			this.btnSouthPlayPanel[i1].setIcon(null);
			this.btnSouthPlayPanel[i1].setText("  ");
			this.btnSouthPlayPanel[i1].setBackground(DEFAULTCOLOR);
			this.arySouthPlayPanelSelected[i1] = 0;
		}

		freezeSouthHand(false);
		arrayOfcard = this.southHand.getHand();
		for (int i2 = 0; i2 < arrayOfcard.length; i2++) {
			int i3 = arrayOfcard[i2].getSuit();
			int i4 = arrayOfcard[i2].getValue();
			String str = "";
			ImageIcon localImageIcon1 = null;
			ImageIcon localImageIcon2 = null;
			ImageIcon localImageIcon3 = null;
			ImageIcon localImageIcon4 = null;
			switch (i4) {
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

			switch (i3) {
			case 1:
				localImageIcon1 = new ImageIcon("./spade.gif");
				this.btnSouthTempPanel[i2].setIcon(localImageIcon1);
				this.arySouthTempPanelSelectedSuit[i2] = 1;
				break;
			case 2:
				localImageIcon2 = new ImageIcon("./heart.gif");
				this.btnSouthTempPanel[i2].setIcon(localImageIcon2);
				this.arySouthTempPanelSelectedSuit[i2] = 2;
				break;
			case 3:
				localImageIcon3 = new ImageIcon("./club.gif");
				this.btnSouthTempPanel[i2].setIcon(localImageIcon3);
				this.arySouthTempPanelSelectedSuit[i2] = 3;
				break;
			case 4:
				localImageIcon4 = new ImageIcon("./diamond.gif");
				this.btnSouthTempPanel[i2].setIcon(localImageIcon4);
				this.arySouthTempPanelSelectedSuit[i2] = 4;
			}
		}

		this.southPanel.validate();
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;

		this.cntrTopPanel.removeAll();
		this.cntrHandPanel.removeAll();
		this.cntrHandPanel.add(new JLabel("Current Hand"));
		this.cntrTopPanel.validate();
		this.cntrHandPanel.validate();
		this.cntrHandPanel.repaint();
		this.cntrTopPanel.repaint();
	}

	private void sendUp() {
		int i = this.southHand.getHandSize();
		Vector localVector = new Vector();
		boolean[] arrayOfBoolean = new boolean[1];
		int j = 1;
		for (int k = 0; k < 13; k++) {
			if (this.arySouthPlayPanelSelected[k] == 1)
				if (!this.btnSouthPlayPanel[k].getText().equals("  ")) {
					localVector.addElement(new Integer(k));
				} else {
					j = 0;
					break;
				}
		}
		this.currSouthTempPanelSelected = -1;
		this.currSouthPlayPanelSelected = -1;

		if ((localVector.size() == 0) || (j == 0)) {
			JOptionPane.showMessageDialog(this.frame,
					"Error in your selection!", "Error", 0);
			return;
		}
		card[] arrayOfcard1 = null;
		int n;
		if (localVector.size() != 0) {
			arrayOfcard1 = new card[localVector.size()];
			for (int m = 0; m < localVector.size(); m++) {
				n = 0;
				String str = this.btnSouthPlayPanel[((Integer) localVector
						.elementAt(m)).intValue()].getText();
				if (str.equals("A"))
					n = 1;
				else if (str.equals("J"))
					n = 11;
				else if (str.equals("Q"))
					n = 12;
				else if (str.equals("K"))
					n = 13;
				else {
					n = new Integer(str).intValue();
				}
				arrayOfcard1[m] = new card(
						this.arySouthPlayPanelSelectedSuit[((Integer) localVector
								.elementAt(m)).intValue()], n);
			}
		}
		card[] arrayOfcard2;
		if (this.currLeader == 0) {
			if (this.southHand.getLead() == true)
				arrayOfcard2 = this.southHand.playerSelected3DiamondCard(
						arrayOfcard1, arrayOfBoolean);
			else
				arrayOfcard2 = this.southHand.playerSelectedCard(arrayOfcard1,
						arrayOfBoolean);
		} else
			arrayOfcard2 = this.southHand.playerSelectedCard(arrayOfcard1,
					arrayOfBoolean, this.currHand);
		if (arrayOfcard2 != null) {
			this.southHand.setLead(false);
			this.currLeader = 0;
			this.currHand = new tableHand(arrayOfcard2);
			updateCenterPanel("You");

			for (n = 0; n < 13; n++)
				if (this.arySouthPlayPanelSelected[n] == 1) {
					this.arySouthPlayPanelSelected[n] = 0;
					this.btnSouthPlayPanel[n].setBackground(DEFAULTCOLOR);
					this.btnSouthPlayPanel[n].setIcon(null);
					this.btnSouthPlayPanel[n].setText("  ");
				}
			if (this.southHand.getHandSize() == 0) {
				JOptionPane.showMessageDialog(this.frame,
						"Congratulations! You have won!", "Information", 1);
				this.lblMsg.setText("Congratulations! You have won!");
				this.btnPass.setEnabled(false);
				this.btnSend.setEnabled(false);
				this.itmPlay[1].setEnabled(false);
				this.itmPlay[2].setEnabled(false);
			} else {
				this.currTurn = 1;
				startPlay();
			}
		} else {
			JOptionPane.showMessageDialog(this.frame,
					"Error in your selection!", "Error", 0);
		}
	}

	private void setMenuBar() {
		this.mb = new JMenuBar();

		this.menu = new JMenu[3];
		this.menu[0] = new JMenu("File");
		this.menu[0].setMnemonic(70);
		this.menu[1] = new JMenu("Play");
		this.menu[1].setMnemonic(80);
		this.menu[2] = new JMenu("Help");
		this.menu[2].setMnemonic(72);

		this.itmFile = new JMenuItem[1];
		this.itmFile[0] = new JMenuItem("Exit");
		this.itmFile[0].setMnemonic(88);
		for (int i = 0; i < this.itmFile.length; i++) {
			this.itmFile[i].addActionListener(this);
			this.menu[0].add(this.itmFile[i]);
		}

		this.itmPlay = new JMenuItem[3];
		this.itmPlay[0] = new JMenuItem("New Game");
		this.itmPlay[0].setMnemonic(71);
		this.itmPlay[1] = new JMenuItem("Pass Turn");
		this.itmPlay[1].setMnemonic(84);
		this.itmPlay[2] = new JMenuItem("Send Up");
		this.itmPlay[2].setMnemonic(85);
		for (int j = 0; j < this.itmPlay.length; j++) {
			this.itmPlay[j].addActionListener(this);
			this.menu[1].add(this.itmPlay[j]);
		}

		this.itmHelp = new JMenuItem[1];
		this.itmHelp[0] = new JMenuItem("About");
		this.itmHelp[0].setMnemonic(65);
		for (int k = 0; k < this.itmHelp.length; k++) {
			this.itmHelp[k].addActionListener(this);
			this.menu[2].add(this.itmHelp[k]);
		}

		for (int m = 0; m < this.menu.length; m++)
			this.mb.add(this.menu[m]);
		this.frame.setJMenuBar(this.mb);
	}

	private void setSouthPlayPanelState(JButton paramJButton, int paramInt) {
		this.currSouthPlayPanelSelected = paramInt;
		if (this.currSouthTempPanelSelected != -1) {
			swapSouthPanel(paramJButton, paramInt,
					this.btnSouthTempPanel[this.currSouthTempPanelSelected],
					this.currSouthTempPanelSelected);
		} else if (paramJButton.getBackground() == YELLOW) {
			paramJButton.setBackground(DEFAULTCOLOR);
			this.arySouthPlayPanelSelected[paramInt] = 0;
		} else {
			paramJButton.setBackground(YELLOW);
			this.arySouthPlayPanelSelected[paramInt] = 1;
		}
	}

	private void setSouthTempPanelState(JButton paramJButton, int paramInt) {
		this.currSouthTempPanelSelected = paramInt;
		if (this.currSouthPlayPanelSelected != -1) {
			swapSouthPanel(
					this.btnSouthPlayPanel[this.currSouthPlayPanelSelected],
					this.currSouthPlayPanelSelected, paramJButton, paramInt);
		} else {
			paramJButton.setBackground(YELLOW);
			for (int i = 0; i < 13; i++) {
				if (i == paramInt)
					continue;
				this.btnSouthTempPanel[i].setBackground(DEFAULTCOLOR);
			}
		}
	}

	private void startPlay() {
		while (this.currTurn != 0) {
			boolean[] arrayOfBoolean = new boolean[1];
			int i = 1;

			int j = this.currHand.getNoCard();
			boolean bool;
			card[] arrayOfcard1;
			card[] arrayOfcard2;
			int k;
			switch (this.currTurn) {
			case 1:
				if ((this.currLeader != 0) && (this.currLeader != 1)) {
					this.currTurn = 2;
				} else {
					if (this.southHand.getHandSize() == 1) {
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2) {
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5) {
						bool = true;
						i = 2;
					} else {
						bool = false;
						i = 5;
					}

					if (this.currLeader == 1)
						arrayOfcard1 = this.eastHand.computerSelectedCard(
								arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.eastHand.computerSelectedCard(
								arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null) {
						this.currLeader = 1;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("East Hand");

						if (this.eastHand.getHandSize() == 0) {
							this.eastPanel.removeAll();
							this.eastPanel.validate();
							this.eastPanel.repaint();
							this.currTurn = 0;

							JOptionPane.showMessageDialog(this.frame,
									"Congratulations! East Hand has won!",
									"Information", 1);
							this.lblMsg
									.setText("Congratulations! East Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else {
							this.eastPanel.removeAll();
							arrayOfcard2 = this.eastHand.getHand();

							this.eastPanel.setLayout(new GridLayout(
									arrayOfcard2.length, 1));
							this.btnEastPanel = new JButton[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++) {
								this.eastPanel
										.add(this.btnEastPanel[k] = new JButton(
												arrayOfcard2[k].getSuit()
														+ "-"
														+ arrayOfcard2[k]
																.getValue()));
							}
							this.eastPanel.validate();
							this.currTurn = 2;
						}
					} else {
						this.currTurn = 2;
					}
				}
				break;
			case 2:
				if ((this.currLeader != 0) && (this.currLeader != 2)) {
					this.currTurn = 3;
				} else {
					if (this.southHand.getHandSize() == 1) {
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2) {
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5) {
						bool = true;
						i = 2;
					} else {
						bool = false;
						i = 5;
					}

					if (this.currLeader == 2)
						arrayOfcard1 = this.northHand.computerSelectedCard(
								arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.northHand.computerSelectedCard(
								arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null) {
						this.currLeader = 2;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("North Hand");

						if (this.northHand.getHandSize() == 0) {
							this.northPanel.removeAll();
							this.northPanel.validate();
							this.northPanel.repaint();
							this.currTurn = 0;

							JOptionPane.showMessageDialog(this.frame,
									"Congratulations! North Hand has won!",
									"Information", 1);
							this.lblMsg
									.setText("Congratulations! North Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else {
							this.northPanel.removeAll();
							arrayOfcard2 = this.northHand.getHand();

							this.northPanel.setLayout(new GridLayout(1,
									arrayOfcard2.length));
							this.btnNorthPanel = new JButton[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++) {
								this.northPanel
										.add(this.btnNorthPanel[k] = new JButton(
												arrayOfcard2[k].getSuit()
														+ "-"
														+ arrayOfcard2[k]
																.getValue()));
							}
							this.northPanel.validate();
							this.currTurn = 3;
						}
					} else {
						this.currTurn = 3;
					}
				}
				break;
			case 3:
				if ((this.currLeader != 0) && (this.currLeader != 3)) {
					freezeSouthHand(false);
					this.currTurn = 0;
				} else {
					if (this.southHand.getHandSize() == 1) {
						bool = true;
						i = 5;
					} else if (this.southHand.getHandSize() == 2) {
						bool = true;
						i = 1;
					} else if (this.southHand.getHandSize() == 5) {
						bool = true;
						i = 2;
					} else {
						bool = false;
						i = 5;
					}
					if (this.currLeader == 3)
						arrayOfcard1 = this.westHand.computerSelectedCard(
								arrayOfBoolean, bool, i);
					else
						arrayOfcard1 = this.westHand.computerSelectedCard(
								arrayOfBoolean, bool, this.currHand);
					if (arrayOfcard1 != null) {
						this.currLeader = 3;
						this.currHand = new tableHand(arrayOfcard1);
						updateCenterPanel("West Hand");

						if (this.westHand.getHandSize() == 0) {
							this.westPanel.removeAll();
							this.westPanel.validate();
							this.westPanel.repaint();
							this.currTurn = 0;

							JOptionPane.showMessageDialog(this.frame,
									"Congratulations! West Hand has won!",
									"Information", 1);
							this.lblMsg
									.setText("Congratulations! West Hand has won!");
							this.btnPass.setEnabled(false);
							this.btnSend.setEnabled(false);
							this.itmPlay[1].setEnabled(false);
							this.itmPlay[2].setEnabled(false);
							this.currTurn = 0;
						} else {
							this.westPanel.removeAll();
							arrayOfcard2 = this.westHand.getHand();

							this.westPanel.setLayout(new GridLayout(
									arrayOfcard2.length, 1));
							this.btnWestPanel = new JButton[arrayOfcard2.length];
							for (k = 0; k < arrayOfcard2.length; k++) {
								this.westPanel
										.add(this.btnWestPanel[k] = new JButton(
												arrayOfcard2[k].getSuit()
														+ "-"
														+ arrayOfcard2[k]
																.getValue()));
							}
							this.westPanel.validate();
							this.currTurn = 0;
						}
					} else {
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

	private void swapSouthPanel(JButton paramJButton1, int paramInt1,
			JButton paramJButton2, int paramInt2) {
		String str1 = paramJButton1.getText();
		int i = this.arySouthPlayPanelSelectedSuit[paramInt1];

		String str2 = paramJButton2.getText();
		int j = this.arySouthTempPanelSelectedSuit[paramInt2];

		if ((!str1.equals("  ")) || (!str2.equals("  "))) {
			if ((str1.equals("  ")) && (!str2.equals("  "))) {
				this.arySouthPlayPanelSelectedSuit[paramInt1] = this.arySouthTempPanelSelectedSuit[paramInt2];
				paramJButton1.setIcon(paramJButton2.getIcon());
				paramJButton1.setText(paramJButton2.getText());
				paramJButton2.setIcon(null);
				paramJButton2.setText("  ");
			} else if ((!str1.equals("  ")) && (str2.equals("  "))) {
				this.arySouthTempPanelSelectedSuit[paramInt2] = this.arySouthPlayPanelSelectedSuit[paramInt1];
				paramJButton2.setIcon(paramJButton1.getIcon());
				paramJButton2.setText(paramJButton1.getText());
				paramJButton1.setIcon(null);
				paramJButton1.setText("  ");
			} else {
				int k = this.arySouthPlayPanelSelectedSuit[paramInt1];
				Icon localIcon = paramJButton1.getIcon();

				this.arySouthPlayPanelSelectedSuit[paramInt1] = this.arySouthTempPanelSelectedSuit[paramInt2];
				this.arySouthTempPanelSelectedSuit[paramInt2] = k;

				paramJButton1.setIcon(paramJButton2.getIcon());
				paramJButton1.setText(str2);

				paramJButton2.setIcon(localIcon);
				paramJButton2.setText(str1);
			}
		}
		paramJButton1.setBackground(DEFAULTCOLOR);
		paramJButton2.setBackground(DEFAULTCOLOR);
		this.arySouthPlayPanelSelected[paramInt1] = 0;
		this.currSouthPlayPanelSelected = -1;
		this.currSouthTempPanelSelected = -1;
	}

	private void updateCenterPanel(String paramString) {
		Component[] arrayOfComponent = this.cntrHandPanel.getComponents();
		for (int i = 1; i < arrayOfComponent.length; i++)
			this.cntrTopPanel.add(arrayOfComponent[i]);
		this.cntrTopPanel.validate();
		this.cntrTopPanel.repaint();
		this.cntrHandPanel.removeAll();
		this.cntrHandPanel
				.add(new JLabel("Current Hand (" + paramString + ")"));

		card[] arrayOfcard = this.currHand.getCards();
		for (int j = 0; j < arrayOfcard.length; j++) {
			int k = arrayOfcard[j].getSuit();
			int m = arrayOfcard[j].getValue();
			String str = "";
			ImageIcon localImageIcon1 = null;
			ImageIcon localImageIcon2 = null;
			ImageIcon localImageIcon3 = null;
			ImageIcon localImageIcon4 = null;
			switch (m) {
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
			switch (k) {
			case 1:
				localImageIcon1 = new ImageIcon("./spade.gif");
				this.cntrHandPanel.add(new JLabel(str, localImageIcon1, 0));
				break;
			case 2:
				localImageIcon2 = new ImageIcon("./heart.gif");
				this.cntrHandPanel.add(new JLabel(str, localImageIcon2, 0));
				break;
			case 3:
				localImageIcon3 = new ImageIcon("./club.gif");
				this.cntrHandPanel.add(new JLabel(str, localImageIcon3, 0));
				break;
			case 4:
				localImageIcon4 = new ImageIcon("./diamond.gif");
				this.cntrHandPanel.add(new JLabel(str, localImageIcon4, 0));
			}
		}

		this.cntrHandPanel.validate();
		this.cntrHandPanel.repaint();

		switch (this.currHand.getCombination()) {
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

	public void windowActivated(WindowEvent paramWindowEvent) {
	}

	public void windowClosed(WindowEvent paramWindowEvent) {
	}

	public void windowClosing(WindowEvent paramWindowEvent) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent paramWindowEvent) {
	}

	public void windowDeiconified(WindowEvent paramWindowEvent) {
	}

	public void windowIconified(WindowEvent paramWindowEvent) {
	}

	public void windowOpened(WindowEvent paramWindowEvent) {
	}
}