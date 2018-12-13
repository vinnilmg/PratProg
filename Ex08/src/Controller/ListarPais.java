package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.VendedorService;
import Model.Pais;

/**
 * Servlet implementation class ListarClientesController
 */
@WebServlet("/ListarPais.do")
public class ListarPais extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		VendedorService vendedor = new VendedorService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
		if (acao.equals("Buscar")) {
			if (chave != null && chave.length() > 0) {
				lista = vendedor.listarPais(chave);
			} else {
				lista = vendedor.listarPais();
			}
			session.setAttribute("lista", lista);
		} else if (acao.equals("reiniciar")) {
			session.setAttribute("lista", null);
		}

		RequestDispatcher dispatcher = request
				.getRequestDispatcher("ListarPais.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
