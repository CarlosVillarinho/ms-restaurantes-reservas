package com.github.carlosvillarinho.ms.restaurante.service;

import com.github.carlosvillarinho.ms.restaurante.dto.ReservasDTO;
import com.github.carlosvillarinho.ms.restaurante.dto.RestaurantesDTO;
import com.github.carlosvillarinho.ms.restaurante.entities.Reservas;
import com.github.carlosvillarinho.ms.restaurante.entities.Restaurantes;
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

//    @Transactional
//    public RestaurantesDTO saveRestaurante(RestaurantesDTO restaurantesDTO) {
//        Restaurantes restaurantes = new Restaurantes();
//        copyDtoToRestaurantes(restaurantesDTO, restaurantes);
//        restaurantes = restaurantesRepository.save(restaurantes);
//        return new RestaurantesDTO(restaurantes);
//    }
//
//    private void copyDtoToRestaurantes(RestaurantesDTO restaurantesDTO, Restaurantes restaurantes) {
//        restaurantes.setNome(restaurantesDTO.getNome());
//        restaurantes.setEndereco(restaurantesDTO.getEndereco());
//        restaurantes.setCidade(restaurantesDTO.getCidade());
//        restaurantes.setUf(restaurantesDTO.getUf());
//    }
//
//    @Transactional
//    public RestaurantesDTO updateRestaurantes(Long id, RestaurantesDTO restaurantesDTO) {
//        try {
//            Restaurantes restaurantes = restaurantesRepository.getReferenceById(id);
//            copyDtoToRestaurantes(restaurantesDTO, restaurantes);
//            restaurantes = restaurantesRepository.save(restaurantes);
//            return new RestaurantesDTO(restaurantes);
//        } catch (EntityNotFoundException e) {
//            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
//        }
//    }
//
//    @Transactional
//    public void deleteRestaurantesById(Long id){
//        if(!restaurantesRepository.existsById(id)){
//            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
//        }
//        restaurantesRepository.deleteById(id);
//    }
}
