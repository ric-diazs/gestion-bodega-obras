package com.bodega_obra.cl.monitoreo_sistema.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.repository.RegistroMonitoreoRepository;

@SpringBootTest
public class RegistroMonitoreoServiceTest {
    @Autowired
    private RegistroMonitoreoService registroMonitoreoService;

    // Mock para simular comportamiento del repositorio de RegistroMonitoreo
    @MockitoBean
    private RegistroMonitoreoRepository registroMonitoreoRepository;

    
    /* 
    ======================================
        Pruebas de metodos del service
    ======================================
    */
    
    // 1. Prueba de metodo findAll()
    @Test
    public void testFindAll() {
        LocalDate fechaRegistro = LocalDate.of(2025, 3, 15); // fecha: "2025-03-15"
        LocalTime horaRegistro = LocalTime.of(13, 20, 43);      // hora: "13:20:43"
        String tipoEvento = "INFO";
        String mensaje = "El sistema se actualizo exitosamente.";
        String servicio = "Gestion de usuarios";

        // Se define comportamiento del mock repositorio: Cuando se llame su metodo "findAll", devuelve un objeto de RegistroMonitoreo
        when(registroMonitoreoRepository.findAll())
            .thenReturn(List.of(new RegistroMonitoreo(1, fechaRegistro, horaRegistro, tipoEvento, mensaje, servicio)));
        
        List<RegistroMonitoreo> registrosMonitoreo = registroMonitoreoService.findAll();

        // Pruebas para ver si metodo findAll() devolvio el objeto RegistroMonitoreo 
        assertNotNull(registrosMonitoreo);                   // Si funciono, no debe ser null (sera null si esta vacio)
        assertEquals(1, registrosMonitoreo.size()); // Si funciono, el tamaño de la lista debe ser igual a 1
    }

    // 2. Prueba de metodo findById()
    @Test
    public void testFindById() {
        int id = 1;
        LocalDate fechaRegistro = LocalDate.of(2025, 3, 15); // fecha: "2025-03-15"
        LocalTime horaRegistro = LocalTime.of(13, 20, 43);      // hora: "13:20:43"
        String tipoEvento = "INFO";
        String mensaje = "El sistema se actualizo exitosamente.";
        String servicio = "Gestion de usuarios";
        
        RegistroMonitoreo registroMonitoreo = new RegistroMonitoreo(id, fechaRegistro, horaRegistro, tipoEvento, mensaje, servicio);

        when(registroMonitoreoRepository.findById(id)).thenReturn(Optional.of(registroMonitoreo));

        RegistroMonitoreo registroEncontrado = registroMonitoreoService.findById(id);

        assertNotNull(registroEncontrado);
        assertEquals(id, registroEncontrado.getId());
    }

    // 3. Prueba de metodo save()
    @Test
    public void testSave() {
        int id = 1;
        LocalDate fechaRegistro = LocalDate.of(2025, 3, 15); // fecha: "2025-03-15"
        LocalTime horaRegistro = LocalTime.of(13, 20, 43);      // hora: "13:20:43"
        String tipoEvento = "INFO";
        String mensaje = "El sistema se actualizo exitosamente.";
        String servicio = "Gestion de usuarios";

        RegistroMonitoreo registroMonitoreo = new RegistroMonitoreo(id, fechaRegistro, horaRegistro, tipoEvento, mensaje, servicio);

        when(registroMonitoreoRepository.save(registroMonitoreo)).thenReturn(registroMonitoreo);

        RegistroMonitoreo registroGuardado = registroMonitoreoService.save(registroMonitoreo);

        assertNotNull(registroGuardado);
        assertEquals("El sistema se actualizo exitosamente.", registroGuardado.getMensaje());
    }

    // 4. Prueba de metodo delete
    @Test
    public void testDeleteById() {
        int id = 1;

        // Mock: Haz nada cuando se llame al metodo deleteById()
        doNothing().when(registroMonitoreoRepository).deleteById(id);

        // Llamado al metodo delete() del service
        registroMonitoreoService.delete(id);

        // Se verifica que el metodo deleteById() del repository fue llamado solo una vez (a partir del delete() del service)
        verify(registroMonitoreoRepository, times(1)).deleteById(id);
    }
}