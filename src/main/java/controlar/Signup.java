package controlar;



import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.mydao;
import DTO.Customer;

@WebServlet(urlPatterns = "/signup")
@MultipartConfig
public class Signup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//name
		String name = req.getParameter("username");
		
		//email
		String email = req.getParameter("email");
		
		//password
		String password = req.getParameter("password");
		
		//confirm password
		String mobile = req.getParameter("mobile");
		
		//DOB
		LocalDate date = LocalDate.parse(req.getParameter("date"));
		
		//calculating the age
		int age = Period.between(date, LocalDate.now()).getYears();
		
		//Gender
		String gender = req.getParameter("gender");
		
		//logic for storing the photo
		Part pic = req.getPart("picture");
		byte picture[] = null;
		picture = new byte[pic.getInputStream().available()];
		pic.getInputStream().read(picture);
	
		mydao dao=new mydao();
		if(dao.fetchByEmail(email)==null && dao.fetchByMobile(age)==null) {
			
		
		Customer c= new Customer();
		c.setAge(age);
		c.setDob(date);
		c.setEmail(email);
		c.setName(name);
		c.setMobile(age);
		c.setPassword(password);
		c.setPicture(picture);
		
	
		dao.save(c);
		resp.getWriter().print("<h1 style='color:green'> accoumt created Successfully</h1>");
		}
		else {
		resp.getWriter().print("<h1 style='color:brown'>Email and mobile should be unique<h1>");
	}}
}

