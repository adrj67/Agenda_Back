/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.agendaSP.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author adrj
 */
@Entity
@Table(name = "correo")
public class Correo implements Serializable{
    
    private static final long serialVersionUID = 5L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column (name = "idcorreo")
    private Integer idcorreo;
    
    @Column (name = "correo")
    private String correo;
    
    @Column (name = "contacto_idcontacto")
    private Integer contactoIdcontacto;

    public Integer getIdcorreo() {
        return idcorreo;
    }

    public void setIdcorreo(Integer idcorreo) {
        this.idcorreo = idcorreo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getContactoIdcontacto() {
        return contactoIdcontacto;
    }

    public void setContactoIdcontacto(Integer contactoIdcontacto) {
        this.contactoIdcontacto = contactoIdcontacto;
    }
    
}
