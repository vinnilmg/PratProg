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
		request.setCharacterEncoding("UTF-8");
		String pAcao = request.getParameter("acao");
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
		
		if (pAcao.equals("Criar")) {
			ps.criar(pais);
			ArrayList<Pais> lista = new ArrayList<>();
			lista.add(pais);
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
			
		} else if (pAcao.equals("Excluir")) {
			ps.excluir(pais.getId());
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			lista.remove(busca(pais, lista));
			session.setAttribute("lista", lista);
			view = request.getRequestDispatcher("ListarPais.jsp");
			
		} else if (pAcao.equals("Alterar")) {
			ps.atualizar(pais);
			ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
			int pos = busca(pais, lista);
			lista.remove(pos);
			lista.add(pos, pais);
			session.setAttribute("lista", lista);
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
			
		} else if (pAcao.equals("Visualizar")) {
			pais = ps.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("VisualizarPais.jsp");
			
		} else if (pAcao.equals("Editar")) {
			pais = ps.carregar(pais.getId());
			request.setAttribute("pais", pais);
			view = request.getRequestDispatcher("AlterarPais.jsp");		
		}
		
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

