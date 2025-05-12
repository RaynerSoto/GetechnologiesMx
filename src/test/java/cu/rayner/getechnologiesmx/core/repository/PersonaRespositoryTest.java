package cu.rayner.getechnologiesmx.core.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonaRespositoryTest {

    private final PersonaRespository personaRespository;

    @Autowired
    PersonaRespositoryTest(PersonaRespository personaRespository) {
        this.personaRespository = personaRespository;
    }

    @Test
    public void listarPersonas(){
        assertNotNull(personaRespository.findAll());
    }

}