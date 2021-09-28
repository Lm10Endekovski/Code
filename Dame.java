package de.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;


@WebServlet("/Dame")
public class Dame extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Dame() {
        super();
        // TODO Auto-generated constructor stub
    }
    String url = "jdbc:mysql://localhost:3306/studentidatabase";
   	String username = "root1";
    String password = "root1";
   	Connection con;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		 if (request.getParameter("addStudent") !=null  ) {
			
		 
			String name = request.getParameter("imeStudent");
			String surname = request.getParameter("prezimeStudent");
			String index = request.getParameter("indeksStudent");
		;
			
			try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			Statement st = con.createStatement();
			
			
			String signupQuery= "INSERT INTO tblstudent VALUES (" + "'" + name + "','" + surname +
			"','" + index +  "');";
			
			st.executeUpdate(signupQuery);
			response.sendRedirect("index.jsp");
			}
			catch (ClassNotFoundException e) {
				String st = e.toString();
				JOptionPane.showMessageDialog(null, st);

			} catch (SQLException e) {

				e.printStackTrace();
				String st = e.toString();
				JOptionPane.showMessageDialog(null, st);
			}
		
		 
	 }
		 else if (request.getParameter("loginStudent") != null ) {
				String index = request.getParameter("loginIndeks");
				HttpSession sess = request.getSession();
				sess.setAttribute("loginIndeks", index);
				if (index.equals("admin") ) {
				response.sendRedirect("jsp/index.jsp");
				} else {
				try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
				Statement st = con.createStatement();
				String select = "SELECT indeks FROM tblstudent";
				ResultSet res = st.executeQuery(select);
				while (res.next()) {
				if (index.equals(res.getString("indeks"))) {
				response.sendRedirect("jsp/subject.jsp");
				} else {
				}
				}
				} catch (ClassNotFoundException e) {
				e.printStackTrace();
				} catch (SQLException e) {
				e.printStackTrace();
				}
				}
				
			}
		 if (request.getParameter("predmet") !=null  ) {
				
				String predmet = request.getParameter("selectbox");
				String krediti = request.getParameter("selectbox2");
				String semestar = request.getParameter("vnesSemestar");
				String sifra = request.getParameter("vnesSifra");
				
				HttpSession sess = request.getSession();
				String indeks = (String)sess.getAttribute("loginIndeks");
				
				final String mailFromSend = "javamaildamjan@gmail.com"; //meil od koj ke se isprati porakata
				final String passwordForSend = "damjanjavamailtest"; //password za meilot od koj ke se ispraka porakata
				final String mailToSend = request.getParameter("hiddenInput"); //meil na koj ke se isprati porakata
				
				//parametri (protokoli i porti) za prakanje na meil poraki preku gmail serverot
				Properties prop = new Properties();
				prop.put("mail.smtp.host", "smtp.gmail.com");
				prop.put("mail.smtp.port", "587");
				prop.put("mail.smtp.auth", "true");
				prop.put("mail.smtp.starttls.enable", "true");
				prop.put("mail.smtp.ssl.trust", "*");//ova mozi da nee najubo resenie
				
				//sesija koja se kreira preku java (javax.mail), za avtentikacija dali postoi meilot od koj ke se praka porakata
				Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
					protected PasswordAuthentication
					getPasswordAuthentication() {
						return new PasswordAuthentication(mailFromSend,
							passwordForSend);
					}
				});
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					
					
					String insert= "INSERT INTO tblpredmet VALUES (" + "'" + sifra + "','" + predmet +
					"','" + krediti +  "','" + semestar +  "');";
					
					String insert2 = "INSERT INTO tblstudent_predmet(indeks,sifra) VALUES(" + "'" + indeks + "','" + sifra +  "');";
					st.executeUpdate(insert);
					st.executeUpdate(insert2);
					
					// PrintWriter out = response.getWriter();
					// System.out.println("indeksot e :"+ indeks );
					//prakanje na porakata
					Message message = new MimeMessage(session);
					message.setFrom(new InternetAddress(mailFromSend));
					message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mailToSend));
					message.setSubject("Izbiranje na predmet!");
					message.setText("Pochituvani, vie go izbravte predmetot:"+" " + predmet + " koj iznesuva "+" " +krediti+ "krediti");
					Transport.send(message);
					System.out.println("Email sent successfully to: " + mailToSend);
					response.sendRedirect("index.jsp");
				}
				catch (ClassNotFoundException e) {
					String st = e.toString();
					JOptionPane.showMessageDialog(null, st);

				} catch (SQLException e) {

					e.printStackTrace();
					String st = e.toString();
					JOptionPane.showMessageDialog(null, st);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			
			 
		 }
		//PrintWriter out = response.getWriter();
			//	out.println("<h3>" + "vleze"  +
			//	"</h3>");
		 
		
	}

}
