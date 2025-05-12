package cu.rayner.getechnologiesmx.core.controller;

import cu.rayner.getechnologiesmx.core.dto.facturaDto.FacturaDtoEstandar;
import cu.rayner.getechnologiesmx.core.dto.facturaDto.FacturaDtoPersonaRetornar;
import cu.rayner.getechnologiesmx.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import cu.rayner.getechnologiesmx.core.service.Ventas;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/venta")
@Tag(name = "Venta Controller")
public class VentasRestService {
    private final Ventas ventas;

    @Autowired
    public VentasRestService(Ventas ventas) {
        this.ventas = ventas;
    }

    @PostMapping("/{identificador}")
    public ResponseEntity<RespuestaEstandar> insertarFactura(@PathVariable String identificador, @RequestBody FacturaDtoEstandar facturaDtoEstandar) throws SearchException {
        ventas.insertarVenta(identificador, facturaDtoEstandar);
        return ResponseEntity.ok(new RespuestaEstandar("Venta asignada con éxito"));
    }

    @PostMapping("/buscar/{identificador}")
    public ResponseEntity<RespuestaEstandar> buscarFactura(@PathVariable String identificador, @RequestBody FacturaDtoEstandar facturaDtoEstandar) throws SearchException {
        FacturaDtoPersonaRetornar facturaDtoPersonaRetornar = ventas.buscarVenta(identificador, facturaDtoEstandar);
        return ResponseEntity.ok(new RespuestaEstandar(facturaDtoPersonaRetornar));
    }
}
