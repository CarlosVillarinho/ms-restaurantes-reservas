package com.github.carlosvillarinho.ms.restaurante.controller;

import com.github.carlosvillarinho.ms.restaurante.dto.RestaurantesDTO;
import com.github.carlosvillarinho.ms.restaurante.service.RestaurantesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestaurantesService restaurantesService;

    //ENCONTRA TODOS OS RESTAURANTES
    @GetMapping
    public ResponseEntity<List<RestaurantesDTO>> getAllRestaurantes(){
        List<RestaurantesDTO> list = restaurantesService.findAllRestauranes();
        return ResponseEntity.ok(list);
    }

    //ENCONTRA OS RESTAURANTES PELO SEU ID CORRESPONDENTE
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantesDTO> getRestaurantesById(@PathVariable Long id){
       RestaurantesDTO restaurantesDTO = restaurantesService.findRestauranesById(id);
        return ResponseEntity.ok(restaurantesDTO);
    }

    //CRIA NOVOS RESTAURANTES PELO METODO POST NO INSOMINIA
    @PostMapping
    public ResponseEntity<RestaurantesDTO> createRestaurantes(@RequestBody @Valid RestaurantesDTO restaurantesDTO){
        restaurantesDTO = restaurantesService.saveRestaurante(restaurantesDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(restaurantesDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(restaurantesDTO);
    }

    //ATUALIZA INFORMAÇÕES DOS RESTAURANTES PELO ID DELES
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantesDTO> updateRestaurantes(@PathVariable Long id,
                                                              @RequestBody @Valid RestaurantesDTO restaurantesDTO){
       restaurantesDTO = restaurantesService.updateRestaurantes(id, restaurantesDTO);
        return ResponseEntity.ok(restaurantesDTO);
    }

    //DELETA RESTAURANTES PELO ID DELES
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantes(@PathVariable Long id){
        restaurantesService.deleteRestaurantesById(id);
        return ResponseEntity.noContent().build();
    }
}
