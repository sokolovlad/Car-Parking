<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
	<style>
   body {
    background: #F0FFF0; /* fone colour */
    color: #778899; /* text colour */
   }
  </style>
<body>

	<form method="post" action="CarCheck">
	<table>
		
		<tr>		
			<td>Car plate number</td>
			<td><input type="text" name="Car_plate"></td>
		</tr>
		
		<tr>
				<td>
			<input type="submit" value="Park" name="btnPark_login"/>
				</td>
				
				<td>
			<input type="submit" value="Check time" name="btnCheck"/>
				</td>		
		</tr> 
		
	</table>	
	</form>
	
</body>
</html> 