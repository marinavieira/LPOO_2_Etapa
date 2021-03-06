/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marin
 */
@Entity
@Table(name = "tb_agenda")
public class Agenda implements Serializable {

    ///Atributos 
    @Id
    @SequenceGenerator(name = "seq_agenda", sequenceName = "seq_agenda_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_agenda", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_inicio;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_fim;

    @Column(nullable = false, length = 100)
    private String observacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoProduto tipoProduto;

    @ManyToOne///associação
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne///associação
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    ///Construtor
    public Agenda() {

    }
    ///Métodos Get e Set

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the data_inicio
     */
    public Calendar getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Calendar data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the data_fim
     */
    public Calendar getData_fim() {
        return data_fim;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Calendar data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * @return the tipoproduto
     */
    public TipoProduto getTipoProduto() {
        return tipoProduto;
    }

    /**
     * @param tipoProduto the tipoproduto to set
     */
    public void setTipoProduto(TipoProduto tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the medico
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * @param medico the medico to set
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

}
