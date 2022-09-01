package mx.com.rc.accesosam3.repository;

import mx.com.rc.accesosam3.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, String> {

    @Query(nativeQuery = true, value = "SELECT * FROM usuario WHERE usuario = :usuario")
    Optional<Usuario> queryFindByNumeroEmpleado(String usuario);




}
