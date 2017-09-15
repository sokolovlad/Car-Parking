package dbAccess;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.text.SimpleDateFormat;
//import java.time.LocalTime;
//import java.util.Calendar;
//import java.util.Scanner;

public class CarCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	static int timeToPark = 0;
	
	private DBConnect newConnection;
       
    public CarCheck() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Car_plate = request.getParameter("Car_plate") ;
		String btnCheck = request.getParameter("btnCheck") ;
		String btnPark_login = request.getParameter("btnPark_login") ;
		String btnPark = request.getParameter("btnPark") ;
		String btnExtend = request.getParameter("btnExtend") ;
		String btnExtendTime = request.getParameter("btnExtendTime") ;
		String btnStop = request.getParameter("btnStop") ;
		String Time_finish_New = request.getParameter("Time_finish_New") ;
		String Parking_Number = request.getParameter("Parking_number") ;
		String Park_Number = request.getParameter("Park_number") ;
//		String starttime
		
//		public static String starttime() {
//			Calendar cal = Calendar.getInstance();
//			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//			return sdf.format(cal.getTime());
//		}
//		
//		public static String endTime(int minutesToPark) {
//			timeToPark = minutesToPark;
//			
//			LocalTime lt = LocalTime.parse(startTime());
//			LocalTime ltLater = null;
//			String endTime = null;
//			ltLater = lt.plusMinutes(minutesToPark);
//			endTime = ltLater.toString();
//			return endTime;
//		}		
		
		//---for login page Button Check time .jsp
		if(btnCheck!=null) {
			try {
				newConnection = new DBConnect();
				if(newConnection.searchUser("Car_plate")) {
					response.sendRedirect("Info.jsp");
				}
				else {
				boolean result = newConnection.searchUser(Car_plate);
				
				if(result == true) {
					String ParkingNumber = newConnection.getparknumber(Car_plate);
					request.setAttribute("Car_plate",Car_plate);
					request.setAttribute("ParkingNumber",ParkingNumber);
					
					String ParkingTimeFinish = newConnection.getTimeFinish(Car_plate);						
					request.setAttribute("ParkingTimeFinish",ParkingTimeFinish);
					RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/Info.jsp");
					reqDisp.forward(request, response);
					
						}else {
							response.sendRedirect("error.jsp");
						}
				}
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
					
			}
		}
		
		//---for login page Button Park.jsp
				if(btnPark_login!=null) {
					try {
						newConnection = new DBConnect();
						if(newConnection.searchUser("Car_plate")) {
							response.sendRedirect("Park.jsp");
						}
						else {
						boolean result = newConnection.searchUser(Car_plate);
						if(result == false) {
							String Available ="";
							ResultSet spaces = newConnection.getAvailableParking();
							while(spaces.next()) {
								Available = Available+spaces.getString(1)+", ";
							}
							request.setAttribute("Car_plate",Car_plate);
							request.setAttribute("Available",Available);
							RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/park.jsp");
							reqDisp.forward(request, response);
						}else {
							response.sendRedirect("error2.jsp");
								}
						}
					}catch (ClassNotFoundException e) {
						e.printStackTrace();
					}catch (SQLException e) {
						e.printStackTrace();
							
					}
				}
		
		//---for info page Button Extend.jsp
		if(btnExtend!=null) {
			try {
				newConnection = new DBConnect();
				if(newConnection.searchUser(Car_plate)) {
					String ParkingNumber = newConnection.getparknumber(Car_plate);
					request.setAttribute("ParkingNumber",ParkingNumber);					
					String ParkingTimeFinish = newConnection.getTimeFinish(Car_plate);						
					request.setAttribute("ParkingTimeFinish",ParkingTimeFinish);					
					request.setAttribute("Car_plate",Car_plate);
					RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/extend.jsp");
					reqDisp.forward(request, response);
				}
				
			}catch (ClassNotFoundException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				e.printStackTrace();
					
			}
		}
		//---for info page Button stop.jsp
				if(btnStop!=null) {
					try {
						newConnection = new DBConnect();
						if(newConnection.searchUser(Car_plate)) {
							System.out.println(Parking_Number);
							newConnection.UpAvailableParking(Park_Number);
							newConnection.StopParking(Car_plate);							
							request.setAttribute("Car_plate",Car_plate);
							RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/stop.jsp");
							reqDisp.forward(request, response);
						}
						
					}catch (ClassNotFoundException e) {
						e.printStackTrace();
					}catch (SQLException e) {
						e.printStackTrace();
							
					}
				}
		
		//---for Extend page Button Extend time.jsp
				if(btnExtendTime!=null) {
					System.out.println(Car_plate);
					System.out.println(Time_finish_New);
					try {
						newConnection = new DBConnect();
						if(newConnection.searchUser(Car_plate)) {
							newConnection.ExtendTime(Car_plate, Time_finish_New);
							request.setAttribute("Car_plate",Car_plate);
							request.setAttribute("Parking_Number",Parking_Number);							
							String ParkingTimeFinish = newConnection.getTimeFinish(Car_plate);						
							request.setAttribute("ParkingTimeFinish",ParkingTimeFinish);
							RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/Info.jsp");
							reqDisp.forward(request, response);
						}
						
					}catch (ClassNotFoundException e) {
						e.printStackTrace();
					}catch (SQLException e) {
						e.printStackTrace();
							
					}
				}	
//				//---for Park page Button Park.jsp
				if(btnPark!=null) {
					System.out.println(Car_plate);
					try {
						newConnection = new DBConnect();
							newConnection.NewPark(Car_plate, Parking_Number, Time_finish_New);
							newConnection.NotAvailableParking(Parking_Number);
							request.setAttribute("Car_plate",Car_plate);
							//newConnection.AvailableParking(Parking_Number);
							String ParkingTimeFinish = newConnection.getTimeFinish(Car_plate);
							RequestDispatcher reqDisp = getServletContext().getRequestDispatcher("/login.jsp");
							reqDisp.forward(request, response);
						
					}catch (ClassNotFoundException e) {
						e.printStackTrace();
					}catch (SQLException e) {
						e.printStackTrace();
							
					}
				}	
	
	}
	private void starttime() {
		// TODO Auto-generated method stub
		
	}
}
