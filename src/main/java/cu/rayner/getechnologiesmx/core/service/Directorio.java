package cu.rayner.getechnologiesmx.core.service;

import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Persona;

import java.util.List;

public interface Directorio {
    void insertarPersona(Persona persona);
    void modificarPersona(Persona persona) throws SearchException;
    void eliminarPersona(Long idPersona) throws SearchException;
    Persona buscarPersona(Long idPersona) throws SearchException;
    List<Persona> listarPersonas();
}
