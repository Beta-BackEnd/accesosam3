package mx.com.rc.accesosam3.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "rol")
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol", nullable = false, columnDefinition = "INT(11)")
    private Integer idRol;

    @Column(name = "descripcion", nullable = true, columnDefinition = "VARCHAR(50)")
    private String descripcion;

    @Column(name = "fecha_alta", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_baja", nullable = true, columnDefinition = "DATATIME")
    private LocalDateTime fechaBaja;

    @Column(name = "status", nullable = true, columnDefinition = "INT (11)")
    private Integer status;

    @PrePersist
    public void prePersist(){
        this.fechaAlta = LocalDateTime.now();
        this.status = 1;
    }

}
