/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model.dao;

import br.edu.ifsul.cc.lpoo.cv.model.Funcionario;
import br.edu.ifsul.cc.lpoo.cv.model.Pessoa;
import br.edu.ifsul.cc.lpoo.cv.model.Produto;
import br.edu.ifsul.cc.lpoo.cv.model.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author marin
 */
public class PersistenciaJPA implements InterfacePersistencia {

    //parametro: é o nome da unidade de persistencia (Persistence Unit)
    public EntityManagerFactory factory;  //fabrica de gerenciadores de entidades
    public EntityManager entity; //gerenciador de entidades JPA

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_cv_lpoo");
        entity = factory.createEntityManager();
    }

    @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }

    @Override
    public void fecharConexao() {
        entity.close();
    }

    @Override
    public Object find(Class c, Object id) throws Exception {

        return entity.find(c, id); //encontra um determinado registro
    }

    @Override
    public void persist(Object o) throws Exception {
        entity.getTransaction().begin(); // abrir a transacao.
        entity.persist(o); //realiza o insert ou update.
        entity.getTransaction().commit(); //comita a transacao (comando sql)
    }

    @Override
    public void remover(Object o) throws Exception {
        entity.getTransaction().begin(); // abrir a transacao.
        entity.remove(o); //realiza o delete
        entity.getTransaction().commit(); //comita a transacao (comando sql)
    }

    @Override
    public List<Produto> listProdutos() {

        return entity.createNamedQuery("Produto.list_order_by_id_asc").getResultList();
    }

    @Override
    public Pessoa doLogin(String cpf, String senha) throws Exception {
        List<Pessoa> list = entity.createNamedQuery("Pessoa.login").setParameter("paramCPF", cpf).setParameter("paramS", senha).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<Funcionario> listFuncionarios() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Venda> listVendas() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
