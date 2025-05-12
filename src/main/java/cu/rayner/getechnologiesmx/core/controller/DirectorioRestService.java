package cu.rayner.getechnologiesmx.core.controller;

import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoEstandar;
import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoObtener;
import cu.rayner.getechnologiesmx.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Persona;
import cu.rayner.getechnologiesmx.core.service.Directorio;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/directorio")
public class DirectorioRestService {
    private final Directorio directorio;

    @Autowired
    public DirectorioRestService(Directorio directorio) {
        this.directorio = directorio;
    }

    @PostMapping("/")
    public ResponseEntity<RespuestaEstandar> insertarPersona(@RequestBody PersonaDtoEstandar personaDtoEstandar){
        directorio.insertarPersona(personaDtoEstandar.toPersona());
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona insertada exitosamente"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> modificarPersona(@RequestBody PersonaDtoEstandar personaDtoEstandar, @PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        directorio.modificarPersona(new Persona(personaDtoEstandar, id));
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona modificada exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> eliminarPersona(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        directorio.eliminarPersona(id);
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona eliminada"));
    }

    @GetMapping("/")
    public ResponseEntity<RespuestaEstandar> listarPersonas(){
        return ResponseEntity.ok().body(new RespuestaEstandar(directorio.listarPersonas().stream().
                map(PersonaDtoObtener::fromPersona)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaEstandar> obtenerPersona(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        return ResponseEntity.ok().body(new RespuestaEstandar(PersonaDtoObtener.fromPersona(directorio.buscarPersona(id))));
    }
}
