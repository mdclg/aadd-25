package aadd.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletRegistro
 */
@WebServlet("/ServletRegistro")
public class ServletRegistro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = new Usuario();
		user.setNombre(request.getParameter("nombre"));
		user.setApellidos(request.getParameter("apellidos"));
		user.setNif(request.getParameter("nif"));
		user.setUsermail(request.getParameter("usermail"));
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("clave"));
		
		// Recupera el contexto de la aplicación

		ServletContext app = request.getServletContext();
		HashMap<String, Usuario> usuariosHash = (HashMap<String,Usuario>) app.getAttribute("usuarios");
		// Si no existe, la crea
		if ( usuariosHash == null ) {
		    usuariosHash = new HashMap();
		    app.setAttribute("usuarios", usuariosHash);
		}

		// Intenta guardar un usuario. Si existe el identificador, devuelve un error
		if ( usuariosHash.get(user.getUsername()) != null ) {
		    response.sendError(500, "Identificador de usuario duplicado");
		    return;
		} else {
		    usuariosHash.put(user.getUsername(), user);
		}
		app.setAttribute("usuarios", usuariosHash);
	}

}
