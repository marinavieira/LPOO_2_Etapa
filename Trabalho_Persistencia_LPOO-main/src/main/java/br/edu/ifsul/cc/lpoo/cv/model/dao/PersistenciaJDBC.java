/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Cliente;
import br.edu.ifsul.cc.lpoo.cv.model.Fornecedor;
import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pagamento;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.Venda;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.TipoProduto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author marin
 */
public class PersistenciaJDBC implements InterfacePersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "postgres";
    public static final String URL = "jdbc:postgresql://localhost:5432/TrabalhoLPOO_2_Etapa";
    private Connection con = null;

    public PersistenciaJDBC() throws Exception {

        Class.forName(DRIVER);
        System.out.println("Tentando estabelecer conexao JDBC com :" + URL + " ...");

        this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);

    }

    @Override
    public Boolean conexaoAberta() {
        try {
            if (con != null) {
                return !con.isClosed();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void fecharConexao() {
        try {
            this.con.close();
            System.out.println("Fechou conexao JDBC");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if (c == Venda.class) {
            //tb_venda
            PreparedStatement ps = this.con.prepareStatement("select id, observacao, valor_total, data, cliente_id, pagamento, funcionario_id from tb_venda where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Venda venda = new Venda();

                Calendar data = Calendar.getInstance();

                //Elementos da venda
                venda.setId(rs.getInt("id"));
                venda.setObservacao(rs.getString("observacao"));
                venda.setValortotal(rs.getFloat("valortotal"));
                data.setTimeInMillis(rs.getDate("data").getTime());
                venda.setData(data);
                venda.setPagamento(("pagamento"));
                venda.setFuncionario((Funcionario) find(Funcionario.class, rs.getInt("funcionario_id")));
                venda.setCliente((Cliente) find(Cliente.class, rs.getInt("cliente_id")));
                return venda;

            }
        } else if (c == Produto.class) {
            PreparedStatement ps = this.con.prepareStatement("select id, nome, valor, quantidade, tipo, fornecedor_id from tb_produto where id = ?");
            ps.setInt(1, Integer.parseInt(id.toString()));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();

                //Elementos do produto
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getFloat("valor"));
                produto.setQuantidade(rs.getFloat("quantidade"));
                produto.setTipo(("tipo"));
                produto.setFornecedor((Fornecedor) find(Fornecedor.class, rs.getInt("fornecedor_id")));
                return produto;
            }
        }

        return null;
    }

    @Override
    public void persist(Object o) throws Exception {

        if (o instanceof Venda) {
            Venda v = (Venda) o;

            if (v.getId() == null) { //inserção

                PreparedStatement ps = this.con.prepareStatement("insert into tb_venda (id, observacao, valor_total, data, pagamento, cliente_id, funcionario_id) values (nextval('seq_venda_id'),?,?,?,?,?,?) returning id");

                ps.setInt(1, v.getId());
                ps.setString(2, v.getObservacao());
                ps.setFloat(3, v.getValortotal());
                ps.setTimestamp(4, new Timestamp(v.getData().getTimeInMillis()));
                ps.setString(5, "pagamento");
                ps.setString(6, v.getCliente().getCpf());
                ps.setString(7, v.getFuncionario().getNumero_pis());
                ps.execute();

            } else { //update
                PreparedStatement ps = this.con.prepareStatement("update tb_venda set "
                        + "observacao = ?, "
                        + "valor_total = ? "
                        + "data = ? "
                        + "pagamento"
                        + "cliente_id = ?"
                        + "funcionario_id = ?"
                        + "where id = ?");

                ps.setString(1, v.getObservacao());
                ps.setFloat(2, v.getValortotal());
                ps.setTimestamp(3, new Timestamp(v.getData().getTimeInMillis()));
                ps.setInt(4, v.getId());
                ps.setString(4, "pagamento");
                ps.setString(5, v.getCliente().getCpf());
                ps.setString(6, v.getFuncionario().getNumero_pis());
                ps.execute();

                ps.execute(); //executa
            }
        } else if (o instanceof Produto) {
            Produto p = (Produto) o;

            if (p.getId() == null) {
                PreparedStatement ps = this.con.prepareStatement("insert into tb_produto (id, nome, valor, quantidade, tipo, fornecedor_id) values (nextval('seq_produto_id'), ?, ?, ?, ?, ?) returning id");

                ps.setInt(1, p.getId());
                ps.setString(2, p.getNome());
                ps.setFloat(3, p.getValor());
                ps.setFloat(4, p.getQuantidade());
                ps.setString(5, "tipo");
                ps.setString(6, p.getFornecedor().getCnpj());

                ps.execute();

            } else {
                //update
                PreparedStatement ps = this.con.prepareStatement("update tb_produto set "
                        + "nome = ?, "
                        + "valor = ? "
                        + "quantidade = ? "
                        + "tipo= ? "
                        + "fornecedor_id = ? "
                        + "where id = ?");

                ps.setString(1, p.getNome());
                ps.setFloat(2, p.getValor());
                ps.setFloat(3, p.getQuantidade());
                ps.setString(4, "tipo");
                ps.setString(5, p.getFornecedor().getCnpj());
                ps.setInt(6, p.getId());
                ps.execute();

                ps.execute();//executa o comando.
            }
        }
    }

    @Override
    public void remover(Object o) throws Exception {
        if (o instanceof Venda) {

            Venda v = (Venda) o;

            PreparedStatement ps = this.con.prepareStatement("delete from tb_venda where id = ?");
            ps.setInt(1, v.getId());
            ps.execute();

        } else if (o instanceof Produto) {
            Produto p = (Produto) o;
            PreparedStatement ps = this.con.prepareStatement("delete from tb_produto where id = ?");
            ps.setInt(1, p.getId());
            ps.execute();
        }
    }

    @Override
    public List<Produto> listProdutos() throws Exception {

        List<Produto> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id, nome, valor, quantidade, tipo, fornecedor_id from tb_produto order by id asc");

        ResultSet rs = ps.executeQuery();//executa a query        

        lista = new ArrayList();
        while (rs.next()) {

            Produto p = new Produto();
            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getFloat("valor"));
            p.setQuantidade(rs.getFloat("quantidade"));
            p.setTipo(TipoProduto.MEDICAMENTO);
            p.setFornecedor((Fornecedor) find(Fornecedor.class, rs.getInt("fornecedor_id")));

            lista.add(p);//adiciona na lista o objetivo que contem as informações de um determinada linha do ResultSet.

        }
        return lista;
    }

    public Pessoa doLogin(String cpf, String senha) throws SQLException {
        Pessoa p = null;

        PreparedStatement ps
                = this.con.prepareStatement("select p.cpf, p.senha from tb_pessoa p where p.nome= ? and p.senha = ? ");

        ps.setString(1, cpf);
        ps.setString(2, senha);

        ResultSet rs = ps.executeQuery();//o ponteiro do REsultSet inicialmente está na linha -1

        if (rs.next()) {//se a matriz (ResultSet) tem uma linha

            p = new Pessoa();
            p.setCpf(rs.getString("cpf"));
            p.setSenha(rs.getString("senha"));
        }

        ps.close();
        return p;
    }

    @Override
    public List<Funcionario> listFuncionarios() throws Exception {

        List<Funcionario> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select f.cpf,f.numero_ctps,f.numero_pis from tb_funcionario as f");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList();
        while (rs.next()) {

            Funcionario f = new Funcionario();

            f.setCpf(rs.getString("CPF"));
            f.setNumero_ctps(rs.getString("numero_ctps"));
            f.setNumero_pis(rs.getString("numero_pis"));

            lista.add(f);
        }
        return lista;
    }

    @Override
    public List<Venda> listVendas() throws Exception {

        List<Venda> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select id,observacao,pagamento,valortotal,cliente_id,funcionario_id from tb_venda");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList();
        while (rs.next()) {

            Venda v = new Venda();

            v.setId(rs.getInt("id"));
            v.setObservacao(rs.getString("observaco"));
            v.setPagamento(Pagamento.DINHEIRO);
            v.setValortotal(rs.getFloat("valortotal"));
            v.setCliente((Cliente) find(Cliente.class, rs.getInt("cliente_id")));
            v.setFuncionario((Funcionario) find(Funcionario.class, rs.getInt("funcionario_id")));
            lista.add(v);
        }
        return lista;
    }

    public List<Cliente> listClientes() throws Exception {

        List<Cliente> lista = null;

        PreparedStatement ps = this.con.prepareStatement("select c.cpf,p.nome,c.data_ultima_visita from tb_cliente as c,tb_pessoa as p where p.cpf = c.cpf order by p.nome asc");

        ResultSet rs = ps.executeQuery();

        lista = new ArrayList();
        while (rs.next()) {

            Cliente c = new Cliente();

            c.setCpf(rs.getString("cpf"));
            Calendar dtuv = Calendar.getInstance();
            dtuv.setTimeInMillis(rs.getDate("data_ultima_visita").getTime());
            c.setData_ultima_visita(dtuv);
            c.setNome(rs.getString("nome"));
            lista.add(c);
        }
        return lista;
    }

}
