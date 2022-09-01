package mx.com.rc.accesosam3.repository;

import mx.com.rc.accesosam3.entity.RolUsuario;
import mx.com.rc.accesosam3.entity.RolUsuarioPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolUsuarioRepository extends JpaRepository<RolUsuario, RolUsuarioPk> {

    @Query(nativeQuery = true, value = "SELECT * FROM rol_usuario WHERE usuario = :numeroEmpleado")
    List<RolUsuario> listFindByNumeroEmpelado(Integer numeroEmpleado);

    @Query(nativeQuery = true, value = "SELECT * FROM `usuario` AS U INNER JOIN rol_usuario AS R ON R.usuario = U.usuario " +
            "WHERE U.numero_empleado = :numeroEmpleado LIMIT 1")
    Optional<RolUsuario> queryFindByNumeroEmpelado(Integer numeroEmpleado);
}
