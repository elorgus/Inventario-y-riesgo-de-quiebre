package com.ejemplo.inventario.dto;

public record RiesgoQuiebreDTO(
    Long productoId,
    String sku,
    String producto,
    Long bodegaId,
    String bodega,
    Integer stockActual,
    Integer stockMinimo,
    Double diasHastaQuiebre,
    String nivel
) {}
