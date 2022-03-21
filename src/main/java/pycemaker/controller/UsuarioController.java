package pycemaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pycemaker.model.Usuario;
import pycemaker.repository.UsuarioRepository;


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
