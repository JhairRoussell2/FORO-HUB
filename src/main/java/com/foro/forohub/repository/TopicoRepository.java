package com.foro.forohub.repository;

import com.foro.forohub.domain.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByActivoTrue(Pageable pageable);

    Optional<Topico> findByIdAndActivoTrue(Long id);

    @Query("SELECT t FROM Topico t WHERE t.activo = true AND t.curso.nombre = :cursoNombre AND YEAR(t.fechaCreacion) = :year")
    Page<Topico> findByCursoAndYear(@Param("cursoNombre") String cursoNombre, @Param("year") int year, Pageable pageable);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Topico t WHERE t.titulo = :titulo AND t.mensaje = :mensaje AND t.id != :id")
    boolean existsByTituloAndMensajeAndIdNot(@Param("titulo") String titulo, @Param("mensaje") String mensaje, @Param("id") Long id);

}