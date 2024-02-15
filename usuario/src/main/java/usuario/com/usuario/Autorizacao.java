package usuario.com.usuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@AllArgsConstructor
public enum Autorizacao implements GrantedAuthority {

    GET("Get"),
    POST("Post"),
    PUT("Put"),
    DELETE("Delete");

    private final String nome;

//    public static Autorizacao getAutorizacao(String nome){
//        return valueOf(nome.toUpperCase());
//    }

    @Override
    public String getAuthority() {
        return nome;
    }
}
