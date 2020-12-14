package br.com.itau.web.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import br.com.itau.web.pages.CarrinhoDeCompraPage;
import br.com.itau.web.pages.HomePage;
import br.com.itau.web.utils.Utils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class ProdutoStep {

	private  String dataFormatada;
	private  String diretorio;
	
	private static final Logger LOGGER = Logger.getLogger("br.com.itau.web.steps.ProdutoStep"); 

	@Before
	public void inicio() {
	    LocalDateTime minhaDataObj = LocalDateTime.now();  
	    
	    DateTimeFormatter meuFormatoObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	    
	    diretorio = System.getProperty("user.dir");
	    
	    dataFormatada = minhaDataObj.format(meuFormatoObj);

		File file = new File(diretorio+"/"+dataFormatada); 
		file.mkdir();
	}
	
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
		Utils.printScreen(diretorio+"/"+dataFormatada+"/busca.png");
		LOGGER.info("Buscando elementos");
	}

	// 2) Selecione o terceiro produto da lista retornada.

	@Quando("listar o item desejado")
	public void listarOItemDesejado() {
		assertEquals(homePage.listarItemBuscado(2), "Ração Royal Canin Maxi - Cães Adultos - 15kg");
		Utils.printScreen(diretorio+"/"+dataFormatada+"/itens.png");
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
		Utils.printScreen(diretorio+"/"+dataFormatada+"/carrinho.png");
		LOGGER.info("Inserindo produto no carrinho");

	}

	@After
	public void finalizar() {
		homePage.finalizar();
	}
}
