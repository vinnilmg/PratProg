package Service;

import java.util.ArrayList;

import Model.Pais;
import DAO.PaisDao;

public class VendedorService {
	PaisDao dao;
	
	public VendedorService(){
		dao = new PaisDao();
	}
	public ArrayList<Pais> listarPais(){
		return dao.listarPais();
	}
	public ArrayList<Pais> listarPais(String chave){
		return dao.listarPais(chave);
	}

}
