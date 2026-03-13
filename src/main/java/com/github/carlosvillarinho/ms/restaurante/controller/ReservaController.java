package com.github.carlosvillarinho.ms.restaurante.controller;

import com.github.carlosvillarinho.ms.restaurante.dto.ReservasDTO;
import com.github.carlosvillarinho.ms.restaurante.service.ReservasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservasService reservasService;

    //ENCONTRA TODAS AS RESERVAS
    @GetMapping
    public ResponseEntity<List<ReservasDTO>> getAllReservas(){
        List<ReservasDTO> list = reservasService.findAllReservass();
        return ResponseEntity.ok(list);
    }

    //ENCONTRA AS RESERVAS PELO SEU ID CORRESPONDENTE
    @GetMapping("/{id}")
    public ResponseEntity<ReservasDTO> getReservasById(@PathVariable Long id){
        ReservasDTO reservasDTO = reservasService.findReservassById(id);
        return ResponseEntity.ok(reservasDTO);
    }

    //CRIA NOVAS RESERVAS PELO METODO POST NO INSOMINIA
    @PostMapping
    public ResponseEntity<ReservasDTO> createReservas(@RequestBody @Valid ReservasDTO reservasDTO){
        reservasDTO = reservasService.saveReservas(reservasDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(reservasDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(reservasDTO);
    }

    //ATUALIZA INFORMAÇÕES DAS RESERVAS PELO ID DELAS
    @PutMapping("/{id}")
    public ResponseEntity<ReservasDTO> updateReservas(@PathVariable Long id,
                                                              @RequestBody @Valid ReservasDTO reservasDTO){
        reservasDTO = reservasService.updateReservas(id, reservasDTO);
        return ResponseEntity.ok(reservasDTO);
    }

    //DELETA RESERVAS PELO ID DELAS
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservas(@PathVariable Long id){
        reservasService.deleteReservasById(id);
        return ResponseEntity.noContent().build();
    }
}
