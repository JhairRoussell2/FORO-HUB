package com.foro.forohub.service;

import com.foro.forohub.domain.Curso;
import com.foro.forohub.domain.Topico;
import com.foro.forohub.domain.Usuario;
import com.foro.forohub.dto.TopicoRegistroDTO;
import com.foro.forohub.dto.TopicoRespuestaDTO;
import com.foro.forohub.repository.CursoRepository;
import com.foro.forohub.repository.TopicoRepository;
import com.foro.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public TopicoRespuestaDTO registrarTopico(TopicoRegistroDTO datos) {
        // Verificar si ya existe un tópico con el mismo título y mensaje
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.findByIdAndActivoTrue(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        return new TopicoRespuestaDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                autor.getNombre(),
                curso.getNombre());
    }

    public Page<TopicoRespuestaDTO> listarTopicos(Pageable paginacion) {
        return topicoRepository.findByActivoTrue(paginacion).map(topico -> new TopicoRespuestaDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        ));
    }

    public TopicoRespuestaDTO obtenerTopicoPorId(Long id) {
        Topico topico = topicoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return new TopicoRespuestaDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }

    public TopicoRespuestaDTO actualizarTopico(Long id, TopicoRegistroDTO datos) {
        Topico topico = topicoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        // Verificar si ya existe otro tópico con el mismo título y mensaje (excluyendo el actual)
        if (topicoRepository.existsByTituloAndMensajeAndIdNot(datos.titulo(), datos.mensaje(), id)) {
            throw new RuntimeException("Ya existe otro tópico con el mismo título y mensaje");
        }

        Usuario autor = usuarioRepository.findByIdAndActivoTrue(datos.autorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        Curso curso = cursoRepository.findById(datos.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(autor);
        topico.setCurso(curso);

        topicoRepository.save(topico);

        return new TopicoRespuestaDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                autor.getNombre(),
                curso.getNombre());
    }

    public void eliminarTopico(Long id) {
        Topico topico = topicoRepository.findByIdAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        topico.setActivo(false);
        topicoRepository.save(topico);
    }

    public Page<TopicoRespuestaDTO> listarTopicosPorCursoYYear(String cursoNombre, int year, Pageable paginacion) {
        return topicoRepository.findByCursoAndYear(cursoNombre, year, paginacion).map(topico -> new TopicoRespuestaDTO(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        ));
    }
}