<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String url ="jdbc:mysql://localhost:3306/studentidatabase";
String username = "root1";
String password ="root1";
Connection con;
	
Class.forName("com.mysql.jdbc.Driver");
con= DriverManager.getConnection(url, username, password);
Statement st = con.createStatement();

String selectQuery = "SELECT * FROM tblstudent";
ResultSet rez =st.executeQuery(selectQuery);

out.print("<table>");
out.print("<tr>");
out.print("<th>IME:</th>");
out.print("<th>PREZIME:</th>");
out.print("<th>INDEKS:</th>");
out.print("</tr>");
	while (rez.next()){
		out.print("<tr>");
		out.print("<td>" + rez.getString("ime")+ "</td>");
		out.print("<td>" + rez.getString("prezime")+ "</td>");
		out.print("<td>" + rez.getString("indeks")+ "</td>");
		
		

		out.print("</tr>");
	} 
	
	%>
</body>
</html>
