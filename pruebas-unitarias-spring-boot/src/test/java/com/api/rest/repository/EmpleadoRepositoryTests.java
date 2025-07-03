package com.api.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.api.rest.model.Empleado;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// This annotation is used to configure a test that focuses on JPA components
@DataJpaTest
public class EmpleadoRepositoryTests {

    @Autowired
    private EmpleadoRepository empleadoRepository;

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

}
