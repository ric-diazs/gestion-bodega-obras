package com.bodega_obra.cl.gestion_usuarios.controller;

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

import com.bodega_obra.cl.gestion_usuarios.model.Rol;
import com.bodega_obra.cl.gestion_usuarios.service.RolService;

@RestController
@RequestMapping("/api/v1/roles")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> enlistarRoles() {
        List<Rol> roles = rolService.findAll();

        if(roles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rol> buscarRol(@PathVariable Integer id) {
        try {
            Rol rol = rolService.findById(id);

            return ResponseEntity.ok(rol);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Rol> crearRol(@RequestBody Rol rol) {
        Rol nuevoRol = rolService.save(rol);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol rol) {
        try {
            Rol rolV2 = rolService.findById(id);
            rolV2.setId(id);
            rolV2.setNombreRol(rol.getNombreRol());
            rolV2.setPermisos(rol.getPermisos());
            /*
                * Es mejor gestionar a los usuarios desde 'UsuarioController'
                * que desde 'RolController', porque los usuarios tienen su propia logica.
                //rolV2.setUsuarios(rol.getUsuarios());
            */
            rolService.save(rolV2);
            return ResponseEntity.ok(rol);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarRol(@PathVariable Integer id) {
        try {
            rolService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
