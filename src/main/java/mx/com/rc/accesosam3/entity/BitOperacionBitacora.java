package mx.com.rc.accesosam3.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
/*
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "bit_operacion_bitacora")*/
public class BitOperacionBitacora implements Serializable {

    @Id
    @Column(name = "id_operacion_bitacora", nullable = true, columnDefinition = "INTEGER")
    private Integer idOperacionBitacora;

    @Column(name = "operacion", columnDefinition = "VARCHAR (255)")
    private String operacion;

}
