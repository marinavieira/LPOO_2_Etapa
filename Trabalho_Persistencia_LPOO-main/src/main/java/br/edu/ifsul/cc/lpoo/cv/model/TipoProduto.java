/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.cc.lpoo.cv.model;

/**
 *
 * @author marin
 */
public enum TipoProduto {
    MEDICAMENTO, SESSAO_FISIOTERAPIA, SESSAO_ADESTRAMENTO, ATENDIMENTO_AMBULATORIAL, CONSULTA, CONSULTA_REVISAO;

    public static TipoProduto getTipoproduto(String nameEnum) {
        if (nameEnum.equals(TipoProduto.MEDICAMENTO.toString())) {
            return TipoProduto.MEDICAMENTO;
        } else if (nameEnum.equals(TipoProduto.SESSAO_FISIOTERAPIA.toString())) {
            return TipoProduto.SESSAO_FISIOTERAPIA;
        } else if (nameEnum.equals(TipoProduto.SESSAO_ADESTRAMENTO.toString())) {
            return TipoProduto.SESSAO_ADESTRAMENTO;
        } else if (nameEnum.equals(TipoProduto.ATENDIMENTO_AMBULATORIAL.toString())) {
            return TipoProduto.ATENDIMENTO_AMBULATORIAL;
        } else if (nameEnum.equals(TipoProduto.CONSULTA.toString())) {
            return TipoProduto.CONSULTA;
        } else if (nameEnum.equals(TipoProduto.CONSULTA_REVISAO.toString())) {
            return TipoProduto.CONSULTA_REVISAO;
        } else {
            return null;
        }

    }
}
