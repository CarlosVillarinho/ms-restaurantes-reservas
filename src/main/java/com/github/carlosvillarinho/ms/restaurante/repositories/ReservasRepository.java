package com.github.carlosvillarinho.ms.restaurante.repositories;

import com.github.carlosvillarinho.ms.restaurante.entities.Reservas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasRepository extends JpaRepository<Reservas, Long> {
}
