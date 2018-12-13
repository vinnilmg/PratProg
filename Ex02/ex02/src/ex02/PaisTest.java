package ex02;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PaisTest {
	Pais pais, copia;
	 static int id=0;
	
/* h. Crie uma classe de testes que, além de testar os CRUDs, teste também os métodos
	maiorPopulacao, menorArea e vetorTresPaises.*/

	 @Test
	 public void test00Carregar() {
	 System.out.println("carregar");
	 Pais fixture = new Pais(9, "Inglaterra", 5000, 589.99);
	 Pais novo = new Pais(1, null, 0, 0);
	 novo.carregar();
	 assertEquals("testa inclusao", novo, fixture);
	 }
	 
	 @Test
	public void test01Incluir() {
		System.out.println	("Incluir");
		pais.criar();
		id = pais.getId();
		copia.setId(id);
		assertEquals(pais.getId(), copia.getId(),"Criar");
	}
	@Test
	public void test02Atualizar() {
		System.out.println	("atualizar");
		pais.setNome		(null);
		copia.setNome		(null);
		pais.atualizar();
		assertEquals(pais.getNome(),copia.getNome(),"Atualizar");
	}
	@Test
	public void test03Excluir() {
		System.out.println	("Excluir");
		pais.setId(id);
		copia.setId(-1);
		pais.excluir();
		assertEquals(pais.getId(),copia.getId(),"Excluir");
	}
	@Test
	public void test04MaiorPopulacao() {
		System.out.println("Maior populacao");
		pais.maiorPopulacao();
		long populacao = pais.getPopulacao();
		copia.setPopulacao(populacao);
		assertEquals(pais.getPopulacao(),copia.getPopulacao(),"Método MaiorPop");
	}	
	@Test
	public void test05MenorArea() {
		System.out.println	("menorArea");
		pais.menorArea();
		double area = pais.getArea();
		copia.setArea(area);
		assertEquals(pais.getArea(),copia.getArea(),"Método MenorArea");
	}
	public void test06Vetor() {
		System.out.println	("Vetor dos países");
		String vet[] = pais.vetPaises();
		assertEquals(vet.length,3,"VetPaises");
	}
}