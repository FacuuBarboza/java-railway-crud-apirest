package com.apirest.apirest.Controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductRepository;



@RestController
@RequestMapping("/productos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }
    

    @GetMapping
    public List<Producto> getAllProductos() {
        return productRepository.findAll();
    }

    @PostMapping
    public Producto createProducto (@RequestBody Producto producto) {
        return productRepository.save(producto);
    }

    @PutMapping("/{id}")
    public Producto updateProducto (@PathVariable Long id, @RequestBody Producto detailsProducto) {
        Producto producto = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
        
        producto.setNombre(detailsProducto.getNombre());
        producto.setPrecio(detailsProducto.getPrecio());

        return productRepository.save(producto);
    }

    @DeleteMapping("/{id}")
    public String DeleteProducto (@PathVariable Long id) {
        Producto producto = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productRepository.delete(producto);
        return "El producto con el ID: " + id + " ha sido eliminado";
    }
}
