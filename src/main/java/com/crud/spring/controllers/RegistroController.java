package com.crud.spring.controllers;

import com.crud.spring.persistence.entities.RegistroEntity;
import com.crud.spring.services.IRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8100")
@RestController
@RequestMapping("/registro")
public class RegistroController {
    @Autowired
    IRegistroService registroService;

    @PostMapping("/create")


    public ResponseEntity<RegistroEntity> createRegistro(@RequestBody RegistroEntity registro){
        try {
            RegistroEntity createdRegistro= registroService.createRegistro(registro);
            return new ResponseEntity<>(createdRegistro, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-all")
    public ResponseEntity <List<RegistroEntity>> getAllRegistros(){
        try {
            List<RegistroEntity> registros = registroService.getAllRegistros();
            return new ResponseEntity<>(registros, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("get/{userId}")
    public ResponseEntity<RegistroEntity> getRegistroById(@PathVariable Long registroId){
        try{
            Optional<RegistroEntity> registro = registroService.getRegistroById(registroId) ;
            return registro.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update/{registroId}")
    public ResponseEntity <RegistroEntity> updateRegistro(@PathVariable Long registroId, @RequestBody RegistroEntity newRegistro){
        try {
RegistroEntity updateRegistro = registroService.updateRegistro(registroId, newRegistro);
if (updateRegistro !=null){
    return new ResponseEntity<>(updateRegistro, HttpStatus.OK);
}
return ResponseEntity.notFound().build();

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
@DeleteMapping("/delete/{registroId}")
public ResponseEntity<HashMap<String, String>> deleteregistro(@PathVariable long registroId){
        try {
            HashMap<String, String>response= registroService.deleteRegistro(registroId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
}
}


