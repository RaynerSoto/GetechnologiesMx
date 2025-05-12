package cu.rayner.getechnologiesmx.core.controller;

import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoEstandar;
import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoObtener;
import cu.rayner.getechnologiesmx.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Persona;
import cu.rayner.getechnologiesmx.core.service.Directorio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/directorio")
@Tag(name = "Directorio Controller")
public class DirectorioRestService {
    private final Directorio directorio;

    @Autowired
    public DirectorioRestService(Directorio directorio) {
        this.directorio = directorio;
    }

    @PostMapping("/")
    @Operation(summary = "Insertar Persona",description = "Permite insertar una persona")
    public ResponseEntity<RespuestaEstandar> insertarPersona(@RequestBody PersonaDtoEstandar personaDtoEstandar){
        directorio.insertarPersona(personaDtoEstandar.toPersona());
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona insertada exitosamente"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar Persona",description = "Permite modificar una persona")
    public ResponseEntity<RespuestaEstandar> modificarPersonabyID(@RequestBody PersonaDtoEstandar personaDtoEstandar, @PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        directorio.modificarPersonabyID(new Persona(personaDtoEstandar, id));
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona modificada exitosamente"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar Persona",description = "Permite eliminar una persona a través del id de la base de datos")
    public ResponseEntity<RespuestaEstandar> eliminarPersonabyID(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        directorio.eliminarPersonabyID(id);
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona eliminada"));
    }

    @DeleteMapping("/{identificador}")
    @Operation(summary = "Eliminar Persona",description = "Permite eliminar una persona a través del identificador")
    public ResponseEntity<RespuestaEstandar> eliminarPersonabyIdentificador(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") String identificador) throws SearchException {
        directorio.eliminarPersonabyIdentificacion(identificador);
        return ResponseEntity.ok().body(new RespuestaEstandar("Persona eliminada"));
    }

    @GetMapping("/")
    @Operation(summary = "Listar Persona",description = "Permite listar a todas las personas del siste")
    public ResponseEntity<RespuestaEstandar> listarPersonas(){
        return ResponseEntity.ok().body(new RespuestaEstandar(directorio.listarPersonas().stream().
                map(PersonaDtoObtener::fromPersona)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener Persona",description = "Permite obtener una persona por su id de la base de datos")
    public ResponseEntity<RespuestaEstandar> obtenerPersonabyID(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") Long id) throws SearchException {
        return ResponseEntity.ok().body(new RespuestaEstandar(PersonaDtoObtener.fromPersona(directorio.buscarPersonabyID(id))));
    }

    @GetMapping("/{identificador}")
    @Operation(summary = "Obtener Persona",description = "Permite obtener una persona por su identificador")
    public ResponseEntity<RespuestaEstandar> obtenerPersonabyIdentificador(@PathVariable @NotNull(message = "El valor del identificador no puede ser nulo") String identificador) throws SearchException {
        return ResponseEntity.ok().body(new RespuestaEstandar(PersonaDtoObtener.fromPersona(directorio.buscarPersonabyIdentificacion(identificador))));
    }
}
