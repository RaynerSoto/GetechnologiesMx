package cu.rayner.getechnologiesmx.core.service;

import cu.rayner.getechnologiesmx.core.dto.facturaDto.FacturaDtoEstandar;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.model.Factura;

public interface Ventas {
    void insertarVenta(String identificadorPersona, FacturaDtoEstandar factura) throws SearchException;
    void buscarVenta(String identificadorPersona, FacturaDtoEstandar facturadto) throws SearchException;
}
