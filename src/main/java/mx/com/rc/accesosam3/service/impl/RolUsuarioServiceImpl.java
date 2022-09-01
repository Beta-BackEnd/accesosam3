package mx.com.rc.accesosam3.service.impl;

import mx.com.rc.accesosam3.entity.Rol;
import mx.com.rc.accesosam3.entity.RolUsuario;
import mx.com.rc.accesosam3.entity.RolUsuarioPk;
import mx.com.rc.accesosam3.entity.Usuario;
import mx.com.rc.accesosam3.repository.IRolUsuarioRepository;
import mx.com.rc.accesosam3.service.IRolService;
import mx.com.rc.accesosam3.service.IRolUsuarioService;
import mx.com.rc.accesosam3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RolUsuarioServiceImpl implements IRolUsuarioService {

    @Autowired
    private IRolUsuarioRepository rolUsuarioRepo;

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public RolUsuario saveRolUsusario(RolUsuarioPk rolUsuarioPk) {
        Rol rolDb = this.rolService.findById(rolUsuarioPk.getRol());
        Usuario usuarioDb = this.usuarioService.finById(rolUsuarioPk.getUsuario());
        if(rolDb == null || usuarioDb == null)
            return null;
        RolUsuario usuarioSave= new RolUsuario();
        usuarioSave.setRol(rolDb);
        usuarioSave.setUsuario(usuarioDb);
        usuarioSave.setDescripcion(rolDb.getDescripcion());
        return this.rolUsuarioRepo.save(usuarioSave);
    }


    @Override
    public RolUsuario deleteRolUsuario(RolUsuarioPk rolUsuarioPk) {
        Rol rolDb = this.rolService.findById(rolUsuarioPk.getRol());
        Usuario usurioDb = this.usuarioService.finById(rolUsuarioPk.getUsuario());
        if (rolDb == null || usurioDb == null)
            return null;
        RolUsuario rolUsuarioStatus = this.findById(rolUsuarioPk);
        if (rolUsuarioStatus == null)
            return null;
        rolUsuarioStatus.setFechaBaja(LocalDateTime.now());
        rolUsuarioStatus.setStatus((rolUsuarioStatus.getStatus() == 1) ? 0 : 1);
        if (rolUsuarioStatus.getStatus() == 1)
            rolUsuarioStatus.setFechaBaja(null);
        RolUsuario rolUsuarioCambio = this.rolUsuarioRepo.save(rolUsuarioStatus);
        return rolUsuarioCambio;
    }

    @Override
    public List<RolUsuario> findByAll() {
        return this.rolUsuarioRepo.findAll();
    }

    @Override
    public RolUsuario findById(RolUsuarioPk rolUsuarioPk) {
        return this.rolUsuarioRepo.findById(rolUsuarioPk).orElse(null);
    }

    @Override
    public RolUsuario queryFindByNumeroEmpelado(Integer numeroEmpleado) {
        return this.rolUsuarioRepo.queryFindByNumeroEmpelado(numeroEmpleado).orElse(null);
    }
}
