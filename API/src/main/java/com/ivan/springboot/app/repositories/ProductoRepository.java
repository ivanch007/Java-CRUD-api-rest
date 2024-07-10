package com.ivan.springboot.app.repositories;

import com.ivan.springboot.app.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
