package com.bodega_obra.cl.monitoreo_sistema;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.bodega_obra.cl.monitoreo_sistema.model.RegistroMonitoreo;
import com.bodega_obra.cl.monitoreo_sistema.repository.RegistroMonitoreoRepository;

import net.datafaker.Faker;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RegistroMonitoreoRepository registroMonitoreoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();
        
        String[] tiposEvento = {"ERROR", "WARNING", "INFO"};
        String[] mensajesInfo = {
            "El sistema se ha iniciado correctamente.",
            "Se ha completado la actualización del sistema. Versión actual: 1.2.3.",
            "El usuario 'admin' ha iniciado sesión con éxito.",
            "Se ha realizado una copia de seguridad del sistema a las 02:00 AM.",
            "El servicio de monitoreo está funcionando correctamente."
        };
        String[] mensajesWarning = {
            "La utilización de la CPU ha superado el 80%. Monitorear el rendimiento.",
            "El espacio en disco está por debajo del 10%. Considerar la limpieza de archivos.",
            "Se ha detectado un intento de acceso no autorizado. Verifique los registros de seguridad.",
            "El servicio de respaldo no se ha ejecutado en las últimas 24 horas.",
            "La versión del software está desactualizada. Se recomienda actualizar a la última versión."
        };

        String[] mensajesError = {
            "Fallo en la conexión a la base de datos. Verifique las credenciales y la configuración del servidor.",
            "El servicio no pudo iniciarse debido a un error de configuración.",
            "Se ha producido un error inesperado en el módulo de autenticación.",
            "El sistema ha alcanzado el límite de memoria. Se requiere intervención inmediata.",
            "Error al procesar la solicitud del usuario. Código de error: 500."
        };

        String[] nombresMicroservicio = {
            "Auditoria y logs",
            "Gestion inventarios",
            "Gestion mantenimiento",
            "Gestion pedidos",
            "Gestion usuarios",
            "Integracion con proveedores",
            "Monitoreo de sistema",
            "Notificaciones",
            "Registro entrada y salida",
            "Validacion de usuario"
        };

        // Se generan los registros del monitoreo de sistema
        for(int i = 0; i < 20; i++) {
            LocalDate fechaRandom = LocalDate.of(
                2025,                           // año
                faker.number().numberBetween(1, 12), // mes
                faker.number().numberBetween(1, 28)  // dia (termina en 28 por febrero)
            );
            
            LocalTime horaRandom = LocalTime.of(
                faker.number().numberBetween(1, 24), // hora (formato 24 hrs)
                faker.number().numberBetween(0, 59), // minutos
                faker.number().numberBetween(0, 59)  // segundos
            );
            
            String eventoRandom = tiposEvento[random.nextInt(tiposEvento.length)];
            
            String nombreMicroservicioRandom = nombresMicroservicio[random.nextInt(nombresMicroservicio.length)];

            // Se guardan los datos
            RegistroMonitoreo registroMonitoreo = new RegistroMonitoreo();
            registroMonitoreo.setId(i + 1);
            registroMonitoreo.setFechaRegistro(fechaRandom);
            registroMonitoreo.setHoraRegistro(horaRandom);
            registroMonitoreo.setTipoEvento(eventoRandom);

            if(registroMonitoreo.getTipoEvento().equals("ERROR")) {
                registroMonitoreo.setMensaje(mensajesError[random.nextInt(mensajesError.length)]);

            } else if(registroMonitoreo.getTipoEvento().equals("WARNING")) {
                registroMonitoreo.setMensaje(mensajesWarning[random.nextInt(mensajesError.length)]);
                
            } else if(registroMonitoreo.getTipoEvento().equals("INFO")) {
                registroMonitoreo.setMensaje(mensajesInfo[random.nextInt(mensajesError.length)]);
            }

            registroMonitoreo.setServicio(nombreMicroservicioRandom);

            registroMonitoreoRepository.save(registroMonitoreo);
        }
    }
    
}