package br.com.itau.web.pages;

import static br.com.itau.web.webdriver.Driver.getDriver;
import static br.com.itau.web.webdriver.Driver.setDriver;

import java.util.List; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import br.com.itau.web.utils.EsperaUtils;

public class HomePage {

	private final String URL= "https://www.petz.com.br"; 
	
	@FindBy(how = How.ID, using = "search")
	private WebElement inputPesquisa;
	
	@FindBy(how = How.CLASS_NAME, using = "liProduct")
	private List<WebElement> itens;	
	
	@FindBy(how = How.CLASS_NAME, using = "button-search")
	private WebElement clicarBotaoBuscar;
	
	@FindBy(how = How.CLASS_NAME, using = "blue")
	private WebElement nomeFornecedor;
	
	@FindBy(how = How.CLASS_NAME, using = "price-current")
	private WebElement precoProduto;
	
	@FindBy(how = How.CLASS_NAME, using = "price-subscriber")
	private WebElement precoAssinante;
	
	@FindBy(how = How.CLASS_NAME, using = "td-resumo")
	private WebElement resumoCarrinhoCompra;

	public HomePage() {
		
        PageFactory.initElements(getDriver(), this);
	}
	
	public void inicializarPagina() {
		getDriver().get(URL);
	    getDriver().manage().window().maximize();
	}
	
	public void buscar(String valor) {
		inputPesquisa.sendKeys(valor);
		clicarBotaoBuscar.click();
	}
	
	public String listarItemBuscado(int item) {

		sleep();

		EsperaUtils.waitPresenceOfElement(getDriver().findElement(By.className("liProduct")), 120);

		EsperaUtils.waitPresenceOfElement(itens.get(item).findElements(By.id("produto-href")).get(0), 60);
		
         return itens.get(item)
        		 .findElements(By.id("produto-href")).get(0).getAttribute("data-nomeproduto");
	}
	
	public void clicarItemSelecionado(int item) {
		
		itens.get(item)
		 .findElements(By.tagName("a")).get(1).click();
	}

	public void sleep() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String nomeFornecedor () {

		EsperaUtils.waitPresenceOfElement(nomeFornecedor, 120);

		return nomeFornecedor.getText();
	}
	
	public String precoProduto() {
		
		return precoProduto.findElement(By.tagName("meta")).getAttribute("content");
	}
	
	public String precoAssinante() {
		return precoAssinante.getText();
	}
	
	public String ProdutoCamposCarrinhoDeCOmpra() {
		
		 return resumoCarrinhoCompra.findElement(By.tagName("a")).getText();
	}
	
	public void finalizar() {
	
		if (getDriver() != null) {
			getDriver().quit();
			setDriver(null);
		}
	}
}
