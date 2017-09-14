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
//
	public String getparknumber(String Plate_number ) {
	PreparedStatement myStmt = null; 
	ResultSet myRS = null;
	
	try {
		myStmt = myConn.prepareStatement("select * from car where Plate_number=?  ");
		myStmt.setString(1, Plate_number);
		myRS = myStmt.executeQuery();
		
		if(myRS.next()) {
			return myRS.getString(2);
		}
		else {
			return "";
		}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
// date from parkingspaces
	public ResultSet getAvailableParking() {
	PreparedStatement myStmt = null; 
	ResultSet myRS = null;
	
	try {
		myStmt = myConn.prepareStatement("select * from parkingspaces where Available=1");
		
		myRS = myStmt.executeQuery();
		
			return myRS;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return myRS;
	}
	
	public String getTimeFinish(String Plate_number ) {
	PreparedStatement myStmt = null; 
	ResultSet myRS = null;
	
	try {
		myStmt = myConn.prepareStatement("select * from car where Plate_number=?  ");
		myStmt.setString(1, Plate_number);
		myRS = myStmt.executeQuery();
		
		if(myRS.next()) {
			return myRS.getString(4);
		}
		else {
			return "";
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return "";
	}
//Update Extend time
	public void ExtendTime(String Plate_number, String Time_finish_New) {
		PreparedStatement myStmt = null; 
		int myRS = 0;
		try {
			myStmt = myConn.prepareStatement("update car set Time_stop=? where Plate_number=?");
			myStmt.setString(1, Time_finish_New);
			myStmt.setString(2, Plate_number);
			myRS = myStmt.executeUpdate();
	}	catch (SQLException e) {
			e.printStackTrace();
		}
	}
//Update New park 
		public void NewPark(String Plate_number, String Parking_number, String Time_finish_New) {
			PreparedStatement myStmt = null; 
			int myRS = 0;
			try {
				myStmt = myConn.prepareStatement("insert into car (Plate_number, Parking_number, Time_stop) Values (?,?,?)");
				myStmt.setString(1, Plate_number);
				myStmt.setString(2, Parking_number);
				myStmt.setString(3, Time_finish_New);
				myRS = myStmt.executeUpdate();
		}	catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
//Update Stop
	public void StopParking(String Plate_number) {
		PreparedStatement myStmt = null; 
		int myRS = 0;
		try {
			myStmt = myConn.prepareStatement("delete from car where Plate_number=?");
			myStmt.setString(1, Plate_number);
			myRS = myStmt.executeUpdate();
	}	catch (SQLException e) {
			e.printStackTrace();
		}
	}
//Update AvailableParking Stop
	public void UpAvailableParking(String Park_number) {
		PreparedStatement myStmt = null; 
		int myRS = 0;
		try {
			myStmt = myConn.prepareStatement("update parkingspaces set Available=1 where Parking_number=?");
			myStmt.setString(1, Park_number);		
			myRS = myStmt.executeUpdate();
	}	catch (SQLException e) {
			e.printStackTrace();
		}
	}
//Update AvailableParking Park
	public void NotAvailableParking(String Park_number) {
		PreparedStatement myStmt = null; 
		int myRS = 0;
		try {
			myStmt = myConn.prepareStatement("update parkingspaces set Available=0 where Parking_number=?");
			myStmt.setString(1, Park_number);		
			myRS = myStmt.executeUpdate();
	}	catch (SQLException e) {
			e.printStackTrace();
		}
	}
//test
/*	public static void main(String[] args) {
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
	}	*/

	
}
