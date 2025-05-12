package cu.rayner.getechnologiesmx.core.model;

import cu.rayner.getechnologiesmx.core.dto.personaDto.PersonaDtoEstandar;
import cu.rayner.getechnologiesmx.core.utils.Validacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "personas",schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "persona_seq")
    @SequenceGenerator(name = "persona_seq", sequenceName = "persona_seq_id",initialValue = 1,allocationSize = 1)
    @Column(name = "id_persona",unique = true,nullable = false)
    private Long id;

    @Column(name = "nombre",nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El nombre de la persona debe tener entre 4 y 100 caracteres")
    @NotBlank(message = "El nombre de la persona no puede ser nulo o estar vacío")
    private String nombre;

    @Column(name = "apellido_paterno",nullable = false,length = 100)
    @Size(min = 4,max = 100,message = "El apellido paterno de la persona debe tener entre 4 y 100 caracteres")
    @NotBlank(message = "El apellido paterno de la persona no puede ser nulo o estar vacío")
    private String apellidoPaterno;

    @Transient
    private String apellidoMaterno;

    // Hola, valido que esta identificación es el de un pasaporte para que así se a nivel global
    @Column(name = "identificacion",unique = true,nullable = false,length = 50)
    @Size(min = 6,max = 15,message = "La identificación de la persona debe tener entre 6 y 15 caracteres")
    @NotBlank(message = "La identificación de la persona no puede ser nulo o estar vacío")
    private String identificacion;

    @OneToMany(mappedBy = "persona",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Factura> facturas;

    public Persona(PersonaDtoEstandar personaDtoEstandar){
        this.nombre = personaDtoEstandar.nombre();
        this.apellidoPaterno = personaDtoEstandar.apellidoPaterno();
        this.apellidoMaterno = personaDtoEstandar.apellidoMaterno();
        this.identificacion = personaDtoEstandar.identificacion();
    }

    public Persona(PersonaDtoEstandar personaDtoEstandar, Long id){
        this.id = id;
        this.nombre = personaDtoEstandar.nombre();
        this.apellidoPaterno = personaDtoEstandar.apellidoPaterno();
        this.apellidoMaterno = personaDtoEstandar.apellidoMaterno();
        this.identificacion = personaDtoEstandar.identificacion();
    }

    @PrePersist
    @PreUpdate
    public void validar(){
        Validacion.validarElemento(this);
    }

}
