
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    
    //buscar contacto por id
    @GetMapping("/buscar/{idcontacto}")
    public Optional<Contacto> byId(@PathVariable("idcontacto") Integer id) {
        return contactoRepository.findById(id);
    }

    // Editar contactos
   @PutMapping("/update/{idcontacto}")
    public ResponseEntity<?> updateContacto(@PathVariable("idcontacto") Integer idcontacto, @RequestBody Contacto updatedContacto) {
        Optional<Contacto> existingContactoOpt = contactoRepository.findById(idcontacto);
        if (!existingContactoOpt.isPresent()) {
            return new ResponseEntity<>("Contacto no encontrado", HttpStatus.NOT_FOUND);
        }

       
        Contacto existingContacto = existingContactoOpt.get();
        // Actualizar las propiedades del Contacto
        existingContacto.setNombre(updatedContacto.getNombre());
        existingContacto.setApellido(updatedContacto.getApellido());
        existingContacto.setFotografia(updatedContacto.getFotografia());
        //existingContacto.setUsuarioCorreo(updatedContacto.getUsuarioCorreo());

        // Actualizar las listas de Telefonos, Direcciones y Correos
        updateRelations(existingContacto, updatedContacto);

        // Guardar el Contacto actualizado y sus relaciones
        contactoRepository.save(existingContacto);
        
        return new ResponseEntity<>("Contacto actualizado", HttpStatus.OK);
    }

    // Lógica para actualizar las relaciones de Telefono, Direccion y Correo
    private void updateRelations(Contacto existingContacto, Contacto updatedContacto) {
    // Update Telefono relationships
    Map<Integer, Telefono> existingTelefonos = existingContacto.getTelefonoList().stream()
            .collect(Collectors.toMap(Telefono::getIdtelefono, Function.identity()));
    
    for (Telefono updatedTelefono : updatedContacto.getTelefonoList()) {
        if (existingTelefonos.containsKey(updatedTelefono.getIdtelefono())) {
            // Existing Telefono found, update its fields
            Telefono existingTelefono = existingTelefonos.get(updatedTelefono.getIdtelefono());
            existingTelefono.setNumero(updatedTelefono.getNumero());
            // Add other fields to update if needed
        } else {
            // New Telefono, save it directly
            telefonoRepository.save(updatedTelefono);
        }
    }
    
    // Update Direccion relationships similarly
    Map<Integer, Direccion> existingDirecciones = existingContacto.getDireccionList().stream()
            .collect(Collectors.toMap(Direccion::getIddireccion, Function.identity()));
    
    for (Direccion updatedDireccion : updatedContacto.getDireccionList()) {
        if (existingDirecciones.containsKey(updatedDireccion.getIddireccion())) {
            // Existing Direccion found, update its fields
            Direccion existingDireccion = existingDirecciones.get(updatedDireccion.getIddireccion());
            existingDireccion.setDescripcion(updatedDireccion.getDescripcion());
            // Add other fields to update if needed
        } else {
            // New Direccion, save it directly
            direccionRepository.save(updatedDireccion);
        }
    }
    
    // Update Correo relationships similarly
    Map<Integer, Correo> existingCorreos = existingContacto.getCorreoList().stream()
            .collect(Collectors.toMap(Correo::getIdcorreo, Function.identity()));
    
    for (Correo updatedCorreo : updatedContacto.getCorreoList()) {
        if (existingCorreos.containsKey(updatedCorreo.getIdcorreo())) {
            // Existing Correo found, update its fields
            Correo existingCorreo = existingCorreos.get(updatedCorreo.getIdcorreo());
            existingCorreo.setCorreo(updatedCorreo.getCorreo());
            // Add other fields to update if needed
        } else {
            // New Correo, save it directly
            correoRepository.save(updatedCorreo);
            }
        }
    }
    
    // Borrar Telefono
    @DeleteMapping (path = "/telefono/eliminar/{idtelefono}")
    public void deleteTelefono (@PathVariable ("idtelefono") Integer idtelefono){
        Optional<Telefono> telefono;
        telefono = telefonoRepository.findById(idtelefono);
        if(telefono.isPresent()){
            telefonoRepository.delete(telefono.get());
        }
    }
    
    // Borrar Correo
    @DeleteMapping (path = "/correo/eliminar/{idcorreo}")
    public void deleteCorreo (@PathVariable ("idcorreo") Integer idcorreo){
        Optional<Correo> correo;
        correo = correoRepository.findById(idcorreo);
        if(correo.isPresent()){
            correoRepository.delete(correo.get());
        };
    }
    
    // Borrar Domicilio
    @DeleteMapping (path = "/direccion/eliminar/{iddireccion}")
    public void deleteDireccion (@PathVariable ("iddireccion") Integer iddireccion){
        Optional<Direccion> direccion;
        direccion = direccionRepository.findById(iddireccion);
        if(direccion.isPresent()){
            direccionRepository.delete(direccion.get());
        }
    }
    
}