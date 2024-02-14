package usuario.com.usuario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class ApiConfig {

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
