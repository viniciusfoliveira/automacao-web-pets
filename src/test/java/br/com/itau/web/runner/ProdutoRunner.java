package br.com.itau.web.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
	      features = "src/test/resources/features/produtos.feature",
	      glue = "br.com.itau.web.steps",
	      plugin = {"pretty",  "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm"},
	      strict = true,
	      snippets = SnippetType.CAMELCASE 
)
public class ProdutoRunner {

}
