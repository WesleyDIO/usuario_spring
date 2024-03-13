package usuario.com.usuario.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private SecretKey key;
    public JwtUtil(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = encoder.encode("senha123");
        this.key = Keys.hmacShaKeyFor(senha.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(UserDetails userDetails){
       return Jwts.builder().issuer("WEG").issuedAt(new Date()).expiration(new Date(new Date().getTime() + 300000))
                .signWith(this.key, Jwts.SIG.HS384).encryptWith(this.key, Jwts.ENC.A128CBC_HS256).subject(userDetails.getUsername()).compact();
    }

    private Jws<Claims> validarToken(String token){
         return getParser().parseSignedClaims(token);
    }

    private JwtParser getParser(){
        return Jwts.parser().decryptWith(this.key).build();
    }


    public String getUsername(String token){
     return validarToken(token).getPayload().getSubject();
    }

}
