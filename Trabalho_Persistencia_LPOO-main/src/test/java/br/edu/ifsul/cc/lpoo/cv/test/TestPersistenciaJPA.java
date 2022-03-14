/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.test;

import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJPA;
import javax.swing.JOptionPane;
import org.junit.Test;

/**
 *
 * @author marin
 */
public class TestPersistenciaJPA {

    @Test
    ///Teste para verificar a conexao e criação automática de tabelas
    public void testarConexao() throws Exception {///Teste de Conexão            
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if (persistencia.conexaoAberta()) {
            JOptionPane.showMessageDialog(null, "Conexao com o BD aberta utilizando JPA");
            persistencia.fecharConexao();
        } else {
            JOptionPane.showMessageDialog(null, "Não abriu conexao via jpa");
        }
    }
}

