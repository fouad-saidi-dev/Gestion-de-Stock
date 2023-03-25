import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;



@SuppressWarnings("serial")
public class AjouterProd extends JFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	
	Connection con =null;
	ResultSet rs =null;
	PreparedStatement requette=null;
	private JTable table;
	private JTextField textField_rech;
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AjouterProd window = new AjouterProd();
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
	public AjouterProd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestion des Stock");
		frame.setResizable(false);
		frame.setBounds(100, 100, 726, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		con = Mysql.Connect(); 
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBackground(new Color(65, 105, 225));
		btnSupprimer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Supprim obj=new Supprim();
				obj.setVisible(true);
                actual();
			}
		});
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBackground(new Color(65, 105, 225));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql ="update Produit set libelle=? , prix=? , quantite=?  where id=? ";
	            
				try {
					requette=con.prepareStatement(sql);
					requette.setString(1,textField_1.getText().toString());
					requette.setString(2,textField_2.getText().toString());
					requette.setString(3,textField_3.getText().toString());
					requette.setString(4,textField.getText().toString());
					if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")) {
						
						
						int b =JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment modifier ce produit ?","modifier le produit",JOptionPane.YES_NO_OPTION);
						if(b==0) {
							requette.execute();
							JOptionPane.showMessageDialog(null,"bien modifié");
						}
						
					}else{
						JOptionPane.showMessageDialog(null, "remplir les champs");
					}
					actual();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}		    	
			
			}
		});
		
		JButton btnNewButton = new JButton("actualiser");
		btnNewButton.setBackground(new Color(65, 105, 225));
		btnNewButton.setForeground(new Color(128, 0, 0));
		btnNewButton.setFont(new Font("Source Sans Pro Black", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actual();
			}
		});
		
		JButton btnrech = new JButton("rechercher");
		btnrech.setBackground(new Color(65, 105, 225));
		btnrech.setForeground(new Color(128, 0, 0));
		btnrech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recherch();
			}
		});
		btnrech.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 9));
		btnrech.setBounds(506, 75, 86, 20);
		frame.getContentPane().add(btnrech);
		
		textField_rech = new JTextField();
		textField_rech.setBounds(405, 74, 86, 20);
		frame.getContentPane().add(textField_rech);
		textField_rech.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(new Color(65, 105, 225));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
				textField_3.setText(null);
				textField_rech.setText(null);
				
			}
		});
		btnClear.setForeground(new Color(128, 0, 0));
		btnClear.setFont(new Font("Source Sans Pro Black", Font.BOLD, 10));
		btnClear.setBounds(180, 338, 96, 23);
		frame.getContentPane().add(btnClear);
		btnNewButton.setBounds(97, 365, 101, 20);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(301, 105, 399, 269);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		btnModifier.setForeground(new Color(128, 0, 0));
		btnModifier.setFont(new Font("Source Sans Pro Black", Font.BOLD, 10));
		btnModifier.setBounds(20, 338, 96, 23);
		frame.getContentPane().add(btnModifier);
		btnSupprimer.setForeground(new Color(128, 0, 0));
		btnSupprimer.setFont(new Font("Source Sans Pro Black", Font.BOLD, 10));
		btnSupprimer.setBounds(180, 304, 96, 23);
		frame.getContentPane().add(btnSupprimer);
		
		JLabel lblNewLabel_6 = new JLabel(" Quantit\u00E9 :");
		lblNewLabel_6.setOpaque(true);
		lblNewLabel_6.setBackground(new Color(192, 192, 192));
		lblNewLabel_6.setForeground(new Color(30, 144, 255));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 245, 69, 48);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_5 = new JLabel(" Libelle :");
		lblNewLabel_5.setOpaque(true);
		lblNewLabel_5.setBackground(new Color(192, 192, 192));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setForeground(new Color(30, 144, 255));
		lblNewLabel_5.setBounds(10, 133, 69, 46);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_4 = new JLabel("    Prix :");
		lblNewLabel_4.setBackground(new Color(192, 192, 192));
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setForeground(new Color(30, 144, 255));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(10, 188, 69, 46);
		frame.getContentPane().add(lblNewLabel_4);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(65, 105, 225));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql ="insert into Produit (id,libelle,prix,quantite) values (?,?,?,?)";
				
			    try {
					requette=con.prepareStatement(sql);
					requette.setString(1,textField.getText().toString());
					requette.setString(2,textField_1.getText().toString());
					requette.setString(3,textField_2.getText().toString());
					requette.setString(4,textField_3.getText().toString());
					
					
					if(!textField.getText().equals("") && !textField_1.getText().equals("") && !textField_2.getText().equals("") && !textField_3.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Produit ajoutée");
						requette.execute();
					}else {
						JOptionPane.showMessageDialog(null, "remplir les champs");
					}
					
					actual();		
			    } catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});
		btnAjouter.setFont(new Font("Source Sans Pro Black", Font.BOLD, 10));
		btnAjouter.setForeground(new Color(128, 0, 0));
		btnAjouter.setBounds(20, 304, 96, 23);
		frame.getContentPane().add(btnAjouter);
		
		JLabel lblNewLabel_3 = new JLabel("     Id :");
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(new Color(192, 192, 192));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setForeground(new Color(30, 144, 255));
		lblNewLabel_3.setBounds(10, 75, 69, 47);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(89, 246, 122, 46);
		frame.getContentPane().add(textField_3);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(89, 190, 122, 46);
		frame.getContentPane().add(textField_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(89, 133, 122, 46);
		frame.getContentPane().add(textField_1);
		
		textField = new JTextField();
		textField.setBounds(89, 75, 122, 46);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("GESTION DES STOCK");
		lblNewLabel_2.setForeground(new Color(30, 144, 255));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(258, 19, 245, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel backg = new JLabel("New label");
		backg.setIcon(new ImageIcon("market (1).jpg"));
		backg.setBounds(0, 66, 710, 330);
		frame.getContentPane().add(backg);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("desk1 (1).jpg"));
		lblNewLabel_1.setBounds(0, 0, 710, 67);
		frame.getContentPane().add(lblNewLabel_1);
	}
	public void actual() {
		
		String sql="select*from Produit";
		try {
			requette=con.prepareStatement(sql);
			rs=requette.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void recherch() {
		String sql="select*from Produit where id=?";
		try {
			requette=con.prepareStatement(sql);
			requette.setString(1,textField_rech.getText().toString());
			rs=requette.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.last();
			int nbrligne= rs.getRow();
			if(nbrligne!=0) {
				JOptionPane.showMessageDialog(null, "Produit trouvé");
			}else {
				JOptionPane.showMessageDialog(null, "Produit non trouvé");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
