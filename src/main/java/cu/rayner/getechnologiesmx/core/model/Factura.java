package cu.rayner.getechnologiesmx.core.model;

import cu.rayner.getechnologiesmx.core.dto.facturaDto.FacturaDtoEstandar;
import cu.rayner.getechnologiesmx.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "facturas",schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "factura_seq")
    @SequenceGenerator(name = "facturas_seq", sequenceName = "factura_seq_id",initialValue = 1,allocationSize = 1)
    @Column(name = "id_factura",unique = true,nullable = false)
    private Long id;

    @Column(name = "fecha",nullable = false)
    @NotNull(message = "La fecha de la factura no puede ser null")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fecha;

    @DecimalMin(value = "0.01",message = "El valor mínimo del monto debe ser 0.01")
    private Double monto;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    public Factura(Persona persona, FacturaDtoEstandar facturadto) {
        this.fecha = facturadto.fecha();
        this.monto = facturadto.valor();
        this.persona = persona;
    }

    @PrePersist
    public void validar(){
        Validacion.validarElemento(this);
    }
}
