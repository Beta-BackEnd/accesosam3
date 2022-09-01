package mx.com.rc.accesosam3.service.impl;

import mx.com.rc.accesosam3.entity.Rol;
import mx.com.rc.accesosam3.exception.ResourceNotFoundException;
import mx.com.rc.accesosam3.exception.ValidacionException;
import mx.com.rc.accesosam3.repository.IRolRepository;
import mx.com.rc.accesosam3.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService {

    @Autowired
    private IRolRepository rolRepo;

    @Autowired
    private ValidacionException validacionException;

    @Override
    public Rol saveRol(Rol rol) {
        this.validacionException
                .validaRolDescripcion(rol.getDescripcion())
                .validaRolDescripcioVacia(rol.getDescripcion());
        List<Rol> rolDb = this.rolRepo.findByDescricpionRol(rol.getDescripcion());
        if (rolDb.size() > 0)
            throw new ResourceNotFoundException("Ya existe un rol con esta descripcion");
        return this.rolRepo.save(rol);
    }

    @Override//ACTUALIZA DESCRIPCION
    public Rol updateRol(Rol rol) {
        this.validacionException
                .validaRolDescripcion(rol.getDescripcion())
                .validaRolDescripcioVacia(rol.getDescripcion());
        Rol rolBd = this.findById(rol.getIdRol());
        rolBd.setDescripcion(rol.getDescripcion());
        Rol rolCambio = this.saveRol(rolBd);
        return rolCambio;

    }

    @Override
    public Rol deleteRol(Integer idRol) {
        Rol rolBd = this.findById(idRol);
        rolBd.setFechaBaja(LocalDateTime.now());
        rolBd.setStatus((rolBd.getStatus() == 1) ? 0 : 1);
        if (rolBd.getStatus() == 1)
            rolBd.setFechaBaja(null);
        Rol rolCambio = this.saveRol(rolBd);
        return rolCambio;
    }

    @Override
    public List<Rol> findByAll() {
        return this.rolRepo.findAll();
    }

    @Override
    public Rol findById(Integer idRol) {
        Optional<Rol> rolRepo = this.rolRepo.findById(idRol);
        if (rolRepo.isEmpty())
            throw new ResourceNotFoundException("No se encontro rol con ese id");
        return rolRepo.get();
    }

    @Override
    public List<Rol> findByDescricpionRol(String descripcion) {
        List<Rol> listaRol = this.rolRepo.findByDescricpionRol(descripcion);
        if (listaRol.size() == 0)
            throw new ResourceNotFoundException("No se encontraron roles con esta descripcion");
        return listaRol;
    }

}
