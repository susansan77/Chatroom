package view;

import java.awt.Container;
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
import javax.swing.table.DefaultTableModel;
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
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import controller.BroadcastMsgController;
import controller.ProcessMsgController;
import controller.WhisperController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientWindow extends JFrame implements Runnable {
	/** client who uses this window */
	String client;

	public String getClient() {
		return client;
	}

	Socket socket;
	/** Means of communication to/from broker. */
	String talkTo = "everyone";
	PrintWriter outS;
	BufferedReader inS;
	private JFrame frmWelcomeToJohn;
	private JPanel inputPanel;
	private JTextArea inputTextArea;
	private JTextArea displayTextArea;
	public JTable connectionList;
	private JScrollPane scrollPane;
	private JTable table;
	JLabel talkToLabel;

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
		frmWelcomeToJohn.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				leave();
			}
		});
		frmWelcomeToJohn.setTitle("Welcome to John 32 Chatroom");
		frmWelcomeToJohn.setBounds(100, 100, 650, 550);
		frmWelcomeToJohn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		displayTextArea = new JTextArea("");
		displayTextArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(displayTextArea,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// frmWelcomeToJohn.getContentPane().add(tableScroll);
		frmWelcomeToJohn.setVisible(true);

		inputPanel = new JPanel();

		JButton inputBtn = new JButton("Submit");
		inputBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = inputTextArea.getText();
				if (talkTo.equals("everyone")) {
					BroadcastMsgController broadctl = new BroadcastMsgController(
							getClientWindow(), outS, str);
					broadctl.process();
					
				} else {
					WhisperController whisperctl=new WhisperController(getClientWindow(), outS, str, talkTo);
					whisperctl.process();
					
				}
				inputTextArea.setText("");
			}
		});

		inputTextArea = new JTextArea();

		talkToLabel = new JLabel("Talk to " + talkTo);
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel
				.setHorizontalGroup(gl_inputPanel
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_inputPanel
										.createSequentialGroup()
										.addContainerGap(22, Short.MAX_VALUE)
										.addGroup(
												gl_inputPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																talkToLabel)
														.addComponent(inputBtn)
														.addComponent(
																inputTextArea,
																GroupLayout.PREFERRED_SIZE,
																355,
																GroupLayout.PREFERRED_SIZE))
										.addGap(23)));
		gl_inputPanel.setVerticalGroup(gl_inputPanel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_inputPanel
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(talkToLabel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(inputTextArea,
								GroupLayout.PREFERRED_SIZE, 57,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(inputBtn).addGap(90)));
		inputPanel.setLayout(gl_inputPanel);

		JButton btnLeave = new JButton("Leave");
		btnLeave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				leave();
			}
		});

		scrollPane = new JScrollPane();

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());// get mouse-selected
															// row
				// String who=table.get;
				int selectedRowIndex = table.getSelectedRow();
				int selectedColumnIndex = table.getSelectedColumn();
				String who = (String) table.getModel().getValueAt(
						selectedRowIndex, selectedColumnIndex);
				talkTo = who;
				getTalkToLabel().setText("Talk to " + who);

			}
		});
		scrollPane.setViewportView(table);
		GroupLayout groupLayout = new GroupLayout(
				frmWelcomeToJohn.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(29)
								.addComponent(scroll,
										GroupLayout.PREFERRED_SIZE, 363,
										GroupLayout.PREFERRED_SIZE)
								.addGap(48)
								.addComponent(scrollPane,
										GroupLayout.PREFERRED_SIZE, 183,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(10)
								.addComponent(inputPanel,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(35)
								.addComponent(btnLeave,
										GroupLayout.PREFERRED_SIZE, 178,
										GroupLayout.PREFERRED_SIZE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(55)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scroll,
																GroupLayout.PREFERRED_SIZE,
																316,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																250,
																GroupLayout.PREFERRED_SIZE))
										.addGap(12)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																inputPanel,
																GroupLayout.PREFERRED_SIZE,
																126,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(2)
																		.addComponent(
																				btnLeave,
																				GroupLayout.PREFERRED_SIZE,
																				35,
																				GroupLayout.PREFERRED_SIZE)))));
		frmWelcomeToJohn.getContentPane().setLayout(groupLayout);
	}

	protected ClientWindow getClientWindow() {
		// TODO Auto-generated method stub
		return this;
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
				ProcessMsgController ctr = new ProcessMsgController(this, str);
				ctr.process();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTable(ArrayList<String> list) {
		// TODO Auto-generated method stub

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn("chat with");
		Vector row = new Vector();
		row.add("everyone");
		tableModel.addRow(row);
		for (String s : list) {
			if (s.equals(this.client)) {
				continue;
			}
			row = new Vector();
			row.add(new String(s));
			tableModel.addRow(row);
		}
		table.setModel(tableModel);

	}

	public void setClient(String client2) {
		// TODO Auto-generated method stub
		client = new String(client2);
	}

	public JLabel getTalkToLabel() {
		return this.talkToLabel;
	}
	public void leave(){
		System.out.println("I am leaving");
	}
}
