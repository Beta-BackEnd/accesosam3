package mx.com.rc.accesosam3.service.impl;

import com.twmacinta.util.MD5;
import mx.com.rc.accesosam3.entity.Usuario;
import mx.com.rc.accesosam3.repository.IUsuarioRepository;
import mx.com.rc.accesosam3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Override
    public Usuario saveUsuario(Usuario entityUsuario) throws UnsupportedEncodingException {
        MD5 md5 = new MD5();
        md5.Update(entityUsuario.getPassword(), null);
        String hash = md5.asHex();
        entityUsuario.setPassword(hash);
        return this.usuarioRepo.save(entityUsuario);
    }

    @Override
    public Usuario updateUsuario(Usuario entityUsuario) throws UnsupportedEncodingException {
        Usuario usuarioBd = this.finById(entityUsuario.getUsuario());
        if (usuarioBd == null)
            return null;
        usuarioBd.setNombre(entityUsuario.getNombre());
        usuarioBd.setAPaterno(entityUsuario.getAPaterno());
        usuarioBd.setAMaterno(entityUsuario.getAMaterno());
        usuarioBd.setNumeroEmpleado(entityUsuario.getNumeroEmpleado());
        usuarioBd.setEmail(entityUsuario.getEmail());
        Usuario usuarioCambio = this.saveUsuario(usuarioBd);
        return usuarioCambio;
    }

    @Override
    public Usuario deleteUsuario(String idUsuario) throws UnsupportedEncodingException {
        Usuario usuarioBd = this.finById(idUsuario);
        if (usuarioBd == null)
            return null;
        usuarioBd.setFechaBaja(LocalDateTime.now());
        usuarioBd.setStatus((usuarioBd.getStatus() == 1)?0:1);
        if (usuarioBd.getStatus() == 1)
            usuarioBd.setFechaBaja(null);
        Usuario usuarioCambio = this.saveUsuario(usuarioBd);
        return usuarioCambio;
    }

    @Override
    public List<Usuario> findByAll() {
        return this.usuarioRepo.findAll();
    }

    @Override
     public Usuario finById(String entityUsuario)
    {
        return this.usuarioRepo.findById(entityUsuario).orElse(null);
    }

    @Override
    public Usuario queryFindByNumeroEmpleado(String entityNumeroEmpleado) {
        return this.usuarioRepo.queryFindByNumeroEmpleado(entityNumeroEmpleado).orElse(null);
    }

    @Override
    public Usuario updatePasswordByNumeroEmpleado(String entityNumeroEmpleado, String entityPassword) throws UnsupportedEncodingException {
        Usuario usuarioBd = this.queryFindByNumeroEmpleado(entityNumeroEmpleado);
        if (usuarioBd == null)
            return null;
        MD5 md5 = new MD5();
        md5.Update(entityPassword, null);
        String hash = md5.asHex();
        usuarioBd.setPassword(hash);
        Usuario usuarioCambio = this.saveUsuario(usuarioBd);
        return usuarioCambio;
    }

    @Override
    public Usuario updateMostradorByNumeroEmpleado(String entityNumeroEmpleado, String entityMostrador) throws UnsupportedEncodingException {
        Usuario usuarioBd = this.queryFindByNumeroEmpleado(entityNumeroEmpleado);
        if (usuarioBd == null)
        return null;
        usuarioBd.setIdUname(entityMostrador);
        Usuario usuarioCambio = this.saveUsuario(usuarioBd);
        return usuarioCambio;
    }
}
