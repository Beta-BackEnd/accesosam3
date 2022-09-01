package mx.com.rc.accesosam3.external;

import mx.com.rc.accesosam3.dto.RelojEmpleadosDto;
import mx.com.rc.accesosam3.entity.RolUsuario;
import mx.com.rc.accesosam3.entity.Usuario;
import mx.com.rc.accesosam3.service.IRolUsuarioService;
import mx.com.rc.accesosam3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RelojRepository {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolUsuarioService rolUsuarioService;

    private Connection conectar() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://reloj.corprama.com.mx/reloj_checador?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true&zeroDataTimeBenavior=convertToNull", "extrae_monitor", "ex_140211");
    }

    public List<RelojEmpleadosDto> buscarEmpelados(String idUname) {
        List<RelojEmpleadosDto> listaEmplados = new ArrayList<>();
        String query = "SELECT * FROM reloj_checador.vista_empleados WHERE tienda_asignada = '" + idUname + "' AND codigo_puesto IN (3010,3020,3040,3050,3060,3070,3110,3120)";
        try {
            Connection conexion = conectar();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Integer numEmpleado = rs.getInt("numero_empleado");
                String nombreCompleto = rs.getString("nombre_completo");
                String puesto = rs.getString("puesto");
                Integer codigoPuesto = rs.getInt("codigo_puesto");
                String statusReloj = rs.getString("status");

                RelojEmpleadosDto empleado = new RelojEmpleadosDto();
                empleado.setNumEmpleado(numEmpleado);
                empleado.setNombreCompleto(nombreCompleto);
                empleado.setPuestoReloj(puesto);
                empleado.setCodigoPuesto(codigoPuesto);
                empleado.setStatusReloj(statusReloj);
                //hacer la consulta de la busqueda de rol por usuario
                RolUsuario rolUsuarioSbd = (RolUsuario) this.rolUsuarioService.queryFindByNumeroEmpelado(numEmpleado);
                if (rolUsuarioSbd != null) {
                    empleado.setIdRol(rolUsuarioSbd.getRol().getIdRol());
                    empleado.setRolDescripcion(rolUsuarioSbd.getRol().getDescripcion());
                    empleado.setUsuario(rolUsuarioSbd.getUsuario().getUsuario());
                    empleado.setIdUname(rolUsuarioSbd.getUsuario().getIdUname());
                    empleado.setStatusSam(rolUsuarioSbd.getUsuario().getStatus());
                }
                listaEmplados.add(empleado);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return listaEmplados;
    }
}
