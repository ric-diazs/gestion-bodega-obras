package com.bodega_obra.cl.monitoreo_sistema.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bodega_obra.cl.monitoreo_sistema.assemblers.RegistroMonitoreoModelAssembler;
import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.service.RegistroMonitoreoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v2/registros-monitoreo")
@Tag(name = "Registros de Monitoreo (con implementación HATEOAS)", description = "Operaciones CRUD en registros del microservicio de monitoreo de sistema.")
public class RegistroMonitoreoControllerV2 {
    @Autowired
    private RegistroMonitoreoService registroMonitoreoService;

    @Autowired
    private RegistroMonitoreoModelAssembler registroAssembler;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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
    public ResponseEntity<CollectionModel<EntityModel<RegistroMonitoreo>>> enlistarRegistrosMonitoreo() {
        List<EntityModel<RegistroMonitoreo>> registrosMonitoreo = registroMonitoreoService.findAll().stream()
            .map(registroAssembler::toModel)
            .collect(Collectors.toList());

        if(registrosMonitoreo.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        CollectionModel<EntityModel<RegistroMonitoreo>> colecciones = CollectionModel.of(
                registrosMonitoreo,
                linkTo(methodOn(RegistroMonitoreoController.class).enlistarRegistrosMonitoreo()).withSelfRel()
            );

        return ResponseEntity.ok(colecciones);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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
    public ResponseEntity<EntityModel<RegistroMonitoreo>> buscarRegistroMonitoreo(@PathVariable Integer id) {
        try {
            RegistroMonitoreo registroMonitoreo = registroMonitoreoService.findById(id);
            return ResponseEntity.ok(registroAssembler.toModel(registroMonitoreo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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
    public ResponseEntity<EntityModel<RegistroMonitoreo>> crearRegistroMonitoreo(@RequestBody RegistroMonitoreo registroMonitoreo) {
        RegistroMonitoreo nuevoRegistro = registroMonitoreoService.save(registroMonitoreo);
        
        return ResponseEntity
            .created(linkTo(methodOn(RegistroMonitoreoController.class).buscarRegistroMonitoreo(nuevoRegistro.getId())).toUri())
            .body(registroAssembler.toModel(nuevoRegistro));
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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
    public ResponseEntity<EntityModel<RegistroMonitoreo>> actualizarRegistroMonitoreo(@PathVariable Integer id, @RequestBody RegistroMonitoreo registroMonitoreo) {
        try {
            RegistroMonitoreo registroModificar = registroMonitoreoService.findById(id);
            registroModificar.setId(id);
            registroModificar.setFechaRegistro(registroMonitoreo.getFechaRegistro());
            registroModificar.setHoraRegistro(registroMonitoreo.getHoraRegistro());
            registroModificar.setTipoEvento(registroMonitoreo.getTipoEvento());
            registroModificar.setMensaje(registroMonitoreo.getMensaje());
            registroModificar.setServicio(registroMonitoreo.getServicio());

            registroMonitoreoService.save(registroModificar);
            return ResponseEntity.ok(registroAssembler.toModel(registroMonitoreo));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaTypes.HAL_JSON_VALUE})
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