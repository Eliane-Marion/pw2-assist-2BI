package br.com.etechoracio.pw2assist.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_TECNICO")
public class Tecnico extends Pessoa {

    @Id
    @Column(name = "ID_TECNICO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
