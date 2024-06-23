package global.controlador;

import global.modelo.EmpleadoContratado;
import global.modelo.Persistencia;
import global.vista.VistaEmpleadoContratado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorEmpleadoContratado {
    private VistaEmpleadoContratado vista = new VistaEmpleadoContratado();
    private ArrayList<EmpleadoContratado> empleadosContratados;
    private int selectedIndex = -1; // Índice del empleado seleccionado

    public ControladorEmpleadoContratado(VistaEmpleadoContratado vista){
        this.vista = vista;
        empleadosContratados = Persistencia.cargarLista("EmpleadoContratados.ser");

        // Verificar si la lista cargada es nula y si es así inicializarla
        if (empleadosContratados == null) {
            empleadosContratados = new ArrayList<>();
        }

        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación del nombre
                String nombre = vista.getNombre();
                if (nombre == null) return; // Termina la ejecución si el nombre no es válido

                // Validación del DNI
                int dni = vista.getDni();
                if (dni == -1) return; // Termina la ejecución si el DNI no es válido

                // Verificar duplicados usando el método estático
                if (EmpleadoContratado.empleadoContratadoDuplicado(empleadosContratados, dni, selectedIndex)) {
                    JOptionPane.showMessageDialog(vista, "Ya existe un empleado con este DNI.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
                    return; // Termina la ejecución si se encuentra un duplicado
                }

                // Validación del Telefono
                int telefono = vista.getTelefono();
                if (telefono == -1) return; // Termina la ejecución si el Telefono no es válido

                // Validación del funcion
                String funcion = vista.getFuncion();
                if (funcion == null) return; // Termina la ejecución si el funcion no es válido

                // Validación del sueldo
                float sueldo = vista.getSueldo();
                if (sueldo == -1) return; // Termina la ejecución si el sueldo no es válido

                // Validación de la fecha de inicio
                LocalDate fechaVencimiento = vista.getFechaContrato();
                if (fechaVencimiento == null) return; // Termina la ejecución si la fecha de inicio no es válida

                EmpleadoContratado empleado = new EmpleadoContratado();
                empleado.setNombre(nombre);
                empleado.setEmpleadoDni(dni);
                empleado.setDireccion(vista.getDireccion());
                empleado.setTelefono(telefono);
                empleado.setFechaVencimiento(fechaVencimiento);
                empleado.setFuncion(funcion);
                empleado.definirSueldo(sueldo);

                if (selectedIndex == -1) {
                    // Añadir nuevo empleado
                    empleadosContratados.add(empleado);
                } else {
                    // Actualizar empleado existente
                    empleadosContratados.set(selectedIndex, empleado);
                    selectedIndex = -1; // Reiniciar índice
                }

                Persistencia.guardarLista(empleadosContratados, "EmpleadoContratados.ser");
                vista.limpiarCampos();
                vista.actualizarLista(empleadosContratados);
            }
        });

        vista.getBtnMostrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleadosContratados = Persistencia.cargarLista("EmpleadoContratados.ser");
                vista.actualizarLista(empleadosContratados);
            }
        });
        vista.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = vista.getListaEmpleados().getSelectedIndex();
                if (selectedIndex != -1) {
                    EmpleadoContratado empleadoSeleccionado = empleadosContratados.get(selectedIndex);
                    vista.cargarEmpleado(empleadoSeleccionado);
                    ControladorEmpleadoContratado.this.selectedIndex = selectedIndex;
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un empleado para editar.");
                }
            }
        });
    }
}
