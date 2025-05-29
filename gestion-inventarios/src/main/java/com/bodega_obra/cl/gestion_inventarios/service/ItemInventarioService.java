package com.bodega_obra.cl.gestion_inventarios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.gestion_inventarios.model.ItemInventario;
import com.bodega_obra.cl.gestion_inventarios.repository.ItemInventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemInventarioService {
    // Inyecta automaticamente una instancia de ItemInventarioRepository sin tener que
    // iniciarlo con el metodo `new ItemInventarioRepository()`
    @Autowired 
    private ItemInventarioRepository itemInventarioRepository; // Objeto `ItemInventarioRepository` inicializado

    // Metodos CRUD
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
