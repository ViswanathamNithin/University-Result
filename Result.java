

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Result extends HttpServlet {
	
	Connection con =null;
	PreparedStatement pstm =null;
	ResultSet rs = null;
	String driver ="com.mysql.cj.jdbc.Driver";
;
	String url = "jdbc:mysql://localhost:3306/august?user=root&password=NITHIN@7";
	String sql = "Select * from result where id=?";
	
	
	
	public void init() {
		try {
			Class.forName(driver);
			System.out.println("Driver executed");
			con = DriverManager.getConnection(url);
			System.out.println("Connection established");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)  {
		try {
			pstm = con.prepareStatement(sql);
			String rollno=req.getParameter("rollno");
			int id=Integer.parseInt(rollno);
			pstm.setInt(1, id);
			rs=pstm.executeQuery();
			res.setContentType("text/html");
			PrintWriter pw =res.getWriter();
			if(rs.next()) {
				String name = rs.getString(2);
				int m1=rs.getInt(4);
				int m2= rs.getInt(5);
				int m3= rs.getInt(6);
				/*pw.println("Name: "+name);
				pw.println("Marks of M1: "+m1);
				pw.println("Marks of M2: "+m2);
				pw.println("Marks of M3: "+m3);*/
				/*pw.println("<!DOCTYPE html>");
				pw.println("<html><head><title>YOUR RESULT</title></head><body>");
				pw.println("<table><tr><th colspan='3'>"+name+" YOUR RESULT IS</th></tr>");
				pw.println("<tr><th colspan='3'>Marks of M1</th><th colspan='3'>Marks of M2</th><th colspan='3'>Marks of M3</th></tr>");
				pw.println("<tr><td colspan='3'>"+m1+"</td><td colspan='3'>"+m2+"</td><td colspan='3'>"+m3+"</td></tr></table></body></html>");*/
				pw.println("<!DOCTYPE html>");
				pw.println("<html><head><title>YOUR RESULT</title>");
				pw.println("<style>");
				pw.println("table { width: 60%; margin: 50px auto; border-collapse: collapse; font-family: Arial, sans-serif; }");
				pw.println("th, td { border: 1px solid black; padding: 10px; text-align: center; }");
				pw.println("th { background-color: #f2f2f2; }");
				pw.println("h2 { text-align: center; }");
				pw.println("</style></head><body>");

				pw.println("<h2>" + name + " - YOUR RESULT</h2>");
				pw.println("<table>");
				pw.println("<thead>");
				pw.println("<tr><th>Subject</th><th>Marks</th></tr>");
				pw.println("</thead>");
				pw.println("<tbody>");
				pw.println("<tr><td>Mathematics 1 (M1)</td><td>" + m1 + "</td></tr>");
				pw.println("<tr><td>Mathematics 2 (M2)</td><td>" + m2 + "</td></tr>");
				pw.println("<tr><td>Mathematics 3 (M3)</td><td>" + m3 + "</td></tr>");
				pw.println("</tbody>");
				pw.println("</table>");

				pw.println("</body></html>");

			}
			else {
				res.sendRedirect("/UniversityResul/error.html");  
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if(con!=null) {
				con.close();
			}
			pstm.close();
			rs.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
