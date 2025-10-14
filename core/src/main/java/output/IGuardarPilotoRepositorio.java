package output;

import model.Piloto;

public interface IGuardarPilotoRepositorio {
    boolean existe(String dni);
    boolean guardarPiloto(Piloto elPiloto);

}
