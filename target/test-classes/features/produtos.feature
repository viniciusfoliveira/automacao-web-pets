#language: pt

Funcionalidade: Controle Petz
            Eu como usuario da aplicacao
            Gostaria de buscar e inserir novos produtos
            Para que eu consiga efetuar a compra
            
Cenario: Validar 3 item na lista de pesquisa

        Dado que estou na aplicacao
        E buscar pelo item "Ração"
        Quando listar o item desejado 
        Entao validar as iformacoes necessarias
        E inserir item no carrinho de compras
 
      
