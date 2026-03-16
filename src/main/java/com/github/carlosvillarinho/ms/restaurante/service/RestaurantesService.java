package com.github.carlosvillarinho.ms.restaurante.service;

import com.github.carlosvillarinho.ms.restaurante.dto.RestaurantesDTO;
import com.github.carlosvillarinho.ms.restaurante.entities.Restaurantes;
import com.github.carlosvillarinho.ms.restaurante.exceptions.DatabaseException;
import com.github.carlosvillarinho.ms.restaurante.exceptions.ResourceNotFoundException;
import com.github.carlosvillarinho.ms.restaurante.repositories.RestaurantesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantesService {
    @Autowired
    RestaurantesRepository restaurantesRepository;

    //METODOS
    @Transactional(readOnly = true)
    public List<RestaurantesDTO> findAllRestauranes() {
        return restaurantesRepository.findAll().stream().map(RestaurantesDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public RestaurantesDTO findRestauranesById(Long id) {
        Restaurantes restaurantes = restaurantesRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Restaurante não encontrado. ID: " + id)
        );
        return new RestaurantesDTO(restaurantes);
    }

    @Transactional
    public RestaurantesDTO saveRestaurante(RestaurantesDTO inputDTO) {
        Restaurantes restaurantes = new Restaurantes();
        mapDtoToRestaurantes(inputDTO, restaurantes);
        restaurantes = restaurantesRepository.save(restaurantes);
        return new RestaurantesDTO(restaurantes);
    }

    private void mapDtoToRestaurantes(RestaurantesDTO inputDTO, Restaurantes restaurantes) {
        restaurantes.setNome(inputDTO.getNome());
        restaurantes.setEndereco(inputDTO.getEndereco());
        restaurantes.setCidade(inputDTO.getCidade());
        restaurantes.setUf(inputDTO.getUf());
    }

    @Transactional
    public RestaurantesDTO updateRestaurantes(Long id, RestaurantesDTO restaurantesDTO) {
        try {
            Restaurantes restaurantes = restaurantesRepository.getReferenceById(id);
            mapDtoToRestaurantes(restaurantesDTO, restaurantes);
            restaurantes = restaurantesRepository.save(restaurantes);
            return new RestaurantesDTO(restaurantes);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteRestaurantesById(Long id){
        if(!restaurantesRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }

        try {
            restaurantesRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possivel excluir este restaurante. Existem reservas associadas a ele");
        }
    }
}
