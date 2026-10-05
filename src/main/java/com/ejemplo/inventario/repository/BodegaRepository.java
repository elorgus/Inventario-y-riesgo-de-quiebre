package com.ejemplo.inventario.repository;

import com.ejemplo.inventario.entity.Bodega;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodegaRepository extends JpaRepository<Bodega, Long> {
}
