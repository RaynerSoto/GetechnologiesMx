package cu.rayner.getechnologiesmx.core.service;

import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Persona;

import java.util.List;

public interface Directorio {
    void insertarPersona(Persona persona);
    void modificarPersonabyID(Persona persona) throws SearchException;
    void eliminarPersonabyID(Long idPersona) throws SearchException;
    Persona buscarPersonabyID(Long idPersona) throws SearchException;
    void eliminarPersonabyIdentificacion(String identificacion) throws SearchException;
    Persona buscarPersonabyIdentificacion(String identificacion) throws SearchException;
    List<Persona> listarPersonas();
}
