package usuario.com.usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.userdetails.UserDetails;
import usuario.com.usuario.model.entity.Usuario;

import java.util.Collection;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class UsuarioDetailsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "usuarioDetailsEntity")
    @NonNull
    @ToString.Exclude
    private Usuario usuario;
    private boolean enabled;
    @Column(unique = true, nullable = false, updatable = false)
    @Email
    private String username;
    @Column(nullable = false)
    @Length(min = 6)
    private String password;
    private Collection<Autorizacao> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }

//    @Override
//    public String getPassword() {
//        return usuario.getSenha();
//    }

//    @Override
//    public String getUsername() {
//        return usuario.getNome();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
}
