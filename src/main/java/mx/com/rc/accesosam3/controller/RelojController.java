package mx.com.rc.accesosam3.controller;

import io.swagger.annotations.Api;
import mx.com.rc.accesosam3.dto.RelojEmpleadosDto;
import mx.com.rc.accesosam3.service.IRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Api Roles de Usuario")
@RestController
@RequestMapping("/reloj-checador")
public class RelojController {

    @Autowired
    private IRelojService relojService;

    @GetMapping("/{idUname}")
    public ResponseEntity<List<RelojEmpleadosDto>> findByIdUname (@PathVariable String idUname){
        return new ResponseEntity<>(this.relojService.findByIdUname(idUname), HttpStatus.OK);
    }
}
