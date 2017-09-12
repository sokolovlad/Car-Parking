package dbAccess;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CarCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Object ParkingTimeFinish = null;
	
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
		String btnExtend = request.getParameter("btnExtend") ;
		String btnExtendTime = request.getParameter("btnExtendTime") ;
		String btnStop = request.getParameter("btnStop") ;
		String Time_finish_New = request.getParameter("Time_finish_New") ;
		String Parking_Number = request.getParameter("Parking_Number") ;
		
		
		//---for login.jsp
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
		
		//---for info_extend.jsp
		if(btnExtend!=null) {
			try {
				newConnection = new DBConnect();
				if(newConnection.searchUser(Car_plate)) {
					String ParkingNumber = newConnection.getparknumber(Car_plate);
				/*	request.setAttribute("Car_plate",Car_plate);*/
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
		//---for info_stop.jsp
				if(btnStop!=null) {
					try {
						newConnection = new DBConnect();
						if(newConnection.searchUser(Car_plate)) {
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
		
		//---for Extend.jsp
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
	
	}
}
