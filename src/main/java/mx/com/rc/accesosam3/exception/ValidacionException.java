package mx.com.rc.accesosam3.exception;

import mx.com.rc.accesosam3.entity.Rol;
import mx.com.rc.accesosam3.entity.Usuario;
import mx.com.rc.accesosam3.service.IRolService;
import mx.com.rc.accesosam3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidacionException {

    @Autowired
    private IRolService rolService;

    @Autowired
    private IUsuarioService usuarioService;

    public ValidacionException validaRolDescripcion(String descripcion){
        List<Rol> rol = this.rolService.findByDescricpionRol(descripcion);
        if (rol.isEmpty())
            throw new ResourceNotFoundException("Ya eciste un Rol con esta descripcion");
        return this;
    }

    public ValidacionException validaRolDescripcioVacia(String descripcion){
        if (descripcion.isEmpty())
            throw new ResourceNotFoundException("Es necasario ingresar una descripcion para dar de alta un Rol");
        return this;
    }

    public ValidacionException validaUsuarioExiste(String numUsuario){
        Usuario usuario = this.usuarioService.queryFindByNumeroEmpleado(numUsuario);
        if (usuario == null)
            throw new ResourceNotFoundException("El usuario no existe");
        return this;
    }

    public ValidacionException queTruene(){
        throw new ResourceNotFoundException("trono");
    }
}
