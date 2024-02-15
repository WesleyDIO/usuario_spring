package usuario.com.usuario;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import usuario.com.usuario.model.entity.Usuario;
import usuario.com.usuario.repository.UsuarioRepository;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AutenticacaoService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByUsuarioDetailsEntity_Username(username);
        if(usuarioOptional.isPresent()){
            return new UsuarioDetailsEntity(usuarioOptional.get());
        }
        throw new UsernameNotFoundException("Dados Inválidos");
    }
}
