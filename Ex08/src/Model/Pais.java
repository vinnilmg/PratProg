package Model;

import java.io.Serializable;

public class Pais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public int id;
	public String nome;
	public long populacao;
	public double area;
	

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

	

}
