
package com.app.agendaSP.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario  implements Serializable{
    
    private static final long serialVersionUID = 2L;
    
    @Id
    @Basic(optional = false)
    @Column (name = "correo")
    private String correo;
    
    @Column (name = "nombre")
    private String nombre;
    
    @Column (name = "apellido")
    private String apellido;
    
    @Lob
    @Column (name = "fotografia")
    private String fotografia;
    
    @Column (name = "password")
    private String password;
    
    @Column (name = "rol_idrol")
    private String rolIdrol;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(String rolIdrol) {
        this.rolIdrol = rolIdrol;
    }
    
    
}
