package usuario.com.usuario.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import usuario.com.usuario.UsuarioDetailsEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @Column(unique = true, nullable = false)
//    private String nome;
//    private boolean habilitado;
//    @Column(nullable = false)
//    private String senha;
    private String email;
//    private Boolean status;
    private Integer idade;
    @OneToOne(cascade = CascadeType.ALL)
    private Foto foto;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private UsuarioDetailsEntity usuarioDetailsEntity;
}
