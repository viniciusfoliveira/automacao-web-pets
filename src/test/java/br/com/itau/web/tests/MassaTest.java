package br.com.itau.web.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.After;
import org.junit.Test;

import br.com.itau.web.model.Busca;
import br.com.itau.web.pages.CarrinhoDeCompraPage;
import br.com.itau.web.pages.HomePage;
import gherkin.deps.com.google.gson.Gson;
import gherkin.deps.com.google.gson.JsonIOException;
import gherkin.deps.com.google.gson.JsonSyntaxException;

public class MassaTest {

	private HomePage homePage = new HomePage();
	private CarrinhoDeCompraPage carrinhoCompra = new CarrinhoDeCompraPage();

	@Test
	public void massaTest() throws IllegalAccessException {

		homePage.inicializarPagina();

		String valores[] = { buscar().getValor1(), buscar().getValor2(), buscar().getValor3(), buscar().getValor4(),
				buscar().getValor5(), buscar().getValor6(), buscar().getValor7(), buscar().getValor8(),
				buscar().getValor9(), buscar().getValor10() };
		

		for (int i = 0; i < valores.length; i++) {
			homePage.buscar(valores[i]);

			assertEquals(homePage.listarItemBuscado(2), "Ração Royal Canin Maxi - Cães Adultos - 15kg");

			homePage.clicarItemSelecionado(2);

			assertEquals(homePage.nomeFornecedor(), "Royal Canin");
			assertEquals(homePage.precoProduto(), "232.69");
			assertEquals(homePage.precoAssinante(), "R$ 209,42");

			carrinhoCompra.clicarCarrinhoCompra();
			assertFalse((carrinhoCompra.ProdutoCamposCarrinhoDeCOmpra()
					.equals("Ração Royal Canin Maxi - Cães Adultos - 15kg")));
			assertEquals(carrinhoCompra.PrecoCamposCarrinhoDeCompra(), "R$ 232,69");

		}
	}
	
	@After
	public void finalizar() {
		homePage.finalizar();
	}

	public Busca buscar() throws IllegalAccessException {

		Gson gson = new Gson();

		try {
			Busca busca = gson.fromJson(new FileReader("src/test/resources/json/busca.json"), Busca.class);
			return busca;
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new IllegalAccessException("error");
	}

}
