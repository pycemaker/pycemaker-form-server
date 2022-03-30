package pycemaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pycemaker.model.Usuario;
import pycemaker.repository.UsuarioRepository;

import java.util.Optional;


@CrossOrigin
@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Endpoint de Formulário
    @CrossOrigin
    @PostMapping("/registrar")
    public Usuario saveUser(@Validated @RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    //Endpoint de listagem
    @CrossOrigin
    @GetMapping("/usuarios")
    public ResponseEntity getAllUsuarios(){
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    //@GetMapping("/usuario/{id}")
    //public Optional<Usuario> consultar(@PathVariable("id") Long id){
        //return usuarioRepository.findById(id);
    //}


    @GetMapping("/stress")
    public String stressTest(){
        try {
            boolean condition = true;
            while(condition){
                Runnable r = () -> {
                    while(true){
                    }
                };
                new Thread(r).start();
                Thread.sleep(5000);
            }
        } catch (Exception e) {

        }
        return "Stress";
    }

}
