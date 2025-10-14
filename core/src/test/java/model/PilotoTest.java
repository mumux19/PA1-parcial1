package model;

import exception.ExceptionNombrePiloto;
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

        Assertions.assertThrows(ExceptionNombrePiloto.class, () -> Piloto.create(licencia," ", "Colapinto", "123456789", LocalDate.of(2002, 2, 3)), "El nombre no puede ser nulo ni vacio");


    }



}
