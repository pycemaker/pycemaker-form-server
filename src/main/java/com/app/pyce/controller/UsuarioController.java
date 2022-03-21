package com.app.pyce.controller;

import com.app.pyce.model.Usuario;
import com.app.pyce.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Endpoint de Formulário
    @PostMapping("/registrar")
    public Usuario saveUser(@Validated @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Endpoint de listagem
    @GetMapping("/usuarios")
    public ResponseEntity getAllUsuarios(){
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }




}
