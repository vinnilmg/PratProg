package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

/**
 * Servlet implementation class ManterPais
 */
@WebServlet("/ManterPais.do")
public class ManterPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
   
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pNome = request.getParameter("nome");
		long pPop = Long.parseLong(request.getParameter("pop"));
		double pArea = Double.parseDouble(request.getParameter("area"));
		
		//instanciar o javabean
		Pais pais = new Pais();
		pais.setNome(pNome);
		pais.setPopulacao(pPop);
		pais.setArea(pArea);
		
		//instanciar o service
		PaisService ps = new PaisService();
		ps.criar(pais);
		pais = ps.carregar(pais.getId());
	
		RequestDispatcher dispatcher = null;
	
		request.setAttribute("pais", pais);
		dispatcher = request.getRequestDispatcher("Pais.jsp");
		
		dispatcher.forward(request, response);
		
	}

	}

