package com.residencia.ecommerce.repositories;

import com.residencia.ecommerce.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Categoria findByNomeCategoria(String nomeCategoria);
}
