package com.crud.spring.services.businesslogic;

import com.crud.spring.persistence.entities.RegistroEntity;

import com.crud.spring.persistence.repos.RegistroRepository;

import com.crud.spring.services.IRegistroService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroServicelmpl implements IRegistroService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistroServicelmpl.class);

    @Autowired
    RegistroRepository registroRepository;

    @Override
    public RegistroEntity createRegistro(RegistroEntity registro) {
        try {
            return registroRepository.save(registro);
        } catch (Exception e) {
            LOGGER.error("Error while creating user: {}", e.getMessage());
            throw new RuntimeException("Error creating user");
        }
    }

    @Override
    public List<RegistroEntity> getAllRegistros() {
        try {
            return registroRepository.findAll();
        } catch (Exception e) {
            LOGGER.error("Error while fetching all users: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Optional<RegistroEntity> getRegistroById(Long registroId) {
        try {
            return registroRepository.findById(registroId);
        } catch (Exception e) {
            LOGGER.error("Error while fetching user by ID: {}", e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }

    @Override
    public RegistroEntity updateRegistro(Long registroID, RegistroEntity newRegistro) {
        try {
            RegistroEntity existingRegistro = registroRepository.findById(registroID).orElse(null);
            if (existingRegistro != null) {
                existingRegistro.setNombre(newRegistro.getNombre());
                existingRegistro.setApellido(newRegistro.getApellido());
                existingRegistro.setRol(newRegistro.getRol());
                existingRegistro.setDescripcion(newRegistro.getDescripcion());
                existingRegistro.setEvidencia(newRegistro.getEvidencia());
                existingRegistro.setEstado(newRegistro.getEstado());
                existingRegistro.setFecha_ingreso(newRegistro.getFecha_ingreso());


                return registroRepository.save(existingRegistro);
            }
            throw new RuntimeException("User not found");
        } catch (Exception e) {
            LOGGER.error("Error while updating user: {}", e.getMessage());
            throw new RuntimeException("Error updating user");
        }
    }

    @Override
    public HashMap<String, String> deleteRegistro(Long registroId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "User deleted succesfully!");
            registroRepository.deleteById(registroId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error while deleting user: {}", e.getMessage());
            throw new RuntimeException("Error deleting user");
        }
    }
}
