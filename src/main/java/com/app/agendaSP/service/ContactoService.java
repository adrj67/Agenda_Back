/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.agendaSP.service;

import com.app.agendaSP.entity.Contacto;
import com.app.agendaSP.entity.Correo;
import com.app.agendaSP.entity.Direccion;
import com.app.agendaSP.entity.Telefono;
import com.app.agendaSP.repository.ContactoRepository;
import com.app.agendaSP.repository.CorreoRepository;
import com.app.agendaSP.repository.DireccionRepository;
import com.app.agendaSP.repository.TelefonoRepository;
import java.util.LinkedList;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adrj
 */
@RestController
@RequestMapping("/contacto")
@CrossOrigin
public class ContactoService {
    
    @Autowired
    CorreoRepository correoRepository;
    
    @Autowired
    TelefonoRepository telefonoRepository;
     
    @Autowired
    DireccionRepository direccionRepository;
    
    @Autowired
    ContactoRepository contactoRepository;
    
    @GetMapping (path = "/buscar")
    public List<Contacto> getAllContacto(){
        return contactoRepository.findAll();
    }
    
    //guarda y actualiza
    @PostMapping(path = "/guardar")
    public Contacto saveContacto (@RequestBody Contacto contacto){
        
        List<Correo> correos = contacto.getCorreoList();
        List<Telefono> telefonos = contacto.getTelefonoList();
        List<Direccion> direcciones = contacto.getDireccionList();
        
        contacto.setCorreoList(null);
        contacto.setTelefonoList(null);
        contacto.setDireccionList(null);
        
        contacto = contactoRepository.save(contacto);
        
        contacto.setTelefonoList(new LinkedList<>());
        contacto.setCorreoList(new LinkedList<>());
        contacto.setDireccionList(new LinkedList<>());
        
        if (correos!=null) {
            for(Correo c : correos){
                c.setContactoIdcontacto(contacto.getIdcontacto());
                correoRepository.save(c);
                contacto.getCorreoList().add(c);
            }
        }
        // https://www.youtube.com/watch?v=ztuYj9SMOgc minuto 36:07 error para ver si idContacto rs Integer o String
        
        if (telefonos!=null) {
            for(Telefono t : telefonos){
                t.setContactoIdcontacto(contacto.getIdcontacto());
                telefonoRepository.save(t);
                contacto.getTelefonoList().add(t);
            }
        }
        
        if (direcciones!=null) {
            for(Direccion d : direcciones){
                d.setContactoIdcontacto(contacto.getIdcontacto());
                direccionRepository.save(d);
                contacto.getDireccionList().add(d);
            }
        }
        return contacto;
    }
    
    // https://www.youtube.com/watch?v=ztuYj9SMOgc minuto 29:08hs Parte 4
    
    @DeleteMapping (path = "/eliminar/{idcontacto}")
    public void deleteContacto (@PathVariable ("idcontacto") Integer idcontacto){
        Optional<Contacto> contacto;
        contacto = contactoRepository.findById(idcontacto);
        if(contacto.isPresent()){
            Contacto borrar = contacto.get();
            for(Telefono t: borrar.getTelefonoList()){
                telefonoRepository.delete(t);
            }
            for(Direccion d: borrar.getDireccionList()){
                direccionRepository.delete(d);
            }
            for(Correo c: borrar.getCorreoList()){
                correoRepository.delete(c);
            }
            
            contactoRepository.delete(contacto.get());
        }
    }
    
    @GetMapping(path = "/buscar/correo/{correo}")
    public List<Contacto> findByCorreo(@PathVariable("correo") String correo){
        return contactoRepository.findByUsuarioCorreo(correo);
    }
    
    @PutMapping("/update/{idcontacto}")
    public Contacto updateContacto (@RequestBody Contacto contacto){
        
        List<Correo> correos = contacto.getCorreoList();
        List<Telefono> telefonos = contacto.getTelefonoList();
        List<Direccion> direcciones = contacto.getDireccionList();
        
        
        contacto = contactoRepository.save(contacto);
        
        contacto.setTelefonoList(new LinkedList<>());
        contacto.setCorreoList(new LinkedList<>());
        contacto.setDireccionList(new LinkedList<>());
        
        if (correos!=null) {
            for(Correo c : correos){
                c.setContactoIdcontacto(contacto.getIdcontacto());
                correoRepository.save(c);
                contacto.getCorreoList().add(c);
            }
        }
        
        if (telefonos!=null) {
            for(Telefono t : telefonos){
                t.setContactoIdcontacto(contacto.getIdcontacto());
                telefonoRepository.save(t);
                contacto.getTelefonoList().add(t);
            }
        }
        
        if (direcciones!=null) {
            for(Direccion d : direcciones){
                d.setContactoIdcontacto(contacto.getIdcontacto());
                direccionRepository.save(d);
                contacto.getDireccionList().add(d);
            }
        }
        return contacto;
    }

}
