/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author marin
 */
public class TestePersistenciaJDBC {

//@Test
    public void testConexao() throws Exception {
        PersistenciaJDBC persistenciaJDBC = new PersistenciaJDBC();
        if (persistenciaJDBC.conexaoAberta()) {
            System.out.println("Abriu conexao com BD via JDBC");
            persistenciaJDBC.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }

    @Test
    public void testListPersistenciaProduto() throws Exception {

        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {

            List<Produto> lista = persistencia.listProdutos();

            if (!lista.isEmpty()) {

                for (Produto p : lista) {

                    System.out.println("Id Produto: " + p.getId() + " Nome: " + p.getNome() + " Valor: " + p.getValor() + " Quantidade: " + p.getQuantidade());

                    persistencia.remover(p);
                }
            } else {

                System.out.println("Não encontrou o produto");

                Produto p = new Produto();
                p.setNome("Caixa de Areia");
                p.setValor(Float.parseFloat("20.50"));
                p.setQuantidade(Float.parseFloat("5"));
                persistencia.persist(p); //insert na tabela.                
                System.out.println("Cadastrou o produto " + p.getId());

                p = new Produto();//reset com a nova instancia que é gerada aqui.
                p.setNome("Casinha");
                p.setValor(Float.parseFloat("90"));
                p.setQuantidade(Float.parseFloat("2"));

                persistencia.persist(p); //insert na tabela.
                System.out.println("Cadastrou o produto " + p.getId());

            }

            persistencia.fecharConexao();
        } else {
            System.out.println("Nao abriu a conexao com o BD via JDBC");
        }
    }
}
