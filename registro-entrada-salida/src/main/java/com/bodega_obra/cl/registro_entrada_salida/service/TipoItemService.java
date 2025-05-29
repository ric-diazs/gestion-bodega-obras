package com.bodega_obra.cl.registro_entrada_salida.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.registro_entrada_salida.model.TipoItem;
import com.bodega_obra.cl.registro_entrada_salida.repository.TipoItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TipoItemService {
    @Autowired
    private TipoItemRepository tipoItemRepository;

    public List<TipoItem> findAll() {
        return tipoItemRepository.findAll();
    }

    public TipoItem findById(Integer id) {
        return tipoItemRepository.findById(id).get();
    }

    public TipoItem save(TipoItem tipoItem) {
        return tipoItemRepository.save(tipoItem);
    }

    public void delete(Integer id) {
        tipoItemRepository.deleteById(id);
    }
}
