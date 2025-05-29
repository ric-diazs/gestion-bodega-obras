package com.bodega_obra.cl.monitoreo_sistema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.repository.RegistroMonitoreoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RegistroMonitoreoService {
    @Autowired
    private RegistroMonitoreoRepository registroMonitoreoRepository;

    public List<RegistroMonitoreo> findAll() {
        return registroMonitoreoRepository.findAll();
    }

    public RegistroMonitoreo findById(Integer id) {
        return registroMonitoreoRepository.findById(id).get();
    }

    public RegistroMonitoreo save(RegistroMonitoreo registroMonitoreo) {
        return registroMonitoreoRepository.save(registroMonitoreo);
    }

    public void delete(Integer id) {
        registroMonitoreoRepository.deleteById(id);
    }
}
