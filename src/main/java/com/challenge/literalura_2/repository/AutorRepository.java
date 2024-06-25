package com.challenge.literalura_2.repository;

import com.challenge.literalura_2.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository <Autor,Long> {

    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);

    @Query("Select a from Autor a Where a.fechaNacimiento <= :fecha AND a.fechaMuerte >= :fecha")
    List<Autor> filtrarAutoresPorFecha(Integer fecha);
}
