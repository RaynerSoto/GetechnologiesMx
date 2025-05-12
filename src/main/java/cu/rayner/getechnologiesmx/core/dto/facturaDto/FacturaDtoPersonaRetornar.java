package cu.rayner.getechnologiesmx.core.dto.facturaDto;

import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoEstandar;
import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoObtener;
import cu.rayner.getechnologiesmx.core.model.Factura;
import cu.rayner.getechnologiesmx.core.model.Persona;

public record FacturaDtoPersonaRetornar(PersonaDtoObtener personaDtoObtener, FacturaDtoEstandar facturaDtoEstandar) {
    public static FacturaDtoPersonaRetornar fromFacturaPersona(Factura factura, Persona persona){
        return new FacturaDtoPersonaRetornar(PersonaDtoObtener.fromPersona(persona),FacturaDtoEstandar.fromFactura(factura));
    }
}
