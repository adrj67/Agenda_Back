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
@Table(name = "telefono")
public class Telefono  implements Serializable{
    
    private static final long serialVersionUID = 6L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column (name = "idtelefono")
    private Integer idtelefono;
    
    @Column (name = "numero")
    private String numero;
    
    @Column (name = "contacto_idcontacto")
    private Integer contactoIdcontacto;

    public Integer getIdtelefono() {
        return idtelefono;
    }

    public void setIdtelefono(Integer idtelefono) {
        this.idtelefono = idtelefono;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getContactoIdcontacto() {
        return contactoIdcontacto;
    }

    public void setContactoIdcontacto(Integer contactoIdcontacto) {
        this.contactoIdcontacto = contactoIdcontacto;
    }

    
}
