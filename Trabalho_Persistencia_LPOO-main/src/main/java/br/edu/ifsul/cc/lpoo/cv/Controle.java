/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv;

import br.edu.ifsul.cc.lpoo.cv.gui.JFramePrincipal;
import br.edu.ifsul.cc.lpoo.cv.gui.JMenuBarHome;
import br.edu.ifsul.cc.lpoo.cv.gui.JPanelHome;
import br.edu.ifsul.cc.lpoo.cv.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cc.lpoo.cv.gui.funcionario.JPanelFuncionario;
import br.edu.ifsul.cc.lpoo.cv.gui.funcionario.acessibilidade.JPanelAFuncionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author marina.ferreira
 */
public class Controle {

    private PersistenciaJDBC conexaoJDBC;
    private JFramePrincipal frame;///frame principal da minha aplicação gráfica
    private JPanelAutenticacao pnlAutenticacao;///painel para a autenticacao do funcionario.
    private JMenuBarHome menuBar;///menu principal
    private JPanelHome pnlHome;/// painel de boas vindas (home)
    private JPanelFuncionario pnlFuncionario;/// painel de manutencao para funcionario.
    private JPanelAFuncionario pnlAFuncionario;///painel manutenção funcionario

    ///construtor.
    public Controle() {

    }

    public boolean conectarBD() throws Exception {
        conexaoJDBC = new PersistenciaJDBC();///nova conexão jdbc
        if (conexaoJDBC != null) {///se não for nula
            return conexaoJDBC.conexaoAberta();///retorna aberta
        }///caso contrario
        return false;
    }

    public void fecharBD() {
        System.out.println("Fechando conexao com o Banco de Dados!");
        conexaoJDBC.fecharConexao();
    }

    public void initComponents() {
        //inicia a interface gráfica.
        //"caminho feliz" : passo 5 
        frame = new JFramePrincipal();
        pnlAutenticacao = new JPanelAutenticacao(this);
        menuBar = new JMenuBarHome(this);
        pnlHome = new JPanelHome(this);
        pnlFuncionario = new JPanelFuncionario(this);
        pnlAFuncionario = new JPanelAFuncionario(this);
        frame.addTela(pnlAutenticacao, "tela_autenticacao");//carta 1
        frame.addTela(pnlHome, "tela_home");//carta 2
        frame.addTela(pnlAFuncionario, "tela_funcionario_a");//carta 3 - poderia adicionar opcionalmente: pnlJogador
        frame.addTela(pnlFuncionario, "tela_funcionario_design");//carta 3 - poderia adicionar opcionalmente: pnlJogador
        ///frame.addTela(pnlAVenda, "tela_venda_a");
        frame.showTela("tela_autenticacao"); // apreseta a carta cujo nome é "tela_autenticacao"  
        frame.setVisible(true); // torna visível o jframe
    }

    public void autenticar(String cpf, String senha) {
        try {
            Pessoa p = getConexaoJDBC().doLogin(cpf, senha);
            if (p != null) {
                JOptionPane.showMessageDialog(pnlAutenticacao, "Funcionario  "+p.getCpf()+" autenticado com sucesso!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
                frame.setJMenuBar(menuBar);//adiciona o menu de barra no frame
                frame.showTela("tela_home");//muda a tela para o painel de boas vindas (home)
            } else {
                JOptionPane.showMessageDialog(pnlAutenticacao, "Dados inválidos!", "Autenticação", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(pnlAutenticacao, "Erro ao executar a autenticação no Banco de Dados!", "Autenticação", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void showTela(String nomeTela) {
        if (nomeTela.equals("tela_autenticacao")) {
            pnlAutenticacao.cleanForm();
            frame.showTela(nomeTela);
            pnlAutenticacao.requestFocus();
        } else if (nomeTela.equals("tela_funcionario_a")) {
            pnlAFuncionario.showTela("tela_funcionario_listagem");
            frame.showTela(nomeTela);
        } ///else if (nomeTela.equals("tela_venda_a")) {
            ///pnlAVenda.showTela("tela_venda_listagem");
            ///frame.showTela(nomeTela);
        ///}
    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
}
 