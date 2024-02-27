package usuario.com.usuario;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

import java.util.List;

@Configuration
@AllArgsConstructor
public class DataBaseConfig {

    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init(){
        Usuario usuario = new Usuario();
        usuario.setIdade(18);
        usuario.setEmail("teste");
        usuario.setUsuarioDetailsEntity(
                UsuarioDetailsEntity.builder()
                        .authorities(List.of(Autorizacao.GET))
                        .usuario(usuario)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .username("teste2@teste.com")
                        .password( new BCryptPasswordEncoder().encode("teste123"))
                        .build());

        usuarioRepository.save(usuario);
    }
}
