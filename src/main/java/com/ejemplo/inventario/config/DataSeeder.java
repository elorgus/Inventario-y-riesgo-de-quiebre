package com.ejemplo.inventario.config;

import com.ejemplo.inventario.entity.Bodega;
import com.ejemplo.inventario.entity.Producto;
import com.ejemplo.inventario.entity.Stock;
import com.ejemplo.inventario.repository.BodegaRepository;
import com.ejemplo.inventario.repository.ProductoRepository;
import com.ejemplo.inventario.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepo;
    private final BodegaRepository bodegaRepo;
    private final StockRepository stockRepo;

    public DataSeeder(ProductoRepository productoRepo, BodegaRepository bodegaRepo, StockRepository stockRepo) {
        this.productoRepo = productoRepo;
        this.bodegaRepo = bodegaRepo;
        this.stockRepo = stockRepo;
    }

    @Override
    public void run(String... args) {
        if (productoRepo.count() > 0) return;

        Producto harina = new Producto();
        harina.setSku("HAR-001"); harina.setNombre("Harina de trigo"); harina.setUnidad("kg");
        harina.setStockMinimo(50); harina.setConsumoDiarioPromedio(new BigDecimal("20"));
        productoRepo.save(harina);

        Producto queso = new Producto();
        queso.setSku("QUE-001"); queso.setNombre("Queso mozzarella"); queso.setUnidad("kg");
        queso.setStockMinimo(30); queso.setConsumoDiarioPromedio(new BigDecimal("10"));
        productoRepo.save(queso);

        Bodega central = new Bodega();
        central.setNombre("Bodega Central"); central.setUbicacion("Quito");
        bodegaRepo.save(central);

        Bodega norte = new Bodega();
        norte.setNombre("Bodega Norte"); norte.setUbicacion("Ibarra");
        bodegaRepo.save(norte);

        Stock s1 = new Stock(); s1.setProducto(harina); s1.setBodega(central); s1.setCantidad(40);
        Stock s2 = new Stock(); s2.setProducto(harina); s2.setBodega(norte); s2.setCantidad(200);
        Stock s3 = new Stock(); s3.setProducto(queso); s3.setBodega(central); s3.setCantidad(80);
        Stock s4 = new Stock(); s4.setProducto(queso); s4.setBodega(norte); s4.setCantidad(15);
        stockRepo.save(s1); stockRepo.save(s2); stockRepo.save(s3); stockRepo.save(s4);
    }
}
