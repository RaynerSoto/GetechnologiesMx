package cu.rayner.getechnologiesmx.core.dto.personaDto;

import cu.rayner.getechnologiesmx.core.model.Persona;

public record PersonaDtoObtener (Long id, String nombre, String apellidoPaterno, String identificacion){
    public static PersonaDtoObtener fromPersona(Persona persona){
        return new PersonaDtoObtener(persona.getId(),persona.getNombre(),persona.getApellidoPaterno(),persona.getIdentificacion());
    }
}
