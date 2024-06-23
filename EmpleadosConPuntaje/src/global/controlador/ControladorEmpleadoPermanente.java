package global.controlador;


import global.modelo.Curso;
import global.modelo.EmpleadoContratado;
import global.modelo.EmpleadoPermanente;
import global.modelo.Persistencia;
import global.vista.VistaEmpleadoPermanente;
import global.vista.VistaSeleccionCursos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorEmpleadoPermanente {
    private VistaEmpleadoPermanente vista = new VistaEmpleadoPermanente();
    private ArrayList<EmpleadoPermanente> empleadosPermanentes;
    private ArrayList<Curso> cursosList;
    private int selectedIndex = -1; // Índice del empleado seleccionado

    public ControladorEmpleadoPermanente(VistaEmpleadoPermanente vista){
        this.vista = vista;
        empleadosPermanentes = Persistencia.cargarLista("EmpleadoPermanentes.ser");
        cursosList = Persistencia.cargarLista("Cursos.ser");

        // Verificar si la lista cargada es nula y si es así inicializarla
        if (empleadosPermanentes == null) {
            empleadosPermanentes = new ArrayList<>();
        }
        if (cursosList == null) {
            cursosList = new ArrayList<>();
        }
        vista.getBtnGuardar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validación del DNI
                int dni = vista.getDni();
                if (dni == -1) return; // Termina la ejecución si el DNI no es válido

                // Verificar duplicados usando el método estático
                if (EmpleadoPermanente.empleadoPermanenteDuplicado(empleadosPermanentes, dni, selectedIndex)) {
                    JOptionPane.showMessageDialog(vista, "Ya existe un empleado con este DNI.", "Error de Duplicado", JOptionPane.ERROR_MESSAGE);
                    return; // Termina la ejecución si se encuentra un duplicado
                }

                // Validación del nombre
                String nombre = vista.getNombre();
                if (nombre == null) return; // Termina la ejecución si el nombre no es válido

                // Validación del Telefono
                int telefono = vista.getTelefono();
                if (telefono == -1) return; // Termina la ejecución si el Telefono no es válido

                // Validación del sueldo
                float sueldo = vista.getSueldo();
                if (sueldo == -1) return; // Termina la ejecución si el sueldo no es válido

                EmpleadoPermanente empleado = new EmpleadoPermanente();
                empleado.setNombre(nombre);
                empleado.setEmpleadoDni(dni);
                empleado.setDireccion(vista.getDireccion());
                empleado.setTelefono(telefono);
                empleado.definirSueldo(sueldo);

                if (selectedIndex == -1) {
                    // Añadir nuevo empleado
                    empleadosPermanentes.add(empleado);
                } else {
                    // Actualizar empleado existente
                    empleadosPermanentes.set(selectedIndex, empleado);
                    selectedIndex = -1; // Reiniciar índice
                }

                Persistencia.guardarLista(empleadosPermanentes, "EmpleadoPermanentes.ser");
                vista.limpiarCampos();
                vista.actualizarLista(empleadosPermanentes);
            }
        });

        vista.getBtnMostrar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empleadosPermanentes = Persistencia.cargarLista("EmpleadoPermanentes.ser");
                vista.actualizarLista(empleadosPermanentes);
            }
        });
        vista.getBtnEditar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = vista.getListaEmpleados().getSelectedIndex();
                if (selectedIndex != -1) {
                    EmpleadoPermanente empleadoSeleccionado = empleadosPermanentes.get(selectedIndex);
                    vista.cargarEmpleado(empleadoSeleccionado);
                    ControladorEmpleadoPermanente.this.selectedIndex = selectedIndex;
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un empleado para editar.");
                }
            }
        });

        vista.getBtnRealizarCurso().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = vista.getListaEmpleados().getSelectedIndex();
                if (selectedIndex != -1) {
                    EmpleadoPermanente empleadoPermanenteSeleccionado = empleadosPermanentes.get(selectedIndex);
                    VistaSeleccionCursos dialog = new VistaSeleccionCursos(vista, cursosList);
                    ControladorSeleccionCurso controladorSeleccion = new ControladorSeleccionCurso(dialog, empleadoPermanenteSeleccionado, cursosList,empleadosPermanentes);
                    dialog.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un empleado para que realice un curso.");
                }
                vista.actualizarLista(empleadosPermanentes);
            }
        });
    }
}

