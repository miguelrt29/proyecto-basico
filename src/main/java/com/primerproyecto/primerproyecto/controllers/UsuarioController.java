package com.primerproyecto.primerproyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primerproyecto.primerproyecto.dto.UsuarioDTO;
import com.primerproyecto.primerproyecto.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    /*
     * Endpoint para traer usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    /*
     * Endpoint para agregar usuarios
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> guardarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioGuardado = usuarioService.guardarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
    }
    /*
     * Endpoint para eliminar usuarios
     */
    @DeleteMapping
    public ResponseEntity<List<UsuarioDTO>> eliminUsuarios(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        List<UsuarioDTO> usuarios = usuarioService.obtenerUsuarios();
        return
        ResponseEntity.ok(usuarios);
    }

    // UPDATE
@PutMapping("/{id}")
public ResponseEntity<UsuarioDTO> actualizarUsuario(
        @PathVariable Long id,
        @RequestBody UsuarioDTO usuarioDTO) {

    UsuarioDTO usuarioActualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
    return ResponseEntity.ok(usuarioActualizado);
}
}
