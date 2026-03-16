package com.github.carlosvillarinho.ms.restaurante.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "tb_reservas")
public class Reservas {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataReserva;
    private String nomeCliente;
    private Integer qtdPessoas;

    //RELAÇÃO
    @ManyToOne
    @JoinColumn(name = "restaurantes_id", nullable = false)
    private Restaurantes restaurantes;
}
