package mx.com.rc.accesosam3.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data //crea los getter y setter
@NoArgsConstructor //crea constructor vacio
@AllArgsConstructor //crea constructor con datos
@ToString //cres el toString para impresion en consola
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "usuario", nullable = false, columnDefinition = "VARCHAR(50)")
    private String usuario;

    @Column(name = "a_materno", nullable = true, columnDefinition = "VARCHAR(50)")
    private String aMaterno;

    @Column(name = "a_paterno", nullable = true, columnDefinition = "VARCHAR(50)")
    private String aPaterno;

    @Column(name = "fecha_alta", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaAlta;

    @Column(name = "fecha_baja", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaBaja;

    @Column(name = "fecha_caducidad", nullable = true, columnDefinition = "DATETIME")
    private LocalDateTime fechaCaducidad;

    @Column(name = "nombre", nullable = true, columnDefinition = "VARCHAR(50)")
    private String nombre;

    @Column(name = "numero_empleado", nullable = true, columnDefinition = "INTEGER(11)")
    private Integer numeroEmpleado;

    @Column(name = "password", nullable = true, columnDefinition = "VARCHAR(32)")
    private String password;

    @Column(name = "status", nullable = true, columnDefinition = "INTEGER(11)")
    private Integer status;

    @Column(name = "id_uname", nullable = true, columnDefinition = "VARCHAR(3)")
    private String idUname;

    @Column(name = "email", nullable = true, columnDefinition = "VARCHAR(255)")
    private String email;

    @Column(name = "num_intento_logeo", nullable = true, columnDefinition = "INTEGER(11)")
    private Integer numIntentoLogeo;

    @Column(name = "bloqueo", nullable = true, columnDefinition = "BIT(1)")
    private Integer bloqueo;

    //Antes de guardar se ejecuta y lo que hace es asignar valor al guardar
    @PrePersist
    public void prePersist() {
        this.fechaAlta = LocalDateTime.now();
        this.status = 1;
        this.numIntentoLogeo = 0;
        this.usuario = this.numeroEmpleado.toString();
        this.aPaterno = this.aPaterno.toUpperCase();
        this.aMaterno = this.aMaterno.toUpperCase();
        this.nombre = this.nombre.toUpperCase();
        this.idUname = this.idUname.toLowerCase();
    }

    @PreUpdate
    public void preUpdate() {
        this.usuario = this.numeroEmpleado.toString();
        this.aPaterno = this.aPaterno.toUpperCase();
        this.aMaterno = this.aMaterno.toUpperCase();
        this.nombre = this.nombre.toUpperCase();
        this.idUname = this.idUname.toLowerCase();
    }

}
