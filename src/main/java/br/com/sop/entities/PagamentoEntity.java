package br.com.sop.entities;

import javax.persistence.*;

@Entity(name = "PAGAMENTO")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGAMENTO_SEQ")
    @SequenceGenerator(name = "PAGAMENTO_SEQ", sequenceName = "PAGAMENTO_SEQ", allocationSize = 1)
    private Integer id_pagamento;
}
