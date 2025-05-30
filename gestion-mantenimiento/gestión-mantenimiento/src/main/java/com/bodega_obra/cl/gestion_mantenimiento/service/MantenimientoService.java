package com.bodega_obra.cl.gestion_mantenimiento.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.gestion_mantenimiento.model.Mantenimiento;
import com.bodega_obra.cl.gestion_mantenimiento.repository.MantenimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public List < Mantenimiento > findALL () {
        return mantenimientoRepository.findAll();
    }

    public Mantenimiento findById (Integer id){
        return mantenimientoRepository.findById(id).get();
    }
    

    public Mantenimiento save(Mantenimiento mantenimiento) {
        return mantenimientoRepository.save(mantenimiento);
    }

    public void delete(Integer id){
        mantenimientoRepository.deleteById(id);
    }

}
