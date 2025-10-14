package model;

import exception.*;

import java.time.LocalDate;
import java.time.Period;
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
        if(licencia==null){
            throw new ExceptionIdPiloto("El id no puede ser nulo");
        }

        if(nombre == null || nombre.isBlank()) {
            throw new ExceptionNombrePiloto("El nombre no puede ser nulo ni vacio");
        }

        if(apellido == null || apellido.trim().isBlank()) {
            throw new ExceptionApellidoPiloto("El apellido no puede ser nulo ni vacio");
        }
        if(DNI == null || DNI.trim().isBlank()) {
            throw new ExceptionDniPiloto("El DNI no puede ser nulo ni vacio");
        }

        if(fechaNacimiento==null){
            throw new ExceptionFechaPiloto("La fecha de nacimiento no puede ser nula");
        }


        LocalDate fechaActual=LocalDate.now();
        int edad= Period.between(fechaNacimiento,fechaActual).getYears();

        if(edad<18){
            throw new ExceptionEdadPiloto("El piloto debe ser mayor de edad");
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
