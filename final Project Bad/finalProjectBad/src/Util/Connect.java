package Util;
import java.sql.*;

public class Connect {
	  private final String USERNAME = "root";
	  private final String PASSWORD = "";
	  private final String DATABASE = "watchesdealer";
	  private final String HOST = "localhost:3306";
	  private final String CONNECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	  
	  public ResultSet rs;
	  public ResultSetMetaData rsm;
	  
	  private Connection con;
	  private Statement st;
	  
	  private static Connect connect;
	  
	  public static Connect getInstance() {
			 if(connect == null) return new Connect();
			 return connect;
		  }
	  
	  private Connect() {
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			st = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	//for sellect
	  public ResultSet execQuery(String query) {
		  try {
			rs = st.executeQuery(query);
			rsm = rs.getMetaData();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  return rs;
		  
	  }
	  
	//for insert
	 public void execUpdate (String query) {
		 try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public PreparedStatement prepareStatement(String query) {
		 PreparedStatement ps = null;
		 try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		 return ps;
	 }
	  
	  
	  
	  
	  
	  
	  
}
