package com.ejemplo.inventario.dto;

public record StockDTO(
    Long id,
    Long productoId,
    String sku,
    String producto,
    Long bodegaId,
    String bodega,
    Integer cantidad
) {}
