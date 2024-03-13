package usuario.com.usuario.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import usuario.com.usuario.UsuarioLogin;
import usuario.com.usuario.util.CookieUtil;
import usuario.com.usuario.util.JwtUtil;

@AllArgsConstructor
@RestController

public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository;
    private final JwtUtil jwtUtil = new JwtUtil();
    private final CookieUtil cookieUtil = new CookieUtil();

    @PostMapping("/auth/login")
    public ResponseEntity<String> authenticate(@RequestBody UsuarioLogin usuarioLogin, HttpServletRequest request, HttpServletResponse response){
        System.out.println(usuarioLogin);
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usuarioLogin.getUsername(), usuarioLogin.getPassword());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContext context = SecurityContextHolder.createEmptyContext();
//            context.setAuthentication(authentication);
////        SecurityContextHolder.setContext(context);
//            securityContextRepository.saveContext(context, request, response);
            UserDetails user = (UserDetails) authentication.getPrincipal();
            Cookie cookie = cookieUtil.gerarCookieJwt(user);
            response.addCookie(cookie);
            return ResponseEntity.ok("autenticação bem sucedida");
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
        }
    }
}
