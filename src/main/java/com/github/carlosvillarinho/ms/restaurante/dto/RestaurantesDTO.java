package com.github.carlosvillarinho.ms.restaurante.dto;

import com.github.carlosvillarinho.ms.restaurante.entities.Restaurantes;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RestaurantesDTO {
    //ATRIBUTOS
    private Long id;

    @NotBlank(message = "Campo nome é requerido")
    @Size(min = 5, max = 120, message = "O nome deve ter entre 5 e 120 caracteres")
    private String nome;

    @NotBlank(message = "Campo endereço é requerido")
    @Size(min = 5, max = 120, message = "O endereço deve ter entre 5 e 120 caracteres")
    private String endereco;

    @NotBlank(message = "Campo cidade é requerido")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String cidade;

    @NotBlank(message = "Campo uf é requerido")
    @Size(min = 2, max = 2, message = "O uf deve ter no máximo 2 caracteres")
    private String uf;

    public RestaurantesDTO(Restaurantes restaurantes) {
        id = restaurantes.getId();
        nome = restaurantes.getNome();
        endereco = restaurantes.getEndereco();
        cidade = restaurantes.getCidade();
        uf = restaurantes.getUf();
    }
}
