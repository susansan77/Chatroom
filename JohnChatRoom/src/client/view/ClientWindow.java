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
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;


public class ClientWindow {
	/**client who uses this window*/
	private Client client;
	private JFrame frame;
	private JPanel inputPanel;
	private JTable table;
	private JTable connectionList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel tablePanel = new JPanel();
		
		JButton btnLeave = new JButton("Leave");
		btnLeave.setBounds(28, 517, 178, 29);
		JTextArea textArea = new JTextArea ("");
		textArea.setEditable(false);

		JScrollPane scroll = new JScrollPane (textArea, 
		   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		frame.getContentPane().add(scroll);
		frame.setVisible (true);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setSize(200, 300);
		
		
		inputPanel = new JPanel();
		
		JButton inputBtn = new JButton("Submit");
		inputBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tablePanel.setLayout(null);
		tablePanel.add(btnLeave);
		
		connectionList = new JTable();
		connectionList.setBounds(6, 476, 200, -454);
		tablePanel.add(connectionList);
		
		JTextArea textArea_1 = new JTextArea();
		GroupLayout gl_inputPanel = new GroupLayout(inputPanel);
		gl_inputPanel.setHorizontalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_inputPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(inputBtn, Alignment.TRAILING)
						.addComponent(textArea_1, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_inputPanel.setVerticalGroup(
			gl_inputPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_inputPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(inputBtn)
					.addGap(17))
		);
		inputPanel.setLayout(gl_inputPanel);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
							.addGap(18))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(45)
							.addComponent(scroll, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(inputPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE)))
					.addGap(12))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
