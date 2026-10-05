package com.ejemplo.inventario.repository;

import com.ejemplo.inventario.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByBodegaId(Long bodegaId);
    List<Stock> findByProductoId(Long productoId);
}
