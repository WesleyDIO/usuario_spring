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

        if(!rotaPublica(request)){
            //Busca e validação do token
            Cookie cookie;
            try {
                cookie = cookieUtil.getCookie(request, "JWT");
            } catch (Exception e) {
                response.setStatus(401);
                return;
            }

            String token = cookie.getValue();
            String username = jwtUtil.getUsername(token);

            //criação do usuario autenticado
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities());

            //salvamento do usuario austenticado no Security Context
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            //seta como objeto de autenticao o objeto retornado pela autenticacao ja autenticado (que foi setado isAthenticated como true)
            context.setAuthentication(authentication);
            securityContextRepository.saveContext(context, request, response);

            // Renovação do JWT e Cookie
            Cookie cookieRenovado = cookieUtil.gerarCookieJwt(userDetails);
            response.addCookie(cookieRenovado);
        }
        //Continuação da requisição
        filterChain.doFilter(request,response);
    }

    //definir aqui todas as rotas publicas (permitAll no authFilter)
    private boolean rotaPublica(HttpServletRequest request){
        return request.getRequestURI().equals("/auth/login");
//                && (request.getMethod().equals("GET") || request.getMethod().equals("POST"));
    }
}
