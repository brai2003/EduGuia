package com.orientacion.universitaria.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.orientacion.universitaria.IService.IUsuarioService;
import com.orientacion.universitaria.entidades.Usuario;
import com.orientacion.universitaria.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario login(String email, String password) {
        Optional<Usuario> u = usuarioRepository.findByEmail(email);
        if (u.isPresent() && u.get().getPassword().equals(password)) {
            return u.get();
        }
        return null;
    }
}
