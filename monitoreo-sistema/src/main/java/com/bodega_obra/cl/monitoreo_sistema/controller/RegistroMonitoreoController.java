package com.bodega_obra.cl.monitoreo_sistema.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.service.RegistroMonitoreoService;

@RestController
@RequestMapping("/api/v1/registros-monitoreo")
@Tag(name = "Registros de Monitoreo", description = "Operaciones CRUD en registros del microservicio de monitoreo de sistema.")
public class RegistroMonitoreoController {
    @Autowired
    private RegistroMonitoreoService registroMonitoreoService;

    @GetMapping
    @Operation(summary = "Obtener todos los registros.", description = "Regresa todos los registros del microservicio de monitoreo de sistema.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Se han encontrado todos los registros del monitoreo.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegistroMonitoreo.class)
                )
            ),
            @ApiResponse(responseCode = "204", description = "No hay registros de monitoreo.")
        }
    )
    public ResponseEntity<List<RegistroMonitoreo>> enlistarRegistrosMonitoreo() {
        List<RegistroMonitoreo> registrosMonitoreo = registroMonitoreoService.findAll();

        if(registrosMonitoreo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(registrosMonitoreo);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener un registro.", description = "Se obtiene un registro del microservicio mediante su número de ID.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "El registro se encontró exitosamente.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegistroMonitoreo.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado.")
        }
    )
    public ResponseEntity<RegistroMonitoreo> buscarRegistroMonitoreo(@PathVariable Integer id) {
        try {
            RegistroMonitoreo registroMonitoreo = registroMonitoreoService.findById(id);
            return ResponseEntity.ok(registroMonitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping
    @Operation(summary = "Crear un nuevo registro.", description = "Se crea un nuevo registro en el microservicio.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "El registro se ha creado exitosamente.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegistroMonitoreo.class)
                )
            ),
            @ApiResponse(responseCode = "400", description = "No se pudo crear el registro.")
        }
    )
    public ResponseEntity<RegistroMonitoreo> crearRegistroMonitoreo(@RequestBody RegistroMonitoreo registroMonitoreo) {
        RegistroMonitoreo nuevoRegistro = registroMonitoreoService.save(registroMonitoreo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un registro.", description = "Se actualiza un registro del microservicio a partir de su número de ID.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "El registro se ha actualizado exitosamente.",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegistroMonitoreo.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Registro no encontrado.")
        }
    )
    public ResponseEntity<RegistroMonitoreo> actualizarRegistroMonitoreo(@PathVariable Integer id, @RequestBody RegistroMonitoreo registroMonitoreo) {
        try {
            RegistroMonitoreo registroModificar = registroMonitoreoService.findById(id);
            registroModificar.setId(id);
            registroModificar.setFechaRegistro(registroMonitoreo.getFechaRegistro());
            registroModificar.setHoraRegistro(registroMonitoreo.getHoraRegistro());
            registroModificar.setTipoEvento(registroMonitoreo.getTipoEvento());
            registroModificar.setMensaje(registroMonitoreo.getMensaje());
            registroModificar.setServicio(registroMonitoreo.getServicio());

            registroMonitoreoService.save(registroModificar);
            return ResponseEntity.ok(registroMonitoreo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un registro.", description = "Se elimina un registro del microservicio a partir de su número de ID.")
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "El registro se ha eliminado exitosamente."
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Registro no encontrado."
            )
        }
    )
    public ResponseEntity<?> eliminarRegistroMonitoreo(@PathVariable Integer id) {
        try {
            registroMonitoreoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
