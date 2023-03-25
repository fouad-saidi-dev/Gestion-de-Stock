import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;


import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Supprim extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField textField_lib;
	Connection con =null;
	ResultSet rs =null;
	PreparedStatement requette=null;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Supprim frame = new Supprim();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Supprim(){
		setResizable(false);
		setTitle("Gestion des Stock");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		con = Mysql.Connect(); 
		
		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.setBackground(new Color(70, 130, 180));
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql="delete from Produit WHERE id=?  AND libelle=? ";
				
				try {
					requette = con.prepareStatement(sql);
					requette.setString(1, txtId.getText().toString());
					requette.setString(2, textField_lib.getText().toString());
					if(!txtId.getText().equals("") && !textField_lib.getText().equals("")) {
						int b =JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ce produit ?","supprimer le produit",JOptionPane.YES_NO_OPTION);
						if(b==0) {
							requette.execute();
							JOptionPane.showMessageDialog(null, "Produit bien supprimé");
						}
					}else {
						JOptionPane.showMessageDialog(null, "remplir les champs");
					}
					
					
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}			
			}
			
		});
		
		JLabel lblNewLabel_3 = new JLabel("GESTION DES STOCK");
		lblNewLabel_3.setForeground(new Color(30, 144, 255));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_3.setBounds(133, 23, 189, 14);
		contentPane.add(lblNewLabel_3);
		btnNewButton.setFont(new Font("Source Sans Pro Black", Font.BOLD, 9));
		btnNewButton.setBounds(192, 226, 94, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2_1 = new JLabel("Libelle:");
		lblNewLabel_2_1.setBackground(new Color(192, 192, 192));
		lblNewLabel_2_1.setOpaque(true);
		lblNewLabel_2_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(133, 166, 49, 36);
		contentPane.add(lblNewLabel_2_1);
		
		textField_lib = new JTextField();
		textField_lib.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql ="select libelle from Produit where libelle=?  ";
				try {
					requette=con.prepareStatement(sql);
					requette.setString(1, textField_lib.getText().toString());
					requette.execute();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		textField_lib.setForeground(Color.BLACK);
		textField_lib.setColumns(10);
		textField_lib.setBounds(192, 166, 94, 36);
		contentPane.add(textField_lib);
		
		JLabel lblNewLabel_2 = new JLabel("   Id :");
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(new Color(192, 192, 192));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setBounds(133, 119, 49, 36);
		contentPane.add(lblNewLabel_2);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql ="select id from Produit where id=?  ";
				try {
					requette=con.prepareStatement(sql);
					requette.setString(1, txtId.getText().toString());
					requette.execute();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		txtId.setForeground(Color.BLACK);
		txtId.setBounds(192, 119, 94, 36);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 5));
		lblNewLabel.setIcon(new ImageIcon("market (2).jpg"));
		lblNewLabel.setBounds(0, 64, 451, 244);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("desk1 (2).jpg"));
		lblNewLabel_1.setBounds(0, 0, 451, 64);
		contentPane.add(lblNewLabel_1);
	}
}
