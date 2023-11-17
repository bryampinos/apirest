package com.crud.spring.services;

import com.crud.spring.persistence.entities.RegistroEntity;
import com.crud.spring.persistence.entities.UserEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IRegistroService {
    public RegistroEntity createRegistro(RegistroEntity registro);
    public List<RegistroEntity> getAllRegistros();
    public Optional<RegistroEntity> getRegistroById(Long registroId);
    public RegistroEntity updateRegistro(Long RegistroId,RegistroEntity newRegistro);
    public HashMap<String, String> deleteRegistro(Long registroId);

}
