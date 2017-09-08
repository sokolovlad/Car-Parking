package dbAccess;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CarCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DBConnect newConnection;
       
    public CarCheck() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Car_plate = request.getParameter("Car_plate") ;
		/*String pass = request.getParameter("password")*/
				
		try {
			newConnection = new DBConnect();
			if(newConnection.searchUser("Car_plate")) {
				response.sendRedirect("Info.jsp");
			}
			else {
					boolean result = newConnection.searchUser(Car_plate);
					
					if(result == true) {
						response.sendRedirect("Info.jsp");					
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
}
