package com.bodega_obra.cl.registro_entrada_salida.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.registro_entrada_salida.model.TipoMovimiento;
import com.bodega_obra.cl.registro_entrada_salida.repository.TipoMovimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoMovimientoService {
    @Autowired
    private TipoMovimientoRepository tipoMovimientoRepository;

    public List<TipoMovimiento> findAll() {
        return tipoMovimientoRepository.findAll();
    }

    public TipoMovimiento findById(Integer id) {
        return tipoMovimientoRepository.findById(id).get();
    }

    public TipoMovimiento save(TipoMovimiento tipoMovimiento) {
        return tipoMovimientoRepository.save(tipoMovimiento);
    }

    public void delete(Integer id) {
        tipoMovimientoRepository.deleteById(id);
    }
    
}
