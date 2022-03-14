/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.gui.funcionario;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;



/**
 *
 * @author marina.ferreira
 */
public class JPanelFuncionario extends JPanel {

    private CardLayout cardLayout;
    private final Controle controle;
    private JPanelFuncionarioListagem lista;
        private JPanelFuncionarioFormulario formulario;

    public JPanelFuncionario(Controle controle) {

        this.controle = controle;
        initComponents();
    }

    private void initComponents() {

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        
        lista = new JPanelFuncionarioListagem(this, controle);
        formulario = new JPanelFuncionarioFormulario(this, controle);  
        
        this.add(lista, "tela_funcionario_listagem");
        this.add(getFormulario(), "tela_funcionario_formulario");

    }

    public void showTela(String nomeTela) {
         if(nomeTela.equals("tela_funcionario_listagem")){           
            lista.populaTable();         
        }
        cardLayout.show(this, nomeTela);
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }

    public JPanelFuncionarioFormulario getFormulario() {
        return formulario;
    }

}
