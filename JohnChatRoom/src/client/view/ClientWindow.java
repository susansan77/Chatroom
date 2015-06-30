package view;




import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;

import java.awt.ScrollPane;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import controller.BroadcastMsgController;
import controller.ProcessMsgController;
import model.Client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientWindow extends JFrame implements Runnable {
	/** client who uses this window */
	Client client;
	public Client getClient() {
		return client;
	}

	Socket socket;
	/** Means of communication to/from broker. */

	PrintWriter outS;
	BufferedReader inS;
	private JFrame frmWelcomeToJohn;
	private JPanel inputPanel;
	private JTable table;
	private JTable connectionList;
	private JTextArea inputTextArea;
	private JTextArea displayTextArea;

	public JTextArea getDisplayTextArea() {
		return displayTextArea;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frmWelcomeToJohn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeToJohn = new JFrame();
		frmWelcomeToJohn.setTitle("Welcome to John 32 Chatroom");
		frmWelcomeToJohn.setBounds(100, 100, 650, 650);
		frmWelcomeToJohn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel tablePanel = new JPanel();

		JButton btnLeave = new JButton("Leave");
		btnLeave.setBounds(28, 517, 178, 29);

		displayTextArea = new JTextArea("");
		displayTextArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(displayTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		frmWelcomeToJohn.getContentPane().add(scroll);
		frmWelcomeToJohn.setVisible(true);

		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null,
				null));
		table.setSize(200, 300);

		inputPanel = new JPanel();

		JButton inputBtn = new JButton("Submit");
		inputBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = inputTextArea.getText();
				BroadcastMsgController broadctl = new BroadcastMsgController();
				broadctl.processMSG(str);
				outS.println(str);
			}
		});

		tablePanel.setLayout(null);
		tablePanel.add(btnLeave);

		connectionList = new JTable();
		connectionList.setBounds(6, 476, 200, -454);
		tablePanel.add(connectionList);

		inputTextArea = new JTextArea();

		JLabel lblInputYourMessage = new JLabel("Input your message here");
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel
				.setHorizontalGroup(gl_inputPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_inputPanel
										.createSequentialGroup()
										.addGroup(
												gl_inputPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																Alignment.TRAILING,
																gl_inputPanel
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				gl_inputPanel
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								inputTextArea,
																								Alignment.LEADING,
																								GroupLayout.DEFAULT_SIZE,
																								388,
																								Short.MAX_VALUE)
																						.addComponent(
																								inputBtn)))
														.addGroup(
																gl_inputPanel
																		.createSequentialGroup()
																		.addGap(23)
																		.addComponent(
																				lblInputYourMessage)))
										.addContainerGap()));
		gl_inputPanel.setVerticalGroup(gl_inputPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_inputPanel
						.createSequentialGroup()
						.addGap(26)
						.addComponent(lblInputYourMessage)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(inputTextArea,
								GroupLayout.PREFERRED_SIZE, 92,
								GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(inputBtn).addGap(17)));
		inputPanel.setLayout(gl_inputPanel);
		GroupLayout groupLayout = new GroupLayout(
				frmWelcomeToJohn.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(12)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				scroll,
																				GroupLayout.PREFERRED_SIZE,
																				386,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				inputPanel,
																				GroupLayout.PREFERRED_SIZE,
																				400,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)))
										.addComponent(tablePanel,
												GroupLayout.PREFERRED_SIZE,
												220, GroupLayout.PREFERRED_SIZE)
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(45)
																		.addComponent(
																				scroll,
																				GroupLayout.PREFERRED_SIZE,
																				343,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				inputPanel,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(27)
																		.addComponent(
																				tablePanel,
																				GroupLayout.PREFERRED_SIZE,
																				548,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(12)));
		frmWelcomeToJohn.getContentPane().setLayout(groupLayout);
	}

	public void setSocket(Socket socket, PrintWriter o, BufferedReader i)
			throws IOException {
		this.socket = socket;
		this.outS = o;
		this.inS = i;
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String str = this.inS.readLine();
				ProcessMsgController ctr=new ProcessMsgController(this,str);
				ctr.process();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTable(ArrayList<String> list) {
		// TODO Auto-generated method stub
		
	}

	
}

