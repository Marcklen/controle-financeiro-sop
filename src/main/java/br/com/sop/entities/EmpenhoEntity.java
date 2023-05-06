package br.com.sop.entities;

import javax.persistence.*;

@Entity(name = "EMPENHO")
public class EmpenhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPENHO_SEQ")
    @SequenceGenerator(name = "EMPENHO_SEQ", sequenceName = "EMPENHO_SEQ", allocationSize = 1)
    private Integer id_empenho;
}
