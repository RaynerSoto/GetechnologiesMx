package cu.rayner.getechnologiesmx.core.repository;

import cu.rayner.getechnologiesmx.core.model.Persona;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonaRespository extends JpaRepository<Persona, Long> {
    boolean existsByIdentificacionEquals(String identificacion);

    void deleteByIdentificacionEquals(String identificacion);

    Optional<Persona> findByIdentificacionEquals(String identificacion);

    String id(Long id);
}
