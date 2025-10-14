package input;

import java.time.LocalDate;
import java.util.UUID;

public interface ICrearPilotoInput {

    boolean crearPiloto(UUID id,String nombre, String apellido, String DNI, LocalDate fechaNacimiento);

}
