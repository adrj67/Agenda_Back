/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.app.agendaSP.repository;

import com.app.agendaSP.entity.Correo;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adrj
 */
@Repository("correoRepository")
public interface CorreoRepository extends JpaRepository<Correo, Serializable>{
}
