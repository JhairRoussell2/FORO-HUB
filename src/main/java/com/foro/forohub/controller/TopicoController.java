package com.foro.forohub.controller;

import com.foro.forohub.dto.TopicoRegistroDTO;
import com.foro.forohub.dto.TopicoRespuestaDTO;
import com.foro.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        TopicoRespuestaDTO topicoRespuesta = topicoService.registrarTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topicoRespuesta.id()).toUri();
        return ResponseEntity.created(url).body(topicoRespuesta);
    }

    @GetMapping
    public ResponseEntity<Page<TopicoRespuestaDTO>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicos(paginacion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoRespuestaDTO> obtenerTopicoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.obtenerTopicoPorId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoRegistroDTO datos) {
        return ResponseEntity.ok(topicoService.actualizarTopico(id, datos));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        topicoService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/curso")
    public ResponseEntity<Page<TopicoRespuestaDTO>> listarTopicosPorCursoYYear(
            @RequestParam String curso,
            @RequestParam int year,
            @PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoService.listarTopicosPorCursoYYear(curso, year, paginacion));
    }
}