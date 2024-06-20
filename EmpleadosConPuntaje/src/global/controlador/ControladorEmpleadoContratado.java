package global.controlador;

import global.modelo.Curso;
import global.modelo.EmpleadoContratado;
import global.modelo.EmpleadoPermanente;
import global.modelo.Persistencia;
import global.vista.VistaEmpleadoContratado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                EmpleadoContratado empleado = new EmpleadoContratado();
                empleado.setNombre(vista.getNombre());
                empleado.setEmpleadoDni(vista.getDni());
                empleado.setDireccion(vista.getDireccion());
                empleado.setTelefono(vista.getTelefono());
                empleado.setFechaVencimiento(vista.getFechaContrato());
                empleado.setFuncion(vista.getFuncion());
                empleado.definirSueldo(vista.getSueldo());

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
