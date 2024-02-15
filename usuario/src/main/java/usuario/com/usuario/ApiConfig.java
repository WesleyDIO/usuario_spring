package usuario.com.usuario;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@AllArgsConstructor
public class ApiConfig {

    private final AutenticacaoService autenticacaoService;

    @Bean
    public UserDetailsService userDetailsService() {
        return autenticacaoService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
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
