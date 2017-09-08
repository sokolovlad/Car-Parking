package dbAccess;

import java.sql.*;

public class DBConnect {
	private Connection myConn;
	
	public DBConnect() throws ClassNotFoundException, SQLException {
		
		String dbUrl = "jdbc:mysql://localhost:3306/car_parking ";
		String user = "root";
		String password = "";
		
		Class.forName("com.mysql.jdbc.Driver");
		myConn = DriverManager.getConnection(dbUrl, user, password);
		System.out.println("initializing database...");
		Statement myStmt = myConn.createStatement(); 
	}
	
	public boolean searchUser(String Plate_number ) {
		PreparedStatement myStmt = null; 
		ResultSet myRS = null;
		
		try {
			myStmt = myConn.prepareStatement("select * from car where Plate_number=?  ");
			myStmt.setString(1, Plate_number);
		/*	myStmt.setString(2, password);*/
			myRS = myStmt.executeQuery();
			
			if(myRS.next()) {
				return true;
			}
			else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public static void main(String[] args) {
		try {
			DBConnect sampleObj = new DBConnect();
			System.out.println("db object created.");
			
			if(sampleObj.searchUser("222")) {
				System.out.println("Car parked!");
			} else {
				System.out.println("the user is NOT in the database.");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}









/*import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/**
 * Servlet implementation class DBConnect
 *//*
public class DBConnect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public DBConnect() {b
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}*/
