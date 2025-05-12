package cu.rayner.getechnologiesmx.core.repository;

import cu.rayner.getechnologiesmx.core.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRespository extends JpaRepository<Persona, Long> {
}
