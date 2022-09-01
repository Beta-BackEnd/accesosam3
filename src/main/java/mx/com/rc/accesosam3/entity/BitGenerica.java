package mx.com.rc.accesosam3.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "bit_generica")
public class BitGenerica implements Serializable {

    @Id
    @GeneratedValue()
    @Column(name = "id_bitacora", nullable = false, columnDefinition = "BIGINT")
    private Long idBitacora;

    @ManyToOne
    @JoinColumn(name = "id_operacion_bitacora", referencedColumnName = "id_operacion_bitacora")
    private BitOperacionBitacora idOperacionBitacora;

    @Column(name = "base_datos", columnDefinition = "VARCHAR (255)")
    private String baseDatos;

    @Column(name = "campo", columnDefinition = "VARCHAR (255)")
    private String campo;

    @Column(name = "dato_anterior", columnDefinition = "VARCHAR (255)")
    private String datoAnterior;

    @Column(name = "dato_nuevo", columnDefinition = "VARCHAR (255)")
    private String datoNuevo;

    @Column(name = "direccion_ip", columnDefinition = "VARCHAR (255)")
    private String direccionIp;

    @Column(name = "fecha", columnDefinition = "DATE")
    private LocalDate fecha;

    @Column(name = "hora", columnDefinition = "TIME")
    private LocalTime hora;

    @Column(name = "llave_registro", columnDefinition = "VARCHAR (255)")
    private String llaveRegistro;

    @Column(name = "modulo", columnDefinition = "VARCHAR (255)")
    private String modulo;

    @Column(name = "tabla", columnDefinition = "VARCHAR (255)")
    private String tabla;

    @Column(name = "usuario", columnDefinition = "VARCHAR (255)")
    private String usuario;

    @PrePersist
    public void prePersist(){
        this.baseDatos = "almacen_rc";
        this.fecha = LocalDate.now();
        this.hora = LocalTime.now();
        this.modulo = "Mantenimiento a los accesos del sam";
    }
}
