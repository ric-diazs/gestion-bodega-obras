package com.bodega_obra.cl.registro_entrada_salida.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.registro_entrada_salida.model.Rol;
import com.bodega_obra.cl.registro_entrada_salida.repository.RolRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    public Rol findById(Integer id) {
        return rolRepository.findById(id).get();
    }

    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    public void delete(Integer id) {
        rolRepository.deleteById(id);
    }
}
