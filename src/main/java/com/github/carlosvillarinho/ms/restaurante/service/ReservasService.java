package com.github.carlosvillarinho.ms.restaurante.service;

import com.github.carlosvillarinho.ms.restaurante.dto.ReservasDTO;
import com.github.carlosvillarinho.ms.restaurante.entities.Reservas;
import com.github.carlosvillarinho.ms.restaurante.entities.Restaurantes;
import com.github.carlosvillarinho.ms.restaurante.exceptions.DatabaseException;
import com.github.carlosvillarinho.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.carlosvillarinho.ms.restaurante.repositories.ReservasRepository;
import com.github.carlosvillarinho.ms.restaurante.repositories.RestaurantesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservasService {
    @Autowired
    ReservasRepository reservasRepository;

    @Autowired
    RestaurantesRepository restaurantesRepository;

    //METODOS
    @Transactional(readOnly = true)
    public List<ReservasDTO> findAllReservass(){
        return reservasRepository.findAll().stream().map(ReservasDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ReservasDTO findReservassById(Long id) {
        Reservas reservas = reservasRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new ReservasDTO(reservas);
    }

    @Transactional
    public ReservasDTO saveReservas(ReservasDTO inputDTO) {
        try {
            Reservas reservas = new Reservas();
            mapDtoToReservas(inputDTO, reservas);
            reservas = reservasRepository.save(reservas);
            return new ReservasDTO(reservas);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possivel salvar Reserva. Restaurante inexistente. ID: " +
                    inputDTO.getRestaurantes().getId());
        }

    }

    private void mapDtoToReservas(ReservasDTO inputDTO, Reservas reservas) {
        reservas.setDataReserva(inputDTO.getDataReserva());
        reservas.setNomeCliente(inputDTO.getNomeCliente());
        reservas.setQtdPessoas(inputDTO.getQtdPessoas());

        Restaurantes restaurantes = restaurantesRepository.getReferenceById(
                inputDTO.getRestaurantes().getId());

        reservas.setRestaurantes(restaurantes);
    }

    @Transactional
    public ReservasDTO updateReservas(Long id, ReservasDTO reservasDTO) {
        try {
            Reservas reservas = reservasRepository.getReferenceById(id);
            mapDtoToReservas(reservasDTO, reservas);
            reservas = reservasRepository.save(reservas);
            return new ReservasDTO(reservas);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteReservasById(Long id){
        if(!reservasRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        reservasRepository.deleteById(id);
    }
}
