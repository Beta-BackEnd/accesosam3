package mx.com.rc.accesosam3.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelojEmpleadosDto implements Serializable {

    private Integer idRol;
    private String idUname;
    private Integer idMst;
    private Integer numEmpleado;
    private String usuario;
    private String nombreCompleto;
    private String mostrador;
    private String rolDescripcion;
    private Integer codigoPuesto;
    private String puestoReloj;
    private String statusReloj;
    private Integer statusSam;


}
