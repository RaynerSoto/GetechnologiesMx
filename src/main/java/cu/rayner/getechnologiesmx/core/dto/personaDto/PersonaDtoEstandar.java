package cu.rayner.getechnologiesmx.core.dto.personaDto;

import cu.rayner.getechnologiesmx.core.model.Persona;

public record PersonaDtoEstandar(String nombre, String apellidoPaterno, String apellidoMaterno, String identificacion) {
    public Persona toPersona(){
        return new Persona(this);
    }

    public static Persona toPersona(PersonaDtoEstandar persona){
        return new Persona(persona);
    }

    public static PersonaDtoEstandar fromPersonaDto(Persona persona){
        return new PersonaDtoEstandar(persona.getNombre(), persona.getApellidoPaterno(), persona.getApellidoMaterno(), persona.getIdentificacion());
    }
}
