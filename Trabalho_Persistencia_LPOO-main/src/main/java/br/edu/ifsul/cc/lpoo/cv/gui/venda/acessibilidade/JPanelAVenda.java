/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.gui.venda.acessibilidade;

import br.edu.ifsul.cc.lpoo.cv.Controle;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author marina.ferreira
 */
class JPanelAVenda extends JPanel {

    private CardLayout cardLayout;
    private Controle controle;
    JPanelAVendaFormulario formulariovenda;
    JPanelAVendaListagem listagemvenda;

    public JPanelAVenda(Controle controle) {
        this.controle = controle;
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        formulariovenda = new JPanelAVendaFormulario(this, controle);
        listagemvenda = new JPanelAVendaListagem(this, controle);
        this.add(getFormulario(), "tela_venda_formulario");
        this.add(listagemvenda, "tela_venda_listagem");
    }

    public void showTela(String nomeTela) {
        if (nomeTela.equals("tela_venda_listagem")) {
            listagemvenda.populaTable();
        } else if (nomeTela.equals("tela_venda_formulario")) {
            getFormulario();
        }
        cardLayout.show(this, nomeTela);
    }

    public Controle getControle() {
        return controle;
    }

    public JPanelAVendaFormulario getFormulario() {
        return formulariovenda;
    }
}
