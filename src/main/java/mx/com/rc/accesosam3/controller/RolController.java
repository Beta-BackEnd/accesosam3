package mx.com.rc.accesosam3.controller;

import io.swagger.annotations.*;
import mx.com.rc.accesosam3.entity.Rol;
import mx.com.rc.accesosam3.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Api de Roles") //para asignar un titulo en la documentacion
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listado de Roles"),
            @ApiResponse(code = 400, message = "No se han encontrado Roles")
    })
    @ApiOperation(value = "Servicio para buscar los Roles de Usuario SAM 3", response = Rol[].class)
    @GetMapping
    public ResponseEntity<List<Rol>> findAll(){
        return new ResponseEntity<>(this.rolService.findByAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se ha encontrado el registro "),
            @ApiResponse(code = 400, message = "No se ha encontrado el registro")
    })
    @ApiOperation(value = "Servicio para buscar un Rol por su descripcion", response = Rol.class)
    @GetMapping("/{descripcion}")
    public ResponseEntity<?> findByDescricpionRol(@PathVariable ("descripcion")  String descripcion){
        List<Rol> desc = rolService.findByDescricpionRol(descripcion);
        /*if (desc.size() == 0){
            return  ResponseEntity.badRequest().body("No existe ese registro");
        }*/
        return  new ResponseEntity<>(desc, HttpStatus.OK);

    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Guradar un nuevo Rol"),
            @ApiResponse(code = 400, message = "No se ha guardado el Rol")
    })
    @ApiOperation(value = "Servicio para guardar Roles", response = Rol.class)
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Rol rol){
        return new ResponseEntity(this.rolService.saveRol(rol), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se actualizo el registro"),
            @ApiResponse(code = 400, message = "No se actualizo el registro")
    })
    @ApiOperation(value = "Servicio para actualizar datos de un Rol", response = Rol.class)
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Rol rol){
        Rol rolActualizar = rolService.updateRol(rol);
        if (rolActualizar == null){
            return ResponseEntity.badRequest().body("No existe ese registro");
        }
        return ResponseEntity.ok().body(rolActualizar);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se ha dado de baja el registro "),
            @ApiResponse(code = 400, message = "No se ha dado de baja el registro")
    })
    @ApiOperation(value = "Servicio para actualizar el status de un Rol", response = Rol.class)
    @GetMapping("/cambiar-status/{id}")
    public ResponseEntity<?> delete(@PathVariable ("id") Integer id){
        Rol rolStatus = this.rolService.deleteRol(id);
      if(rolStatus == null)
          return ResponseEntity.badRequest().body("No existe ese registro");
        Map<String,Object> map = new HashMap<>();
        map.put("mensaje","Se ha cambiado el status correctamente. " + ((rolStatus.getStatus()==0)?"Se ha dado de baja":"Se ha activado"));
        map.put("rol",rolStatus);
        map.put("status",200);
        return ResponseEntity.ok().body(map);
        //return new ResponseEntity<>(HttpStatus.OK);
    }


}
