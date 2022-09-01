package mx.com.rc.accesosam3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolUsuarioPkDto implements Serializable {

    private String idUsuario;
    private Integer idRol;
    private Integer idRol1;
    private boolean isActualiza;

}
