package ex02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Pais {
	
	/*	b. Crie uma classe Pais com os atributos id (int),
	nome (String), populacao (long) e area(double).*/
	
	private int id;
	private String nome;
	private long populacao;
	private double area;
	
	// c. Crie um construtor com os campos, um construtor padrão, gets e sets.
	
	public Pais(int id, String nome, long populacao, double area) {
		this.id = id;
		this.nome = nome;
		this.populacao = populacao;
		this.area = area;
	}

	public Pais() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getPopulacao() {
		return populacao;
	}

	public void setPopulacao(long populacao) {
		this.populacao = populacao;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

//	d.Crie os métodos do CRUD de Pais.
	
	static {
		try {
	Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
	throw new RuntimeException(e);
		}
	}
	
	public Connection obtemConexao() throws SQLException {
	return DriverManager
	.getConnection("jdbc:mysql://localhost/Pais?user=root&password=buibui10");
	}

	public void criar() {
			String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES ( ?, ?, ?)";

			try (Connection conn = obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
					stm.setString(2, getNome());
					stm.setLong(3, getPopulacao());
					stm.setDouble(4, getArea());
					stm.execute();
					String sqlQuery  = "SELECT LAST_INSERT_ID()";
				try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
					if(rs.next()){
						setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	public void atualizar() {
			String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
			
			try (Connection conn = obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
					stm.setString(1, getNome());
					stm.setLong(2, getPopulacao());
					stm.setDouble(3, getArea());
					stm.setInt(4, getId());
					stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	public void excluir() {
			String sqlDelete = "DELETE FROM pais WHERE id = ?";
			
			try (Connection conn = obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
					stm.setInt(1, getId());
					stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	public void carregar() {
			String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
			
			try (Connection conn = obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
					stm.setInt(1, getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						setNome(rs.getString("nome"));
						setPopulacao(rs.getLong("populacao"));
						setArea(rs.getDouble("area"));
					} else {
						setId(-1);
						setNome(null);
						setPopulacao(-1);
						setArea(-1);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		}

//e. Crie um método que retorna o país com maior número de habitantes.
		
		public void maiorPopulacao() {
			String sqlMaxPop = "SELECT * FROM paises WHERE populacao = (SELECT MAX(populacao) FROM pais)";
			try (Connection conn = obtemConexao()){
				PreparedStatement stm = conn.prepareStatement(sqlMaxPop);	
				ResultSet rs = stm.executeQuery();
				if(rs.next()) {
					setNome(rs.getString("nome"));
					setPopulacao(rs.getLong("populacao"));
					setId(rs.getInt("id"));
					setArea(rs.getDouble("area"));
				}else {
					System.out.println("Erro");
				}
				
			}catch (SQLException e) {
				System.out.println(e);
			}
		}

	// f. Crie um método que retorna o país com menor área.
		
		public void menorArea() {
			String sqlGet = "SELECT * FROM pais WHERE area = (SELECT Min(area) FROM pais)";
			try (Connection conn = obtemConexao()){
				PreparedStatement stm = conn.prepareStatement(sqlGet);
				ResultSet rs = stm.executeQuery();
				if(rs.next()) {
					setNome(rs.getString    ("nome"));
					setPopulacao(rs.getLong ("populacao"));
					setId(rs.getInt			("id"));
					setArea(rs.getDouble    ("area"));
				}else {
					System.out.println("Erro");
				}
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
	
	// g. Crie um método que retorne um vetor de 3 países.
		
		public String[] vetPaises() {	
			String sqlGet = "SELECT nome FROM paises ORDER BY nome";
			String vet[] = new String[3];
			int cont = 0 ;
			try (Connection conn = obtemConexao()){
				PreparedStatement stm = conn.prepareStatement(sqlGet);
				ResultSet rs = stm.executeQuery();
				while(rs.next() && cont < 3 ) {
					vet[cont] = rs.getString("nome");
					cont ++;
				}
			}catch (SQLException e) {
				System.out.println(e);
			}	
			return vet;
		}
}
