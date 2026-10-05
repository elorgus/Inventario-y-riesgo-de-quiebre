package com.ejemplo.inventario.controller;

import com.ejemplo.inventario.dto.RiesgoQuiebreDTO;
import com.ejemplo.inventario.dto.StockDTO;
import com.ejemplo.inventario.entity.Bodega;
import com.ejemplo.inventario.entity.Producto;
import com.ejemplo.inventario.entity.Stock;
import com.ejemplo.inventario.repository.BodegaRepository;
import com.ejemplo.inventario.repository.ProductoRepository;
import com.ejemplo.inventario.repository.StockRepository;
import com.ejemplo.inventario.service.InventarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventarioController {

    private final ProductoRepository productoRepo;
    private final BodegaRepository bodegaRepo;
    private final StockRepository stockRepo;
    private final InventarioService service;

    public InventarioController(ProductoRepository productoRepo, BodegaRepository bodegaRepo,
                                StockRepository stockRepo, InventarioService service) {
        this.productoRepo = productoRepo;
        this.bodegaRepo = bodegaRepo;
        this.stockRepo = stockRepo;
        this.service = service;
    }

    @GetMapping("/productos")
    public List<Producto> listarProductos() { return productoRepo.findAll(); }

    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto p) { return productoRepo.save(p); }

    @GetMapping("/bodegas")
    public List<Bodega> listarBodegas() { return bodegaRepo.findAll(); }

    @PostMapping("/bodegas")
    public Bodega crearBodega(@RequestBody Bodega b) { return bodegaRepo.save(b); }

    @GetMapping("/stock")
    public List<StockDTO> listarStock(@RequestParam(required = false) Long bodegaId) {
        List<Stock> stocks = (bodegaId != null)
                ? stockRepo.findByBodegaId(bodegaId)
                : stockRepo.findAll();

        return stocks.stream().map(s -> new StockDTO(
                s.getId(),
                s.getProducto().getId(),
                s.getProducto().getSku(),
                s.getProducto().getNombre(),
                s.getBodega().getId(),
                s.getBodega().getNombre(),
                s.getCantidad()
        )).toList();
    }

    @GetMapping("/riesgo")
    public List<RiesgoQuiebreDTO> riesgo() { return service.calcularRiesgos(); }
}
