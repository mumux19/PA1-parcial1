package usecase;

import exception.ExceptionPilotoExiste;
import exception.ExceptionRepositorio;
import input.ICrearPilotoInput;
import model.Piloto;
import output.IGuardarPilotoRepositorio;

import java.time.LocalDate;
import java.util.UUID;

public class CrearPilotoUseCase implements ICrearPilotoInput {


    private IGuardarPilotoRepositorio guardarPilotoRepositorio;

    public CrearPilotoUseCase(IGuardarPilotoRepositorio guardarPilotoRepositorio) {

        this.guardarPilotoRepositorio = guardarPilotoRepositorio;

    }



    @Override
    public boolean crearPiloto(UUID id, String nombre, String apellido, String DNI, LocalDate fechaNacimiento) {
       Piloto elPiloto= Piloto.create(id,nombre,apellido,DNI,fechaNacimiento);

        if(this.guardarPilotoRepositorio.existe(DNI)) {
           throw new ExceptionPilotoExiste("Piloto ya existe");

       }
       if(!this.guardarPilotoRepositorio.guardarPiloto(elPiloto)){
              throw new ExceptionRepositorio("Algo salio mal");

       }


       return true;
    }
}
