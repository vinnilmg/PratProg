package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Pais;
import Service.VendedorService;

public class ListarPaisBuscar implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		String chave = request.getParameter("data[search]");
		VendedorService vendedor = new VendedorService();
		ArrayList<Pais> lista = null;
		HttpSession session = request.getSession();
			if (chave != null && chave.length() > 0) {
				lista = vendedor.listarPais(chave);
			} else {
				lista = vendedor.listarPais();
			}
			session.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("ListarPais.jsp");
			dispatcher.forward(request, response);
		}
	}


