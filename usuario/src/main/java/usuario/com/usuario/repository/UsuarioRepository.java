package usuario.com.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usuario.com.usuario.model.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

//    Optional<Usuario> findByUsuario(String nome);
    Optional<Usuario>findByUsuarioDetailsEntity_Username(String usuario);
}
