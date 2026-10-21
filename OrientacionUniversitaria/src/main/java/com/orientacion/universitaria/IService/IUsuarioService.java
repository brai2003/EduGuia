package com.orientacion.universitaria.IService;

import java.util.List;
import java.util.Optional;
import com.orientacion.universitaria.entidades.Usuario;

public interface IUsuarioService {
    List<Usuario> findAllUsuarios();
    Usuario saveUsuario(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
    Usuario login(String email, String password);
}
