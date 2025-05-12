package cu.rayner.getechnologiesmx.core.service.implement;

import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Persona;
import cu.rayner.getechnologiesmx.core.repository.PersonaRespository;
import cu.rayner.getechnologiesmx.core.service.Directorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorioImplement implements Directorio {

    private final PersonaRespository personaRespository;

    @Autowired
    public DirectorioImplement(PersonaRespository personaRespository) {
        this.personaRespository = personaRespository;
    }

    @Override
    public void insertarPersona(Persona persona) {
        personaRespository.save(persona);
    }

    @Override
    public void modificarPersonabyID(Persona persona) throws SearchException {
        if (!personaRespository.existsById(persona.getId())) {
            throw new SearchException("No se ha podido encontrar la persona a modificar");
        }
        personaRespository.save(persona);

    }

    @Override
    public void eliminarPersonabyID(Long idPersona) throws SearchException {
        if (!personaRespository.existsById(idPersona)) {
            throw new SearchException("No se ha podido encontrar la persona a eliminar");
        }
        personaRespository.deleteById(idPersona);

    }

    @Override
    public Persona buscarPersonabyID(Long idPersona) throws SearchException {
        if (!personaRespository.existsById(idPersona)) {
            throw new SearchException("No se ha podido encontrar la persona");
        }
        return personaRespository.findById(idPersona).get();
    }

    @Override
    public void eliminarPersonabyIdentificacion(String identificacion) throws SearchException {
        if (!personaRespository.existsByIdentificacionEquals(identificacion)) {
            throw new SearchException("No se ha podido encontrar la persona a eliminar");
        }
        personaRespository.deleteByIdentificacionEquals(identificacion);
    }

    @Override
    public Persona buscarPersonabyIdentificacion(String identificacion) throws SearchException {
        if (!personaRespository.existsByIdentificacionEquals(identificacion)) {
            throw new SearchException("No se ha podido encontrar la persona");
        }
        return personaRespository.findByIdentificacionEquals(identificacion).get();
    }

    @Override
    public List<Persona> listarPersonas() {
        return personaRespository.findAll();
    }
}
