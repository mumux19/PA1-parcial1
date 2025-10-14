package usecase;

import exception.ExceptionPilotoExiste;
import exception.ExceptionRepositorio;
import model.Piloto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import output.IGuardarPilotoRepositorio;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrearPilotoUseCaseTest {

    @Mock
    IGuardarPilotoRepositorio guardarPilotoRepositorio;

    @Test
    public void crearPiloto_PilotoNoExiste() {
        CrearPilotoUseCase crearPilotoUseCase= new CrearPilotoUseCase(guardarPilotoRepositorio);

        when(guardarPilotoRepositorio.existe("12345678")).thenReturn(false);
        when(guardarPilotoRepositorio.guardarPiloto(any(Piloto.class))).thenReturn(true);

        UUID licencia= UUID.randomUUID();
        boolean resultado=crearPilotoUseCase.crearPiloto(licencia,"Franco","Colapinto","12345678", LocalDate.of(2001,2,5));

        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearPiloto_PilotoDuplicado(){

        CrearPilotoUseCase crearPilotoUseCase=new CrearPilotoUseCase(guardarPilotoRepositorio);

        when(guardarPilotoRepositorio.existe("12345678")).thenReturn(true);

        ExceptionPilotoExiste exceptionPilotoExiste = Assertions.assertThrows(ExceptionPilotoExiste.class, () -> {
            crearPilotoUseCase.crearPiloto(UUID.randomUUID(), "Franco", "Colapinto", "12345678", LocalDate.of(2001, 2, 5));
        }, "El piloto ya existe");

        verify(guardarPilotoRepositorio,never()).guardarPiloto(any(Piloto.class));
    }

    @Test
    public void crearPiloto_AtributosInvalidos(){
        CrearPilotoUseCase crearPilotoUseCase=new CrearPilotoUseCase(guardarPilotoRepositorio);

        verify(guardarPilotoRepositorio,never()).existe(any());
        verify(guardarPilotoRepositorio,never()).guardarPiloto(any());

        Assertions.assertThrows(RuntimeException.class,()->crearPilotoUseCase.crearPiloto(UUID.randomUUID(),"","  ","12345678", LocalDate.of(2001,2,5))) ;

    }

    @Test
    public void CrearPiloto_RepositorioFalla(){
        CrearPilotoUseCase crearPilotoUseCase=new CrearPilotoUseCase(guardarPilotoRepositorio);

        when(guardarPilotoRepositorio.existe("12345678")).thenReturn(false);
        when(guardarPilotoRepositorio.guardarPiloto(any())).thenReturn(false);

        Assertions.assertThrows(ExceptionRepositorio.class, ()->crearPilotoUseCase.crearPiloto(UUID.randomUUID(),"Franco","Colapinto","12345678",LocalDate.of(2002,2,5)));



    }


}
