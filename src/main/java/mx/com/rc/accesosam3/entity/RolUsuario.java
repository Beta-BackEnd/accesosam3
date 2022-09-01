package mx.com.rc.accesosam3.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "rol_usuario")
@IdClass(RolUsuarioPk.class)//se manda llamar la clase que valida la llave
public class RolUsuario implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;//se llama la clase de la llave primaria y se asigna en variable

    @Id
    @ManyToOne //relacion de muchos a uno, estos es para definir las llaves foraneas
    @JoinColumn(name = "usuario", referencedColumnName = "usuario")// para relacionar los campos el
    //primer parametro es el campo de la llave foranea y el segundo es de la tabla donde es la llave primaria
    private Usuario usuario;//se llama la clase de la llave primaria y se asigna en variable

    @Column(name = "descripcion", nullable = true, columnDefinition = "VARCHAR(50)")
    private String descripcion;

    @Column(name = "fecha_alta", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_baja", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaBaja;

    @Column(name = "status", nullable = true, columnDefinition = "INT(11)")
    private Integer status;

    @PrePersist
    public void prePersist(){
        this.fechaAlta = LocalDateTime.now();
        this.status = 1;
    }

}
