package mx.com.rc.accesosam3.service;

import mx.com.rc.accesosam3.entity.Usuario;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUsuarioService {

    Usuario saveUsuario(Usuario entityUsuario) throws UnsupportedEncodingException;

    Usuario updateUsuario(Usuario entityUsuario) throws UnsupportedEncodingException;

    Usuario deleteUsuario(String entityUsuario) throws UnsupportedEncodingException;

    List<Usuario> findByAll();

    Usuario finById(String entityUsuario);

    Usuario queryFindByNumeroEmpleado(String entityNumeroEmpleado);

    Usuario updatePasswordByNumeroEmpleado(String entityNumeroEmpleado, String entityPassword) throws UnsupportedEncodingException;

    Usuario updateMostradorByNumeroEmpleado(String entityNumeroEmpleado, String entityMostrador) throws UnsupportedEncodingException;
}
