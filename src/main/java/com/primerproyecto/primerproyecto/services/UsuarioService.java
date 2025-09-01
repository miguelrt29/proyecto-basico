package com.primerproyecto.primerproyecto.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.primerproyecto.primerproyecto.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primerproyecto.primerproyecto.dto.UsuarioDTO;
import com.primerproyecto.primerproyecto.entity.Usuario;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /*
     * Metodo para listar todos los usuarios guardados en la base de datos
     */
    public List<UsuarioDTO> obtenerUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> UsuarioDTO.builder()
                        .id(usuario.getId())
                        .nombre(usuario.getNombre())
                        .correo(usuario.getCorreo())
                        .clave(usuario.getClave())
                        .fechaCreacion(usuario.getFechaCreacion())
                        .build())
                .collect(Collectors.toList());
    }

    /*
     * Metodo para guardar un usuario nuevo
     */
    public UsuarioDTO guardarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .correo(usuarioDTO.getCorreo())
                .clave(usuarioDTO.getClave())
                .fechaCreacion(new Timestamp(System.currentTimeMillis())) // asigna fecha actual
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
                .id(usuarioGuardado.getId())
                .nombre(usuarioGuardado.getNombre())
                .correo(usuarioGuardado.getCorreo())
                .clave(usuarioGuardado.getClave())
                .fechaCreacion(usuarioGuardado.getFechaCreacion())
                .build();
    }

    /*
     * Metodo para eliminar un usuario por id
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    /*
     * Metodo para actualizar un usuario
     */
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setClave(usuarioDTO.getClave());
        usuario.setFechaCreacion(usuarioDTO.getFechaCreacion() != null
                ? usuarioDTO.getFechaCreacion()
                : usuario.getFechaCreacion());

        Usuario usuarioActualizado = usuarioRepository.save(usuario);

        return UsuarioDTO.builder()
                .id(usuarioActualizado.getId())
                .nombre(usuarioActualizado.getNombre())
                .correo(usuarioActualizado.getCorreo())
                .clave(usuarioActualizado.getClave())
                .fechaCreacion(usuarioActualizado.getFechaCreacion())
                .build();
    }
}
