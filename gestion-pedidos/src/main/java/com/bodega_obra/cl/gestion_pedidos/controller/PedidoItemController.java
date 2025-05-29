package com.bodega_obra.cl.gestion_pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodega_obra.cl.gestion_pedidos.model.PedidoItem;
import com.bodega_obra.cl.gestion_pedidos.service.PedidoItemService;

@RestController
@RequestMapping("/api/v1/pedidos-item")
public class PedidoItemController {
    @Autowired
    private PedidoItemService pedidoItemService;

    @GetMapping
    public ResponseEntity<List<PedidoItem>> enlistarPedidosItem() {
        List<PedidoItem> pedidosItem = pedidoItemService.findAll();

        if(pedidosItem.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pedidosItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoItem> buscarPedidoItem(@PathVariable Integer id) {
        try {
            PedidoItem pedidoItem = pedidoItemService.findById(id);
            return ResponseEntity.ok(pedidoItem);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PedidoItem> crearPedidoItem(@RequestBody PedidoItem pedidoItem) {
        PedidoItem nuevoPedidoItem = pedidoItemService.save(pedidoItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedidoItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoItem> actualizarPedidoItem(@PathVariable Integer id, @RequestBody PedidoItem pedidoItem) {
        try {
            PedidoItem pedidoItemModificar = pedidoItemService.findById(id);
            pedidoItemModificar.setId(id);
            pedidoItemModificar.setCantidad(pedidoItem.getCantidad());
            pedidoItemModificar.setPedido(pedidoItem.getPedido());
            pedidoItemModificar.setItemInventario(pedidoItem.getItemInventario());

            pedidoItemService.save(pedidoItemModificar);

            return ResponseEntity.ok(pedidoItem);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedidoItem(@PathVariable Integer id) {
        try {
            pedidoItemService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
