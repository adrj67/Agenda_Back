/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.agendaSP.repository;

import com.app.agendaSP.entity.Contacto;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adrj
 */
@Repository("contactoRepository")
public interface ContactoRepository extends JpaRepository <Contacto, Serializable> {
    
    public List<Contacto> findByUsuarioCorreo(String usuarioCorreo);

}
