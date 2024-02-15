package usuario.com.usuario;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

@Configuration
@AllArgsConstructor
public class ApiConfig {

    private final AutenticacaoService autenticacaoService;
    private UsuarioRepository usuarioRepository;

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return autenticacaoService;
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.userDetailsService(userDetailsService());
//        return httpSecurity.build();
//    }

    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @PostConstruct
    public void init(){
        Usuario usuario = new Usuario();
        usuario.setIdade(18);
        usuario.setEmail("teste");
        usuario.setUsuarioDetailsEntity(
                UsuarioDetailsEntity.builder()
                        .usuario(usuario)
                        .enabled(true)
                        .accountNonExpired(true)
                        .accountNonLocked(true)
                        .credentialsNonExpired(true)
                        .username("teste1@teste.com")
                        .password("teste123")
                        .build());

        usuarioRepository.save(usuario);
    }

//    @Bean
//    public UserDetailsManager inMemoryUserDetailsManager(){
//        UserDetails user= User.withDefaultPasswordEncoder()
//                .username("mi72")
//                .password("m!7dois")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//
//    }
//
}
