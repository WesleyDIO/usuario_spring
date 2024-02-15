package usuario.com.usuario;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

import java.util.List;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AutenticacaoService autenticacaoService;


    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers(HttpMethod.GET , "/teste").permitAll()
                        .requestMatchers("/teste").permitAll()
                        .anyRequest().authenticated()
        );
        return httpSecurity.build();
    }


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        return autenticacaoService;
//    }



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
