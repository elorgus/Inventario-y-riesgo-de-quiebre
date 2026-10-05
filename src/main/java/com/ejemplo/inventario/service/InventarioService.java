package com.ejemplo.inventario.service;

import com.ejemplo.inventario.dto.RiesgoQuiebreDTO;
import com.ejemplo.inventario.entity.Stock;
import com.ejemplo.inventario.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventarioService {

    private final StockRepository stockRepository;

    public InventarioService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<RiesgoQuiebreDTO> calcularRiesgos() {
        List<RiesgoQuiebreDTO> resultado = new ArrayList<>();
        for (Stock s : stockRepository.findAll()) {
            Double dias = null;
            BigDecimal consumo = s.getProducto().getConsumoDiarioPromedio();
            if (consumo != null && consumo.compareTo(BigDecimal.ZERO) > 0) {
                dias = BigDecimal.valueOf(s.getCantidad())
                        .divide(consumo, 2, RoundingMode.HALF_UP)
                        .doubleValue();
            }

            String nivel = "OK";
            if (s.getCantidad() <= s.getProducto().getStockMinimo()) {
                nivel = "CRITICO";
            } else if (dias != null && dias < 3) {
                nivel = "ALTO";
            } else if (dias != null && dias < 7) {
                nivel = "MEDIO";
            }

            resultado.add(new RiesgoQuiebreDTO(
                s.getProducto().getId(),
                s.getProducto().getSku(),
                s.getProducto().getNombre(),
                s.getBodega().getId(),
                s.getBodega().getNombre(),
                s.getCantidad(),
                s.getProducto().getStockMinimo(),
                dias,
                nivel
            ));
        }
        return resultado;
    }
}
