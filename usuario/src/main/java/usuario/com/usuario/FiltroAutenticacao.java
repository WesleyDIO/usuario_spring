package usuario.com.usuario;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import usuario.com.usuario.util.CookieUtil;
import usuario.com.usuario.util.JwtUtil;

import java.io.IOException;

public class FiltroAutenticacao extends OncePerRequestFilter {

    private CookieUtil cookieUtil = new CookieUtil();
    private JwtUtil jwtUtil = new JwtUtil();
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = cookieUtil.getCookie(request,"JWT");
        String token = cookie.getValue();
        jwtUtil.validarToken(token);
        filterChain.doFilter(request,response);
    }
}
