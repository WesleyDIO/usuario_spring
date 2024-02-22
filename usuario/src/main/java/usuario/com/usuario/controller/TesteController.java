package usuario.com.usuario.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import usuario.com.usuario.model.dto.UsuarioCadastroDTO;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

@RestController
@RequestMapping("/teste")
@AllArgsConstructor
public class TesteController {

    @GetMapping
    public String teste(){
        Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        return "Teste" + auth.getName() + "!";
    }


}
