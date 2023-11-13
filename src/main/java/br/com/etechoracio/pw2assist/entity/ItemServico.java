package br.com.etechoracio.pw2assist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.WebProperties;

/*
*
CREATE TABLE TBL_ITEM_SERVICO
(
    ID_ITEM_SERVICO  NUMBER GENERATED ALWAYS AS IDENTITY,
    ID_ORDEM_SERVICO NUMBER NOT NULL,
    ID_SERVICO       NUMBER NOT NULL,
    NR_VALOR         NUMBER(19,4) NOT NULL,
    CONSTRAINT ID_ITEM_SERVICO PRIMARY KEY (ID_ITEM_SERVICO),
    FOREIGN KEY (ID_ORDEM_SERVICO) REFERENCES TBL_ORDEM_SERVICO (ID_ORDEM_SERVICO),
    FOREIGN KEY (ID_SERVICO) REFERENCES TBL_SERVICO (ID_SERVICO)
);
* */
@Getter
@Setter
@Entity
@Table(name="TBL_ITEM_SERVICO")
public class ItemServico {
    @Id
    @Column(name = "ID_ITEM_SERVICO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ORDEM_SERVICO")
    private OrdemServico ordemServico;

    @ManyToOne
    @JoinColumn(name = "ID_SERVICO")
    private Servico servico;

    @Column(name = "NR_VALOR")
    private double valor;
}
