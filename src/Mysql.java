import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Mysql  {
	
	public static Connection Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Gestion_Produit","root","");
			return con;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		 return null; 
		}
		
	}
	
	

}
