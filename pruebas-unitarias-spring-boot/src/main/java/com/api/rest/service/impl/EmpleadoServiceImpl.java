package com.api.rest.service.impl;

import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    // Inyección de dependencias del repositorio de empleados
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado saveEmpleado(Empleado empleado) {
        Optional<Empleado> empleadoGuardado = empleadoRepository.findByEmail(empleado.getEmail());
        if (empleadoGuardado.isPresent()) {
            throw new RuntimeException("El empleado con el email " + empleado.getEmail() + " ya existe.");
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        return List.of();
    }

    @Override
    public Optional<Empleado> getEmpleadoById(long id) {
        return Optional.empty();
    }

    @Override
    public Empleado updateEmpleado(Empleado empleadoActualizado) {
        return null;
    }

    @Override
    public void deleteEmpleado(long id) {

    }
}
