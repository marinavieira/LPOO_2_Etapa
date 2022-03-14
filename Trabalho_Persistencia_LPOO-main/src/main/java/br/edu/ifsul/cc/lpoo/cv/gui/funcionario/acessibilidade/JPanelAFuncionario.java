/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.gui.funcionario.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author marina.ferreira
 */
public class JPanelAFuncionario extends JPanel {

    private CardLayout cardLayout;
    private Controle controle;
    JPanelAFuncionarioFormulario formulario;
    JPanelAFuncionarioListagem listagem;

    public JPanelAFuncionario(Controle controle) {
        this.controle = controle;
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        formulario = new JPanelAFuncionarioFormulario(this, controle);
        listagem = new JPanelAFuncionarioListagem(this, controle);
        this.add(getFormulario(), "tela_funcionario_formulario");
        this.add(listagem, "tela_funcionario_listagem");

    }

    public void showTela(String nomeTela) {
        if (nomeTela.equals("tela_funcionario_listagem")) {
            listagem.populaTable();
        } else if (nomeTela.equals("tela_funcionario_formulario")) {
            ///getFormulario().populaComboEndereco();
        }
        cardLayout.show(this, nomeTela);
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }

    /**
     * @return the formulario
     */
    public JPanelAFuncionarioFormulario getFormulario() {
        return formulario;
    }
}
