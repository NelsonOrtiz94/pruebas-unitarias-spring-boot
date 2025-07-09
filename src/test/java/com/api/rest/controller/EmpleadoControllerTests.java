package com.api.rest.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.api.rest.model.Empleado;
import com.api.rest.service.EmpleadoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

// Se usa para probar el controlador EmpleadoController
@WebMvcTest
public class EmpleadoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Se usa para simular el servicio EmpleadoService
    @MockitoBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    // Aquí se pueden agregar los métodos de prueba para el controlador EmpleadoController

    void testGuardarEmpleado() throws Exception {
        //given
        Empleado empleado = Empleado.builder()
                .id(1L)
                .nombre("Christian")
                .apellido("Ramirez")
                .email("c1@gmail.com")
                .build();
        given(empleadoService.saveEmpleado(any(Empleado.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));
        //when
        ResultActions resultActions = mockMvc.perform(
                post("/api/empleados")
                        .contentType(String.valueOf(MediaType.APPLICATION_JSON))
                        .content(objectMapper.writeValueAsString(empleado))
        );

        //then
    }
}
