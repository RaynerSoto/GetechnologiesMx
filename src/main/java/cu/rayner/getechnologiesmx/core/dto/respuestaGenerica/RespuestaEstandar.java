package cu.rayner.getechnologiesmx.core.dto.respuestaGenerica;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespuestaEstandar<T> {
    @NotBlank(message = "El valor de la respuesta no puede ser nulo")
    private T respuesta;
}
