package mx.com.rc.accesosam3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolUsuarioPk implements Serializable {

    private String usuario;

    private Integer rol;

    @Override
    /*este metodo se sobreescribe para poder comparar que los campos que generan la llave compuesta sasber si
    son iguales o no*/
    public boolean equals(Object o) {
        if (this == o) return true;
        RolUsuarioPk that = (RolUsuarioPk) o;
        return Objects.equals(this.usuario, that.usuario) && Objects.equals(this.rol, that.rol);
    }

    @Override
    /*
    ressgresa el codigo unico de serealizacion del objeto
     */
    public int hashCode() {
        return Objects.hash(this, usuario, this.rol);
    }
}