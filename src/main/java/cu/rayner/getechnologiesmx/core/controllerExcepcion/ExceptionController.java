package cu.rayner.getechnologiesmx.core.controllerExcepcion;
import cu.rayner.getechnologiesmx.core.dto.respuestaGenerica.RespuestaEstandar;
import cu.rayner.getechnologiesmx.core.exception.APIException;
import cu.rayner.getechnologiesmx.core.exception.AsignacionException;
import cu.rayner.getechnologiesmx.core.exception.SearchException;
import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler({SQLException.class, PersistenceException.class, DataAccessException.class})
    public ResponseEntity<RespuestaEstandar> handleSQLException(SQLException ex) {
        // Manejo de excepciones específicas de SQL
        String errorMessage = getSQLExceptionMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(errorMessage));
    }

    private String getSQLExceptionMessage(String errorMessage) {
        if (errorMessage.contains("nombre_unik_pais"))
            return "País con dicho nombre ya insertado";
        else if (errorMessage.contains("nombre_completo_unik_pais"))
            return "País con dicho nombre completo ya insertado";
        else if (errorMessage.contains("siglas_unik_pais"))
            return "País con dicha sigla ya insertado";
        else if (errorMessage.contains("fk_pais_continente_nombre"))
            return "Continente no registrado";
        else if (errorMessage.contains("pais_pkey"))
            return "El id del país ya ha sido insertado o tiene algún error";
        return errorMessage; // Mensaje genérico en caso de no coincidir con los patrones
    }

    public ResponseEntity<RespuestaEstandar> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RespuestaEstandar(ex.getMessage()));
    }
}