import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	public Panel p;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		//this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		contentPane = new JPanel();
		contentPane.setBackground(new Color(169, 169, 169));
		contentPane.setSize( Toolkit.getDefaultToolkit().getScreenSize());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		p = new Panel();
		p.setBackground(new Color(250, 250, 210));
		p.setLayout(new GridBagLayout());
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		
		JButton btnNewButton = new JButton("Wersja prosta");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.clear(p.getGraphics());
				classicaco.start();
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Wersja z³o¿ona");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.clear(p.getGraphics());
				asrankaco.start();			
			}
		});
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_4 = new JButton("Problem komiwoja¿era");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.clear(p.getGraphics());
				acofortsp.start();
			}
		});
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_3 = new JButton("Wyczyœæ");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				p.clear(p.getGraphics());
			}
		});
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("Autorzy");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Authors a = new Authors();
				a.setVisible(true);
			}
		});
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_5 = new JButton("Niespodzianka!");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setChoice(2, p.getGraphics());
			}
		});
		panel.add(btnNewButton_5);
		

		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		panel_1.add(p);
	}

}