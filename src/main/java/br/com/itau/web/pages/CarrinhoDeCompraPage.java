package br.com.itau.web.pages;

import static br.com.itau.web.webdriver.Driver.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import br.com.itau.web.utils.Utils;

public class CarrinhoDeCompraPage {

	@FindBy(how = How.CLASS_NAME, using = "td-resumo")
	private WebElement resumoCarrinhoCompra;
		
	@FindBy(how = How.ID, using = "adicionarAoCarrinho")
	private WebElement clicarBotaoCompra;
	
	@FindBy(how = How.XPATH, using = "//tr/td[@class='preco']")
	private WebElement precoCarrinho;
	
	public CarrinhoDeCompraPage() {
        PageFactory.initElements(getDriver(), this);
	}

	public String ProdutoCamposCarrinhoDeCOmpra() {
		
		Utils.waitPresenceOfElement(resumoCarrinhoCompra, 120);
		
		Utils.waitPresenceOfElement(resumoCarrinhoCompra.findElement(By.tagName("a")), 120);
		
		return resumoCarrinhoCompra.findElement(By.tagName("a")).getText();
	}
	
	public String PrecoCamposCarrinhoDeCompra() {

		return precoCarrinho.getText();
	}
	
	public void clicarCarrinhoCompra() {
		clicarBotaoCompra.click();
	}
	
}
