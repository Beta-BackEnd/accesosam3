package mx.com.rc.accesosam3.service;

import mx.com.rc.accesosam3.dto.RelojEmpleadosDto;

import java.util.List;

public interface IRelojService {

    List<RelojEmpleadosDto> findByIdUname(String idUname);
}
