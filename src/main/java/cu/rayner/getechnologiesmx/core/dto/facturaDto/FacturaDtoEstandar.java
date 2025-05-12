package cu.rayner.getechnologiesmx.core.dto.facturaDto;

import cu.rayner.getechnologiesmx.core.model.Factura;

import java.time.LocalDateTime;

public record FacturaDtoEstandar(LocalDateTime fecha, Double valor) {
    public static FacturaDtoEstandar fromFactura(Factura factura){
        return new FacturaDtoEstandar(factura.getFecha(), factura.getMonto());
    }
}
