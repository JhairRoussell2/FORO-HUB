package com.foro.forohub.dto;

import com.foro.forohub.domain.StatusTopico;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        String curso
) {}