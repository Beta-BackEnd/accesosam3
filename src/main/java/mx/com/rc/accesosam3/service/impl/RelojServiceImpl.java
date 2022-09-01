package mx.com.rc.accesosam3.service.impl;

import mx.com.rc.accesosam3.dto.RelojEmpleadosDto;
import mx.com.rc.accesosam3.external.RelojRepository;
import mx.com.rc.accesosam3.service.IRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelojServiceImpl implements IRelojService {

    @Autowired
    private RelojRepository relojRepo;

    @Override
    public List<RelojEmpleadosDto> findByIdUname(String idUname) {
        return this.relojRepo.buscarEmpelados(idUname);
    }

    @Override
    public RelojEmpleadosDto findByNumeroEmpleado(Integer numeroEmpleado) {
        return this.relojRepo.findByNumeroEmpleado(numeroEmpleado);
    }
}
