package com.orientacion.universitaria.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    
    @Column(unique = true, nullable = false)
    private String email;
    
    private String password;
    private int dni;
    private String interesPrincipal;
    private boolean activo = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoUsuario tipo = TipoUsuario.ESTUDIANTE;

    public enum TipoUsuario {
        ESTUDIANTE,
        ORIENTADOR,
        ADMINISTRADOR
    }

    public Usuario() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }

    public String getInteresPrincipal() { return interesPrincipal; }
    public void setInteresPrincipal(String interesPrincipal) { this.interesPrincipal = interesPrincipal; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}
