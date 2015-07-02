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
	private JTextField portText;

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
				if(textField.getText().equals("")||portText.getText().equals("")){
					JOptionPane.showMessageDialog(getFrame(),
							"Sorry,but your have to input your nickname and port number");
					return;
				}
				String nickname = textField.getText();
				int port=Integer.parseInt(portText.getText());
				
				 try {
					 
					ConnectController connectCtr = new ConnectController(nickname,
					port, getLogWindow());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					 JOptionPane.showMessageDialog(window,
								"Sorry, but your login request is refused. Is the port number correct?");
					 return;
				}
				run();
				
			}

			
		});
		
		portText = new JTextField();
		portText.setColumns(10);
		
		JLabel lblAtPort = new JLabel("port:");

		GroupLayout groupLayout = new GroupLayout(getFrame().getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(16)
							.addComponent(lblWelcomeTo))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(99)
							.addComponent(btnJoinChatting)))
					.addContainerGap(111, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(54)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectYourNickname, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(portText, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAtPort))
					.addGap(44))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(lblWelcomeTo)
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectYourNickname)
						.addComponent(lblAtPort))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(portText, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnJoinChatting)
					.addContainerGap(26, Short.MAX_VALUE))
		);
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
				else if (str.equals("Login OK!")) {
					window = new ClientWindow();
					window.setClient(client);
					window.setSocket(socket, outS, inS);
					getFrame().setVisible(false);

					break;
				}
				else{
					JOptionPane.showMessageDialog(getFrame(),
							"Sorry, but the port is not serving");
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

	public void setClient(String nickname) {
		// TODO Auto-generated method stub
		this.client=nickname;
	}
}
