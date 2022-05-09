package pycemaker.controller;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pycemaker.model.Usuario;
import pycemaker.repository.UsuarioRepository;

import java.util.List;


@CrossOrigin
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

    @GetMapping("/usuario/{page}-{sizepage}")
    public ResponseEntity consultar(@PathVariable int page, @PathVariable int sizepage){
        Pageable firstPage = PageRequest.of(page, sizepage);
        return ResponseEntity.ok((this.usuarioRepository.findAll(firstPage)).toList());
    }

    @DeleteMapping("/usuario/delete/{id}")
    public ResponseEntity delete(@PathVariable long id){
        this.usuarioRepository.deleteById(id);
        return (ResponseEntity) ResponseEntity.ok("Deletado com sucesso");
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
