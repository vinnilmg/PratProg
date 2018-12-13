package Service;

import Model.Usuario;
import DAO.UsuarioDAO;

public class UsuarioService {
	
	public boolean validar(Usuario usuario){
		UsuarioDAO dao = new UsuarioDAO();
		return dao.validar(usuario);
	}
}
