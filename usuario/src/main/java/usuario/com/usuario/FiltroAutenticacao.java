package usuario.com.usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import usuario.com.usuario.util.CookieUtil;
import usuario.com.usuario.util.JwtUtil;

import java.io.IOException;
@Component
@AllArgsConstructor
public class FiltroAutenticacao extends OncePerRequestFilter {
    private SecurityContextRepository securityContextRepository;
    private final CookieUtil cookieUtil = new CookieUtil();
    private final JwtUtil jwtUtil = new JwtUtil();
    private AutenticacaoService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if(!rotaPublica(request)) {
            //Busca e validação do token
            Cookie cookie = cookieUtil.getCookie(request, "JWT");
            String token = cookie.getValue();
            System.out.println(token);
            String username = jwtUtil.getUsername(token);

            //Criação do usuário autenticado
            UserDetails user = userDetailsService.loadUserByUsername(username);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

            // Salvamento do usuário autenticado no Security Context
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, request, response);
        }
            filterChain.doFilter(request, response);

    }

    private boolean rotaPublica(HttpServletRequest request){
        return request.getRequestURI().equals("/auth/login") && request.getMethod().equals("POST");
    }
}
