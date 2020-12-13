package br.com.itau.web.suits;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.itau.web.runner.ProdutoRunner;
import br.com.itau.web.tests.MassaTest;


@RunWith(Suite.class)
@SuiteClasses({ ProdutoRunner.class, MassaTest.class})
public class SuitTest {

}
