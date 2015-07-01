package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


import controller.ConnectController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LoginWindow extends JFrame implements Runnable {

	// private JFrame getFrame();
	private JTextField textField;
	ClientWindow window;
	String client;
	Socket socket;
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	PrintWriter outS;
	public void setOutS(PrintWriter outS) {
		this.outS = outS;
	}
	BufferedReader inS;

	public void setInS(BufferedReader inS) {
		this.inS = inS;
	}

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// LoginWindow window = new LoginWindow();
	// window.getFrame().setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	/***
	 * 
	 * @return this frame
	 */
	public JFrame getFrame() {
		return this;
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		getFrame().setBounds(100, 100, 350, 200);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblSelectYourNickname = new JLabel("Select your nickname:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblWelcomeTo = new JLabel("Welcome to  32 John Chatroom!");

		JButton btnJoinChatting = new JButton("Join chatting....");
		getFrame().setVisible(true);
		btnJoinChatting.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String nickname = textField.getText();
				
				ConnectController connectCtr = new ConnectController(nickname,
						3000, getLogWindow());
				run();
				
			}

			
		});

		GroupLayout groupLayout = new GroupLayout(getFrame().getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
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
																		.addGap(16)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblSelectYourNickname,
																								GroupLayout.PREFERRED_SIZE,
																								261,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblWelcomeTo)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(54)
																		.addComponent(
																				textField,
																				GroupLayout.PREFERRED_SIZE,
																				236,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(99)
																		.addComponent(
																				btnJoinChatting)))
										.addContainerGap(60, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(21)
						.addComponent(lblWelcomeTo)
						.addGap(18)
						.addComponent(lblSelectYourNickname)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(btnJoinChatting)
						.addContainerGap(26, Short.MAX_VALUE)));
		getFrame().getContentPane().setLayout(groupLayout);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			while (true) {
				String str = this.inS.readLine();
				if (str.equals("Server Message: Duplicate User")) {
					this.socket.close();
					warnAndRetry();

					break;
				}
				if (str.equals("Login OK!")) {
					window = new ClientWindow();
					window.client = client;
					window.setSocket(socket, outS, inS);
					getFrame().setVisible(false);

					break;
				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void warnAndRetry() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(getFrame(),
				"Sorry,but your nickname is used, please try another...");
		this.textField.setText("");

	}
	private LoginWindow getLogWindow() {
		// TODO Auto-generated method stub
		return this;
	}
}
