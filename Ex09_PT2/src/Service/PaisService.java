package Service;

import DAO.PaisDao;
import Model.Pais;

public class PaisService {
	PaisDao dao;
	
	public PaisService() {
	 dao = new PaisDao();
	}
	
	public int criar(Pais pais) {
		return dao.criar(pais);
	}
	
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}
	
	public void excluir(int id) {
		dao.excluir(id);
	}
	
	public Pais carregar(int id) {
		return  dao.carregar(id);
	}
	
}
