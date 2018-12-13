package Command;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Pais;
import Service.PaisService;

public class VisualizarPais implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pId = request.getParameter("id");
		String pNome = request.getParameter("nome");
		String pPop = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		int idPais = -1;
		
		try {
			idPais = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		Pais pais = new Pais();
		pais.setId(idPais);
		pais.setNome(pNome);
		if(pPop!=null && pPop.length() > 0) {
			pais.setPopulacao(Long.parseLong(pPop));
		}
		if(pArea!=null && pArea.length()>0) {
			pais.setArea(Double.parseDouble(pArea));
		}
		
		PaisService ps = new PaisService();

		RequestDispatcher view = null;
		
		pais = ps.carregar(pais.getId());
		request.setAttribute("pais", pais);
		view = request.getRequestDispatcher("VisualizarPais.jsp");
		
		view.forward(request, response);

	}

}
