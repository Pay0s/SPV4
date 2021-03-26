import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class JBDC {

	private static final String sql = "com.mysql.jbdc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/SPV4?serverTimezone=UTC";
	private static final String userName = "root";
	private static final String mdp = "";
	
	
	public static Connection Connex(){
		
		try {
			
			Connection con = DriverManager.getConnection(url, userName, mdp);
			return con;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
	}
	
	public static ResultSet TableArticle() throws SQLException {
		Connection con = Connex();
		java.sql.Statement statement =  con.createStatement();
		ResultSet Articles = statement.executeQuery("select * from Articles");
		return Articles;
		
		
	}
	
	
	public static void addArticle(String Nom, int Qte, String Description) throws SQLException {
		Connection con = Connex();
		//java.sql.Statement statement =  con.createStatement();
		PreparedStatement pps = con.prepareStatement("INSERT INTO `articles` (`ID`, `Nom`, `Qte`, `Description`) VALUES (NULL, ? , ? , ?)");
		
		pps.setString(1, Nom);
		pps.setInt(2, Qte);
		pps.setString(3, Description);
		pps.executeUpdate(); 
		//pa
		//PreparedStatement pps = con.execute("INSERT INTO `articles` (`ID`, `Nom`, `Qte`, `Description`) VALUES (NULL, Nom , Qte, Description)");
		
		
		
	}
	
	
	
}
