package com.challenge.literalura_2.repository;

import com.challenge.literalura_2.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository <Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdiomaContaining(String idioma);

    List<Libro> findTop10ByOrderByNumeroDescargasDesc();

}
