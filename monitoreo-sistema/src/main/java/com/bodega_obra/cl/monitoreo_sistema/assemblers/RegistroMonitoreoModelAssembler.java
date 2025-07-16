package com.bodega_obra.cl.monitoreo_sistema.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.bodega_obra.cl.monitoreo_sistema.controller.RegistroMonitoreoController;
import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
@Component
public class RegistroMonitoreoModelAssembler implements RepresentationModelAssembler<RegistroMonitoreo, EntityModel<RegistroMonitoreo>> {
    // Ensamblador de recursos: Permite crear los enlaces HATEOAS en API
    @Override
    public EntityModel<RegistroMonitoreo> toModel(RegistroMonitoreo registro) {
        return EntityModel.of(
            registro,
            linkTo(methodOn(RegistroMonitoreoController.class).enlistarRegistrosMonitoreo()).withRel("registros"),
            linkTo(methodOn(RegistroMonitoreoController.class).buscarRegistroMonitoreo(registro.getId())).withSelfRel(),
            linkTo(methodOn(RegistroMonitoreoController.class).actualizarRegistroMonitoreo(registro.getId(), registro)).withRel("actualizar"),
            linkTo(methodOn(RegistroMonitoreoController.class).eliminarRegistroMonitoreo(registro.getId())).withRel("eliminar")
        );
    }
}