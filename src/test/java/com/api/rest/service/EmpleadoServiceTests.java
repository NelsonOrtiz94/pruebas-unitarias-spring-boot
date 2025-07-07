package com.api.rest.service;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTests {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

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
    void testGuardarEmpleado() {

        //Given
        given(empleadoRepository.findByEmail(empleado.getEmail()))
                        .willReturn(Optional.empty());
        given(empleadoRepository.save(empleado)).willReturn(empleado);

        //When
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);

        //Then
        assertThat(empleadoGuardado).isNotNull();
    }
}
