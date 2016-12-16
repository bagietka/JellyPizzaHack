import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Authors extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Authors() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
			
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(0, 0, 0, 0);
		contentPane.add(textArea);
		
		JLabel lblAutorzy = new JLabel("Autorzy");
		lblAutorzy.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorzy.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutorzy.setBounds(10, 5, 422, 23);
		contentPane.add(lblAutorzy);
		
		JLabel lblPiotrAntoniszyn = new JLabel("Piotr Antoniszyn");
		lblPiotrAntoniszyn.setBounds(15, 30, 158, 14);
		contentPane.add(lblPiotrAntoniszyn);
		
		JLabel lblPiotrGdowski = new JLabel("Piotr Gdowski");
		lblPiotrGdowski.setBounds(15, 66, 105, 14);
		contentPane.add(lblPiotrGdowski);
		
		JLabel lblAdamGuszkowski = new JLabel("Adam Guszkowski");
		lblAdamGuszkowski.setBounds(15, 106, 118, 14);
		contentPane.add(lblAdamGuszkowski);
		
		JLabel lblEdytaRogula = new JLabel("Edyta Rogula");
		lblEdytaRogula.setBounds(15, 148, 105, 14);
		contentPane.add(lblEdytaRogula);
		
		JButton btnOkFajnie = new JButton("OK, fajnie");
		btnOkFajnie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnOkFajnie.setBounds(179, 239, 91, 23);
		contentPane.add(btnOkFajnie);
		
		
		
		
	}
}
