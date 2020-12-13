package br.com.itau.web.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import br.com.itau.web.pages.CarrinhoDeCompraPage;
import br.com.itau.web.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class ProdutoStep {

	private HomePage homePage = new HomePage();
	private CarrinhoDeCompraPage carrinhoCompra = new CarrinhoDeCompraPage();

	@Dado("que estou na aplicacao")
	public void queEstouNaAplicacao() {
		homePage.inicializarPagina();
	}

	// 1) Consulte por "Ração"
	@Dado("buscar pelo item {string}")
	public void buscarPeloItem(String nome) {
		homePage.buscar(nome);
	}

	// 2) Selecione o terceiro produto da lista retornada.

	@Quando("listar o item desejado")
	public void listarOItemDesejado() {
		assertEquals(homePage.listarItemBuscado(2), "Ração Royal Canin Maxi - Cães Adultos - 15kg");
	}

	// 3) Valide o nome do produto, fornecedor, preço normal e preço para assinante

	@Entao("validar as iformacoes necessarias")
	public void validarAsIformacoesNecessarias() {
		homePage.clicarItemSelecionado(2);
		assertEquals(homePage.nomeFornecedor(), "Royal Canin");
		assertEquals(homePage.precoProduto(), "232.69");
		assertEquals(homePage.precoAssinante(), "R$ 209,42");
	}

	// 4) Insira o produto no carrinho de compras
	// 5) Valide se os dados do item 3 continuam idênticos

	@Entao("inserir item no carrinho de compras")
	public void inserirItemNoCarrinhoDeCompras() {
		carrinhoCompra.clicarCarrinhoCompra();
		assertFalse((carrinhoCompra.ProdutoCamposCarrinhoDeCOmpra()
				.equals("Ração Royal Canin Maxi - Cães Adultos - 15kg")));
		assertEquals(carrinhoCompra.PrecoCamposCarrinhoDeCompra(), "R$ 232,69");
		
	}


	@After
	public void finalizar() {
		homePage.finalizar();
	}
}
