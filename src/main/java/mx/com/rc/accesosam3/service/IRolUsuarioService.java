package mx.com.rc.accesosam3.service;

import mx.com.rc.accesosam3.entity.RolUsuario;
import mx.com.rc.accesosam3.entity.RolUsuarioPk;

import java.util.List;

public interface IRolUsuarioService {

    RolUsuario saveRolUsusario(RolUsuarioPk rolUsuario);


    RolUsuario deleteRolUsuario(RolUsuarioPk rolUsuarioPk);

    List<RolUsuario> findByAll();

    RolUsuario findById (RolUsuarioPk rolUsuarioPk);

    RolUsuario queryFindByNumeroEmpelado(Integer numeroEmpleado);

}
