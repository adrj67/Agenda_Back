
package com.app.agendaSP.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;


/**
 *
 * https://www.youtube.com/watch?v=io5g5FS0S08 minuto 21:47
 */
@Entity
@Table(name = "contacto")
public class Contacto   implements Serializable{
    
    private static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column (name = "idcontacto")
    private Integer idcontacto;
    
    @Column (name = "nombre")
    private String nombre;
     
    @Column (name = "apellido")
    private String apellido;
    
    @Column (name = "fotografia")
    private String fotografia;
    
    @Column (name = "usuario_correo")
    private String usuarioCorreo;
    
    @OneToMany (mappedBy = "contactoIdcontacto")
    private List<Correo> correoList;
    
    @OneToMany (mappedBy = "contactoIdcontacto")
    private List<Telefono> telefonoList;
    
    @OneToMany (mappedBy = "contactoIdcontacto")
    private List<Direccion> direccionList;

    public Integer getIdcontacto() {
        return idcontacto;
    }

    public void setIdcontacto(Integer idcontacto) {
        this.idcontacto = idcontacto;
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

    public String getUsuariocorreo() {
        return usuarioCorreo;
    }

    public void setUsuariocorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public List<Correo> getCorreoList() {
        return correoList;
    }

    public void setCorreoList(List<Correo> correoList) {
        this.correoList = correoList;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public List<Direccion> getDireccionList() {
        return direccionList;
    }

    public void setDireccionList(List<Direccion> direccionList) {
        this.direccionList = direccionList;
    }
    
    
}
