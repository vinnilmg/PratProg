package Command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.Pais;
import Service.PaisService;

public class CriarPais implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		HttpSession session = request.getSession();

		ps.criar(pais);
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarPais.jsp");
		
		view.forward(request, response);

	}

	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == pais.getId()){
				return i;
			}
		}
		return -1;
	}


}
