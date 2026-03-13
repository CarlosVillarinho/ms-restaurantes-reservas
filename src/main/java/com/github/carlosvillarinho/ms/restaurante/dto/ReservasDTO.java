package com.github.carlosvillarinho.ms.restaurante.dto;

import com.github.carlosvillarinho.ms.restaurante.entities.Reservas;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservasDTO {
    //ATRIBUTOS
    private Long id;

    @NotNull(message = "Campo data é requerido")
    @FutureOrPresent(message = "Data da reserva deve ser atual ou futura")
    private LocalDate dataReserva;

    @NotBlank(message = "Campo nome é requerido")
    @Size(min = 3, max = 130, message = "O nome deve ter entre 5 e 120 caracteres")
    private String nomeCliente;

    @NotNull(message = "Campo qtdPessoas é requerido")
    @Positive(message = "Quantidade de pessoa deve ser um numero inteiro maior que zero")
    private Integer qtdPessoas;

//    @NotNull(message = "Campo restaurante é requerido")
//    private RestaurantesDTO restaurantes;

    public ReservasDTO(Reservas reservas) {
        id = reservas.getId();
        dataReserva = reservas.getDataReserva();
        nomeCliente = reservas.getNomeCliente();
        qtdPessoas = reservas.getQtdPessoas();
//        restaurantes = new RestaurantesDTO();
    }
}
