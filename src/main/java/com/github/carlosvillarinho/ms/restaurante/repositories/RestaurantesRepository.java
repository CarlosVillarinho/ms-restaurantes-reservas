package com.github.carlosvillarinho.ms.restaurante.repositories;

import com.github.carlosvillarinho.ms.restaurante.entities.Restaurantes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantesRepository extends JpaRepository<Restaurantes, Long> {
}
