package aadd.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCookie
 */
@WebServlet("/ServletCookie")
public class ServletCookie extends HttpServlet {
	 private static final long serialVersionUID = 1L;


	    public ServletCookie() {
	        super();
	    }


	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doPost(request, response);
	    }


	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        {
	            response.setContentType("text/html");
	            PrintWriter out = response.getWriter();
	            out.println("<html><body><B> Usted se identifico anteriormente como ");
	            out.print(request.getAttribute("usuario") + "</B></body></html>");
	        }
	    }
}
