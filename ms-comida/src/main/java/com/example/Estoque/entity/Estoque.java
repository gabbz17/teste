package com.example.Estoque.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter  @Setter  @AllArgsConstructor  @NoArgsConstructor  @EqualsAndHashCode(of = "id")
@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private Integer qtnd;
    @Column(nullable = false)
    private BigDecimal valor;
}
