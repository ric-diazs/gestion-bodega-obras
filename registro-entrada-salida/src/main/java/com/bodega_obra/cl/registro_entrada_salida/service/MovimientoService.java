package com.bodega_obra.cl.registro_entrada_salida.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.registro_entrada_salida.model.Movimiento;
import com.bodega_obra.cl.registro_entrada_salida.repository.MovimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    // Metodos CRUD
    public List<Movimiento> findAll() {
        return movimientoRepository.findAll();
    }

    public Movimiento findById(Integer id) {
        return movimientoRepository.findById(id).get();
    }

    public Movimiento save(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public void delete(Integer id) {
        movimientoRepository.deleteById(id);
    }
}
