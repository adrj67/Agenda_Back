/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.agendaSP.service;

import com.app.agendaSP.entity.Rol;
import com.app.agendaSP.repository.RolRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adrj
 */
@RestController
@RequestMapping("/rol")
@CrossOrigin
public class RolService {
    
    @Autowired
    RolRepository rolRepository;
    
    @GetMapping (path = "/buscar")
    public List<Rol> getAllRol(){
        return rolRepository.findAll();
    }
    //guarda y actualiza
    @PostMapping(path = "/guardar")
    public Rol saveRol (@RequestBody Rol rol){
        return rolRepository.save(rol);
    }
    
    @DeleteMapping (path = "/eliminar/{idRol}")
    public void deleteRol (@PathVariable ("idRol") Integer idRol){
        Optional<Rol> rol;
        rol = rolRepository.findById(idRol);
        if(rol.isPresent()){
            rolRepository.delete(rol.get());
        }
    }
    
}
/*
https://www.youtube.com/watch?v=sOJ-_OG5GeY minuto 15:28hs
*/