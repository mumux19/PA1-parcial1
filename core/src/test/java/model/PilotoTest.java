package model;

import exception.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

public class PilotoTest {

    @Test
    public void create_success_AtributosValidos() {

        UUID licencia=UUID.randomUUID();
        Piloto elPiloto = Piloto.create(licencia,"Franco", "Colapinto", "123456789", LocalDate.of(2002, 2, 3));

        Assertions.assertNotNull(elPiloto);
        Assertions.assertEquals(licencia, elPiloto.getId());
        Assertions.assertEquals("Franco", elPiloto.getNombre());
        Assertions.assertEquals("Colapinto", elPiloto.getApellido());
        Assertions.assertEquals("123456789", elPiloto.getDocumento());
        Assertions.assertEquals(LocalDate.of(2002, 2, 3), elPiloto.getFechaNacimiento());


    }

    @Test
    public void create_AtributosNoValidos(){

        UUID licencia=UUID.randomUUID();

        Assertions.assertThrows(ExceptionIdPiloto.class, () -> Piloto.create(null,"Franco", "Colapinto", "123456789", LocalDate.of(2002, 2, 3)), "El ID no puede ser nulo");

        Assertions.assertThrows(ExceptionNombrePiloto.class, () -> Piloto.create(licencia,"  ", "Colapinto", "123456789", LocalDate.of(2002, 2, 3)), "El nombre no puede ser nulo ni vacio");

       Assertions.assertThrows(ExceptionApellidoPiloto.class, () -> Piloto.create(licencia,"Franco", "  ", "123456789", LocalDate.of(2002, 2, 3)), "El apellido no puede ser nulo ni vacio");
       Assertions.assertThrows(ExceptionDniPiloto.class, () -> Piloto.create(licencia,"Franco", "Colapinto", "  ", LocalDate.of(2002, 2, 3)), "El DNI no puede ser nulo ni vacio");
       Assertions.assertThrows(ExceptionFechaPiloto.class, () -> Piloto.create(licencia,"Franco", "Colapinto", "123456789", null), "La fecha de nacimiento no puede ser nula" );

    }
    @Test
    public void create_MenorDeEdad(){
        UUID licencia=UUID.randomUUID();

        Assertions.assertThrows(ExceptionEdadPiloto.class, () -> Piloto.create(licencia,"Franco", "Colapinto", "123456789",LocalDate.of(2010,2,6)), "El piloto debe ser mayor de edad" );

    }



}
