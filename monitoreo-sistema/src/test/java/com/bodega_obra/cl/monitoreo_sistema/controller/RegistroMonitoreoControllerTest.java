package com.bodega_obra.cl.monitoreo_sistema.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.service.RegistroMonitoreoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(RegistroMonitoreoController.class)
public class RegistroMonitoreoControllerTest {
    @Autowired
    private MockMvc mockMvc; // Punto de entrada principal a las pruebas

    @MockitoBean
    private RegistroMonitoreoService registroMonitoreoService;

    @Autowired
    private ObjectMapper objectMapper;

    
    // Esta parte del codigo es para que se ejecute el metodo setUp() antes de cada (@BeforeEach) prueba (cada @Test)
    private RegistroMonitoreo registro;

    @BeforeEach
    void setUp() {
        registro = new RegistroMonitoreo();
        registro.setId(1);
        registro.setFechaRegistro(LocalDate.of(2025, 3, 15));
        registro.setHoraRegistro(LocalTime.of(13, 20, 43));
        registro.setTipoEvento("INFO");
        registro.setMensaje("El sistema se actualizo exitosamente.");
        registro.setServicio("Gestion de usuarios");
    }

    /*
    =============================================
        Pruebas de los metodos del controller
    =============================================
    */

    // 1. Prueba de metodo enlistarRegistrosMonitoreo()
    @Test
    public void testEnlistarRegistrosMonitoreo() throws Exception {
        /* Comportamiento del Mock: Al ejecutar metodo findAll() del services,
           regresar una lista de tamaño igual a 1 con el objeto 'registro' */
        when(registroMonitoreoService.findAll()).thenReturn(List.of(registro));

        // Se ejecuta metodo GET y se verifica que los datos de 'registro' calcen con los ingresados
        mockMvc.perform(get("/api/v1/registros-monitoreo"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].fechaRegistro").value("2025-03-15"))
            .andExpect(jsonPath("$[0].horaRegistro").value("13:20:43"))
            .andExpect(jsonPath("$[0].tipoEvento").value("INFO"))
            .andExpect(jsonPath("$[0].mensaje").value("El sistema se actualizo exitosamente."))
            .andExpect(jsonPath("$[0].servicio").value("Gestion de usuarios"));
    }

    // 2. Prueba de metodo buscarRegistroMonitoreo()
    @Test
    public void testBuscarRegistroMonitoreo() throws Exception {
        when(registroMonitoreoService.findById(1)).thenReturn(registro);

        mockMvc.perform(get("/api/v1/registros-monitoreo/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.fechaRegistro").value("2025-03-15"))
            .andExpect(jsonPath("$.horaRegistro").value("13:20:43"))
            .andExpect(jsonPath("$.tipoEvento").value("INFO"))
            .andExpect(jsonPath("$.mensaje").value("El sistema se actualizo exitosamente."))
            .andExpect(jsonPath("$.servicio").value("Gestion de usuarios"));
    }

    // 3. Prueba de metodo crearRegistroMonitoreo()
    @Test
    public void testCrearRegistroMonitoreo() throws Exception {
        // Comportamiento mock: Cuando use el metodo 'save' a cualquier objeto de tipo RegistroMonitoreo, regresa el objeto 'registro'
        when(registroMonitoreoService.save(any(RegistroMonitoreo.class))).thenReturn(registro);

        // Se hace peticion PUT con objeto 'registro' en formato JSON y se verifica que respuesta sea correcta
        mockMvc.perform(post("/api/v1/registros-monitoreo")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(registro))
                        )
            .andExpect(status().isCreated()) // Devuelve status 201: CREATED
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.fechaRegistro").value("2025-03-15"))
            .andExpect(jsonPath("$.horaRegistro").value("13:20:43"))
            .andExpect(jsonPath("$.tipoEvento").value("INFO"))
            .andExpect(jsonPath("$.mensaje").value("El sistema se actualizo exitosamente."))
            .andExpect(jsonPath("$.servicio").value("Gestion de usuarios"));
    }

    // 4. Prueba de metodo actualizarRegistroMonitoreo()
    @Test
    public void testActualizarRegistroMonitoreo() throws Exception {
        /* 
            Comportamiento del Mock:
                1. Cuando use el metodo findById(id = 1) del service, debe devolver el objeto 'registro' --> Se cumple que el objeto existe.
                2. Cuando use el metodo 'save', regresa el objeto 'registro'
        */
        when(registroMonitoreoService.findById(1)).thenReturn(registro);
        when(registroMonitoreoService.save(any(RegistroMonitoreo.class))).thenReturn(registro);

        mockMvc.perform(put("/api/v1/registros-monitoreo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registro)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.fechaRegistro").value("2025-03-15"))
            .andExpect(jsonPath("$.horaRegistro").value("13:20:43"))
            .andExpect(jsonPath("$.tipoEvento").value("INFO"))
            .andExpect(jsonPath("$.mensaje").value("El sistema se actualizo exitosamente."))
            .andExpect(jsonPath("$.servicio").value("Gestion de usuarios"));
    }

    // 5. Prueba de metodo eliminarRegistroMonitoreo()
    @Test
    public void testEliminarRegistroMonitoreo() throws Exception {
        // Comportamiento Mock: Haz nada cuando llames al metodo 'delete(id = 1)'
        doNothing().when(registroMonitoreoService).delete(1);

        // Se hace una peticion HTTP DELETE para eliminar el registro con id = 1
        mockMvc.perform(delete("/api/v1/registros-monitoreo/1"))
            .andExpect(status().isNoContent()); // Si funciona, debe devolver codigo de estado 204: No Content
        
        // Se verifica que metodo 'delete' del service (simulado) se haya invocado solo 1 vez
        verify(registroMonitoreoService, times(1)).delete(1);
    }

}