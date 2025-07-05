package com.api.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.rest.model.Empleado;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

// This annotation is used to configure a test that focuses on JPA components
@DataJpaTest
public class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado empleado;

    @BeforeEach
    void setUp() {
        empleado = Empleado.builder()
                .nombre("Empleado 1")
                .apellido("Apellido 1")
                .email("c1@gmail.com")
                .build();
    }

    @DisplayName("Test para guardar un empleado")
    @Test
    void testGuardarEmpleado(){
        // BDD (Behavior Driven Development) - Se centra en el comportamiento esperado del sistema

        // Given - Un empleado con datos específicos (precondiciones)
        Empleado empleado1 = Empleado.builder()
                .nombre("Juan")
                .apellido("Pérez")
                .email("p12@gmail.com")
                .build();

        // When - Se guarda el empleado en la base de datos
        Empleado empleadoGuardado = empleadoRepository.save(empleado1);

        // Then - Se espera que el empleado se guarde correctamente
        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para listar empelados")
    @Test
    void testListarEmpleados() {
        // Given - Se crean y guardan varios empleados
        Empleado empleado1 = Empleado.builder()
                .nombre("Julen")
                .apellido("Oliva")
                .email("j12@gmail.com")
                .build();

        empleadoRepository.save(empleado1);
        empleadoRepository.save(empleado);

        // When - Se guardan los empleados en la base de datos
        List<Empleado> listaEmpleados = empleadoRepository.findAll();

        // Then - Se espera que la lista de empleados no esté vacía
        assertThat(listaEmpleados).isNotNull();
        assertThat(listaEmpleados.size()).isEqualTo(2);
    }

    @DisplayName("Test para buscar empleado por ID")
    @Test
    void testBuscarEmpleadoPorId() {

        empleadoRepository.save(empleado);

        // When - Se busca el empleado por su ID
        Empleado empleadoBuscado = empleadoRepository.findById(empleado.getId()).get();

        // Then - Se espera que el empleado no sea nulo y que su ID coincida
        assertThat(empleadoBuscado).isNotNull();

    }

    @DisplayName("Test para actualizar empleado por ID")
    @Test
    void testActualizarEmpleado() {

        // Given - Se guarda un empleado
        empleadoRepository.save(empleado);

        // When - Se actualiza el nombre del empleado
        Empleado empleadoGuardado = empleadoRepository.findById(empleado.getId()).get();
        empleadoGuardado.setEmail("123j@gmail.com");
        empleadoGuardado.setNombre("Luis actualizado");
        empleadoGuardado.setApellido("Diaz actualizado");
        Empleado empleadoActualizado = empleadoRepository.save(empleadoGuardado);

        // Then - Se espera que el nombre del empleado actualizado sea correcto
        assertThat(empleadoActualizado.getEmail()).isEqualTo("123j@gmail.com");
        assertThat(empleadoActualizado.getNombre()).isEqualTo("Luis actualizado");
    }
}
