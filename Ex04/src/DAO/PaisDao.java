package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Pais;

public class PaisDao {

	ConnectionFactory cf = new ConnectionFactory();
	Pais pais;
	
	public int criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";

		try (Connection conn = cf.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						pais.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return pais.getId();
	}

	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		
		try (Connection conn = cf.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
				stm.setInt(4, pais.getId());
				stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(int id) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = cf.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1, pais.getId());
				stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int id) {
		Pais pais = new Pais();
		String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE id = ?";
		
		try (Connection conn = cf.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, pais.getId());
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					pais.setNome(rs.getString("nome"));
					pais.setPopulacao(rs.getLong("populacao"));
					pais.setArea(rs.getDouble("area"));
				} else {
					pais.setId(-1);
					pais.setNome(null);
					pais.setPopulacao(-1);
					pais.setArea(-1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return pais;
	}

	public void maiorPopulacao() {
		String sqlMaxPop = "SELECT * FROM paises WHERE populacao = (SELECT MAX(populacao) FROM pais)";
		try (Connection conn = cf.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlMaxPop);	
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				pais.setNome(rs.getString("nome"));
				pais.setPopulacao(rs.getLong("populacao"));
				pais.setId(rs.getInt("id"));
				pais.setArea(rs.getDouble("area"));
			}else {
				System.out.println("Erro");
			}
		
		}catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void menorArea() {
		String sqlGet = "SELECT * FROM pais WHERE area = (SELECT Min(area) FROM pais)";
		try (Connection conn = cf.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {
				pais.setNome(rs.getString    ("nome"));
				pais.setPopulacao(rs.getLong ("populacao"));
				pais.setId(rs.getInt			("id"));
				pais.setArea(rs.getDouble    ("area"));
			}else {
				System.out.println("Erro");
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
	}

	public void vetPaises(String vet[]) {	
		String sqlGet = "SELECT nome FROM paises ORDER BY nome";
		int cont = 0 ;
		try (Connection conn = cf.obtemConexao()){
			PreparedStatement stm = conn.prepareStatement(sqlGet);
			ResultSet rs = stm.executeQuery();
			while(rs.next() && cont < 3 ) {
				vet[cont] = rs.getString("nome");
				cont ++;
			}
		}catch (SQLException e) {
		System.out.println(e);
		}
	}
}
