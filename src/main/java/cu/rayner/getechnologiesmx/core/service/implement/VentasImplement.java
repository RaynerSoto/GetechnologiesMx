package cu.rayner.getechnologiesmx.core.service.implement;

import cu.rayner.getechnologiesmx.core.dto.facturaDto.FacturaDtoEstandar;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Factura;
import cu.rayner.getechnologiesmx.core.model.Persona;
import cu.rayner.getechnologiesmx.core.repository.FacturaRepository;
import cu.rayner.getechnologiesmx.core.repository.PersonaRespository;
import cu.rayner.getechnologiesmx.core.service.Ventas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VentasImplement implements Ventas {

    private final PersonaRespository personaRespository;
    private final FacturaRepository facturaRepository;

    @Autowired
    public VentasImplement(PersonaRespository personaRespository, FacturaRepository facturaRepository) {
        this.personaRespository = personaRespository;
        this.facturaRepository = facturaRepository;
    }

    @Override
    public void insertarVenta(String identificadorPersona, FacturaDtoEstandar facturadto) throws SearchException {
        if (!personaRespository.existsByIdentificacionEquals(identificadorPersona)) {
            throw new SearchException("No se ha podido encontrar a la persona");
        }
        Persona persona = personaRespository.findByIdentificacionEquals(identificadorPersona).get();
        Factura factura = new Factura(persona,facturadto);
        facturaRepository.save(factura);
    }

    @Override
    public void buscarVenta(String identificadorPersona, FacturaDtoEstandar facturadto) throws SearchException {
        if (!personaRespository.existsByIdentificacionEquals(identificadorPersona)) {
            throw new SearchException("No se ha podido encontrar a la persona");
        }
        Optional<Factura> factura = facturaRepository.findAll().stream()
                .filter(factura1 -> factura1.getPersona().equals(identificadorPersona) && factura1.getFecha().equals(facturadto.fecha()) && factura1.getMonto() == facturadto.valor())
                .findFirst();
        if (!factura.isPresent()) {
            throw new SearchException("No se ha podido encontrar la factura solicitada");
        }
        return ResponseEntity.ok().body();
    }
}
