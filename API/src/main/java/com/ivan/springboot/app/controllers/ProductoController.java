package com.ivan.springboot.app.controllers;

import com.ivan.springboot.app.entities.Producto;
import com.ivan.springboot.app.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    //Este metodo permite obtener todos los productos de la DB
    @GetMapping
    public List<Producto> getAllProductos(){
        return productoRepository.findAll();
    }

    //Este metodo otiene un solo produto
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id, @RequestBody Producto producto){
        return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

    }


    //Este metodo permite crear un solo producto en la DB
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto){
        return productoRepository.save(producto);

    }

    //Este metodo sirve para actualizar.
    @PutMapping("/{id}")
    public Producto updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());

        return productoRepository.save(producto);
    }

    //Metodo para eliminar un producto
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id){
        Producto producto = productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepository.delete(producto);
        return "El producto con el ID" + id + " fue elimnado correctacmente";
    }
}
