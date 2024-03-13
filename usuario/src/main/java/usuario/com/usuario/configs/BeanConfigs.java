package usuario.com.usuario.configs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import usuario.com.usuario.AutenticacaoService;

import java.util.List;

@AllArgsConstructor
@Configuration
public class BeanConfigs {

    private final AutenticacaoService autenticacaoService;
    @Bean
    public CorsConfigurationSource corsConfig(){
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("http://localhost:8086"));
        corsConfig.setAllowedMethods(List.of("POST","GET",""));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",corsConfig);
        return corsConfigurationSource;
    }
    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(autenticacaoService);
        return new ProviderManager(dao);

    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        return autenticacaoService;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

//   @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth, AutenticacaoService autenticacaoService) throws Exception {
//        auth
//        .userDetailsService(autenticacaoService)
//        .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }
}
