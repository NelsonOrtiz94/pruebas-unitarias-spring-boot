package com.api.rest.controller;

import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    // Inyección de dependencias del repositorio de empleados
    @Autowired
    private EmpleadoService empleadoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Empleado Controller(@RequestBody Empleado empleado) {
        return empleadoService.saveEmpleado(empleado);
    }

    @GetMapping
    public List<Empleado> listarEmpleados() {
        return empleadoService.getAllEmpleados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable("id") long empleadoId) {
        return empleadoService.getEmpleadoById(empleadoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable("id") long empleadoId, @RequestBody Empleado empleadoActualizado) {
        return empleadoService.getEmpleadoById(empleadoId)
                .map(empleado -> {
                    empleado.setNombre(empleadoActualizado.getNombre());
                    empleado.setApellido(empleadoActualizado.getApellido());
                    empleado.setEmail(empleadoActualizado.getEmail());

                    Empleado updatedEmpleado = empleadoService.updateEmpleado(empleado);
                    return ResponseEntity.ok(updatedEmpleado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEmpleado(@PathVariable("id") long empleadoId) {
        empleadoService.deleteEmpleado(empleadoId);
        return new ResponseEntity<String>("Empleado eliminado exitosamente", HttpStatus.OK);
    }
}
