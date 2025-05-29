package com.bodega_obra.cl.gestion_pedidos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bodega_obra.cl.gestion_pedidos.model.PedidoItem;
import com.bodega_obra.cl.gestion_pedidos.repository.PedidoItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoItemService {
    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    public List<PedidoItem> findAll() {
        return pedidoItemRepository.findAll();
    }

    public PedidoItem findById(Integer id) {
        return pedidoItemRepository.findById(id).get();
    }

    public PedidoItem save(PedidoItem pedidoItem) {
        return pedidoItemRepository.save(pedidoItem);
    }

    public void delete(Integer id) {
        pedidoItemRepository.deleteById(id);
    }
}
