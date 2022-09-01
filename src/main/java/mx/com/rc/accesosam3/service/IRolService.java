package mx.com.rc.accesosam3.service;

import mx.com.rc.accesosam3.entity.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    Rol saveRol(Rol rol);

    Rol updateRol(Rol rol);

    Rol deleteRol(Integer idRol);

    List<Rol> findByAll();

    Rol findById(Integer idRol);

    List<Rol> findByDescricpionRol(String descripcion);

    }
