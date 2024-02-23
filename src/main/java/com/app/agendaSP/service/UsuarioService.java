/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.agendaSP.service;

import com.app.agendaSP.entity.Usuario;
import com.app.agendaSP.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping (path = "/buscar")
    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }
    
    //guarda y actualiza
    @PostMapping(path = "/guardar")
    public Usuario saveUsuario (@RequestBody Usuario usuario){
       
        return usuarioRepository.save(usuario);
    }
    
    @DeleteMapping (path = "/eliminar/{correo}")
    public void deleteUsuario (@PathVariable ("correo") String correo){
        Optional<Usuario> usuario;
        usuario = usuarioRepository.findById(correo);
        if(usuario.isPresent()){
            usuarioRepository.delete(usuario.get());
        }
    }
    
    @PostMapping(path = "/login")
    public Usuario login(@RequestBody Usuario usuario){
        
        List<Usuario> usuarios = usuarioRepository.findByCorreoAndPassword(usuario.getCorreo(), usuario.getPassword());
        
        if(!usuarios.isEmpty()){
            return usuarios.get(0);
        }
        return null;
    }
    
}
