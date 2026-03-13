package com.github.carlosvillarinho.ms.restaurante.service;

import com.github.carlosvillarinho.ms.restaurante.dto.ReservasDTO;
import com.github.carlosvillarinho.ms.restaurante.entities.Reservas;
import com.github.carlosvillarinho.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.carlosvillarinho.ms.restaurante.repositories.ReservasRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservasService {
    @Autowired
    ReservasRepository reservasRepository;

    //METODOS
    @Transactional(readOnly = true)
    public List<ReservasDTO> findAllReservass() {
        List<Reservas> reservas = reservasRepository.findAll();
        return reservas.stream().map(ReservasDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ReservasDTO findReservassById(Long id) {
        Reservas reservas = reservasRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Restaurante não encontrado. ID: " + id)
        );
        return new ReservasDTO(reservas);
    }

    @Transactional
    public ReservasDTO saveReservas(ReservasDTO reservasDTO) {
        Reservas reservas = new Reservas();
        copyDtoToReservas(reservasDTO, reservas);
        reservas = reservasRepository.save(reservas);
        return new ReservasDTO(reservas);
    }

    private void copyDtoToReservas(ReservasDTO reservasDTO, Reservas reservas) {
        reservas.setDataReserva(reservasDTO.getDataReserva());
        reservas.setNomeCliente(reservasDTO.getNomeCliente());
        reservas.setQtdPessoas(reservasDTO.getQtdPessoas());
    }

    @Transactional
    public ReservasDTO updateReservas(Long id, ReservasDTO reservasDTO) {
        try {
            Reservas reservas = reservasRepository.getReferenceById(id);
            copyDtoToReservas(reservasDTO, reservas);
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
