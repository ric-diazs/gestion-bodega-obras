package com.bodega_obra.cl.gestion_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.gestion_pedidos.model.ItemInventario;
import com.bodega_obra.cl.gestion_pedidos.repository.ItemInventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemInventarioService {
    @Autowired
    private ItemInventarioRepository itemInventarioRepository;

    public List<ItemInventario> findAll() {
        return itemInventarioRepository.findAll();
    }

    public ItemInventario findById(Integer id) {
        return itemInventarioRepository.findById(id).get();
    }

    public ItemInventario save(ItemInventario itemInventario) {
        return itemInventarioRepository.save(itemInventario);
    }

    public void delete(Integer id) {
        itemInventarioRepository.deleteById(id);
    }
}
