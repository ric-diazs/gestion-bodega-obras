package com.bodega_obra.cl.validacion_usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bodega_obra.cl.validacion_usuario.model.Validacion;
import com.bodega_obra.cl.validacion_usuario.repository.ValidacionRepository;

@Service
@Transactional
public class ValidacionService {
    @Autowired
    private ValidacionRepository validacionRepository;

    public List<Validacion> findALL() {
        return validacionRepository.findAll();
    }

     public Validacion findById(Integer id){
        return validacionRepository.findById(id).get();
    }

     public Validacion save(Validacion validacion) {
        return validacionRepository.save(validacion);
    }
    
     public void delete(Integer id){
        validacionRepository.deleteById(id);
    }
}
