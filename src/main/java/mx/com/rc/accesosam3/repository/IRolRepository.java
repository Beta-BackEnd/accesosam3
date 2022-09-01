package mx.com.rc.accesosam3.repository;

import mx.com.rc.accesosam3.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Integer> {

    @Query (nativeQuery = true, value = "SELECT * FROM rol WHERE descripcion LIKE CONCAT(:descripcion,'%')")
    List<Rol> findByDescricpionRol(String descripcion);


}
