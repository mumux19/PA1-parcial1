package model;

import exception.ExceptionNombrePiloto;

import java.time.LocalDate;
import java.util.UUID;

public class Piloto {
    private UUID licencia;
    private String nombre;
    private String apellido;
    private String DNI;
    private LocalDate fechaNacimiento;


    private Piloto(UUID licencia, String nombre, String apellido, String DNI, LocalDate fechaNacimiento) {
        this.licencia = licencia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.fechaNacimiento = fechaNacimiento;
    }

    public static Piloto create(UUID licencia, String nombre, String apellido, String DNI, LocalDate fechaNacimiento) {

        if(nombre == null || nombre.isBlank()) {
            throw new ExceptionNombrePiloto("El nombre no puede ser nulo ni vacio");
        }

        return new Piloto(licencia, nombre, apellido, DNI, fechaNacimiento);

    }

    public String getNombre() {
        return nombre;
    }


    public String getApellido() {
        return this.apellido;
    }

    public String getDocumento() {
        return DNI;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public UUID getId() {
        return licencia;
    }
}
