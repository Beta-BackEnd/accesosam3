package mx.com.rc.accesosam3.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.auth.In;
import mx.com.rc.accesosam3.dto.RolUsuarioPkDto;
import mx.com.rc.accesosam3.entity.RolUsuario;
import mx.com.rc.accesosam3.entity.RolUsuarioPk;
import mx.com.rc.accesosam3.entity.Usuario;
import mx.com.rc.accesosam3.service.IRolUsuarioService;
import mx.com.rc.accesosam3.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "Api de Usuarios")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IRolUsuarioService rolUsuarioService;

    //Metodo para tabla Usuario

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listado de Usuarios"),
            @ApiResponse(code = 400, message = "No se han encontrado usurios")
    })
    @ApiOperation(value = "Servicio para traer todos los Usuarios")
    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return new ResponseEntity<>(this.usuarioService.findByAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se encontro el Usuario"),
            @ApiResponse(code = 400, message = "No se encontro el Usuario")
    })
    @ApiOperation(value = "Servicio para busqueda por numero de usuario")
    @GetMapping("/{numUsuario}")
    public ResponseEntity<?> findById(@PathVariable("numUsuario") String numUsuario) {
        Usuario usuario = usuarioService.finById(numUsuario);
        if (usuario == null) {
            return ResponseEntity.badRequest().body("No existe el usuario");
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se creo el Usuario"),
            @ApiResponse(code = 400, message = "No se creo el Usuario")
    })
    @ApiOperation(value = "Servicio para crear un usuario")
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        try {
            return new ResponseEntity<>(this.usuarioService.saveUsuario(usuario), HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("No se puede encriptar la contraseña");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se ha dado de baja el Usuario"),
            @ApiResponse(code = 400, message = "No se ha dado de baja el Usuario")
    })
    @ApiOperation(value = "Servicio para dar de baja un Usuario (cambios de status)")
    @GetMapping("/cambiar-status/{idUsuario}")
    public ResponseEntity<?> delete(@PathVariable("idUsuario") String idUsuario) {
        Usuario usuarioStatus = null;
        try {
            usuarioStatus = this.usuarioService.deleteUsuario(idUsuario);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("No se puede encriptar la contraseña");
        }
        if (usuarioStatus == null)
            return ResponseEntity.badRequest().body("El usuario no existe");
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", "Se ha cambiado el status correctamente. " + ((usuarioStatus.getStatus() == 0) ? "Se ha dado de baja el usuario" : "Se ha activado el usuario"));
        map.put("usuario", usuarioStatus);
        map.put("status", 200);
        return ResponseEntity.ok().body(map);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se actualizo la contraseña"),
            @ApiResponse(code = 400, message = "No se ha actuliazdo la contraseña")
    })
    @ApiOperation(value = "Servicio para Actualizar la Contraseña")
    @PutMapping("/actualizar-pass/{numEmpleado}")
    public ResponseEntity<?> updatePass(@PathVariable("numEmpleado") String idUsuario, String password) {
        Usuario usuarioPass = null;
        try {
            usuarioPass = this.usuarioService.updatePasswordByNumeroEmpleado(idUsuario, password);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("No se puede encriptar la contraseña");
        }
        if (usuarioPass == null)
            return ResponseEntity.badRequest().body("El usuario no existe");
        usuarioPass.setPassword(password);
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", "La contraseña se ha cambiado");
        map.put("Status", 200);
        return ResponseEntity.ok().body(map);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se actualizo la Rc"),
            @ApiResponse(code = 400, message = "No se ha actuliazdo la Rc")
    })
    @ApiOperation(value = "Servicio para Actualizar la Rc")
    @PutMapping("/actualizar-rc/{numEmpleado}")
    public ResponseEntity<?> updateMostrador(@PathVariable("numEmpleado") String idUsuario, String mostrador) {
        Usuario usuarioMost = null;
        try {
            usuarioMost = this.usuarioService.updateMostradorByNumeroEmpleado(idUsuario, mostrador);
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("No se puede actualizar la Rc");
        }
        if (usuarioMost == null)
            return ResponseEntity.badRequest().body("El usuario no existe");
        usuarioMost.setIdUname(mostrador);
        Map<String, Object> map = new HashMap<>();
        map.put("mensaje", "El mostrador se actualizo correctamente");
        map.put("Mostrador", usuarioMost.getIdUname());
        map.put("status", 200);
        return ResponseEntity.ok().body(map);
    }

    //Metodo para tabla RolUsuario
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Listado de Roles"),
            @ApiResponse(code = 400, message = "No se han encontrado Roles")
    })
    @ApiOperation(value = "Servicio para traer todos los Roles de usuarios")
    @GetMapping("/roles-usuario")
    public ResponseEntity<List<RolUsuario>> findAllRol() {
        return new ResponseEntity<>(this.rolUsuarioService.findByAll(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se encontro el Rol del usuario"),
            @ApiResponse(code = 400, message = "No se han encontrado Roles")
    })
    @ApiOperation(value = "Servicio para buscar Rol por usuario mediante PathVariable")
    @GetMapping("/usuario-rol/{idUsuario}/{idRol}")
    public ResponseEntity<?> findByIdRol(@PathVariable("idUsuario") String idUsuario, @PathVariable("idRol") Integer idRol) {
        RolUsuarioPk rolUsuarioPk = new RolUsuarioPk(idUsuario, idRol);
        RolUsuario rolUsuarioBd = this.rolUsuarioService.findById(rolUsuarioPk);
        return new ResponseEntity<>(rolUsuarioBd, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se encontraron estos Roles del usuario"),
            @ApiResponse(code = 400, message = "No se han encontrado Roles para este usuario")
    })
    @ApiOperation(value = "Servicio para buscar los Roles de un usuario")
    @GetMapping("/roles-usuario/{numEmpelado}")
    public ResponseEntity<?> findByNumeroEmpleado(@PathVariable Integer numEmpelado){
        return new ResponseEntity<>(this.rolUsuarioService.queryFindByNumeroEmpelado(numEmpelado), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se encontro el Rol del usuario"),
            @ApiResponse(code = 400, message = "No se han encontrado Roles")
    })
    @ApiOperation(value = "Servicio para buscar Rol por usuario mediante RequestBody")
    @PostMapping("/usuario-rol")
    public ResponseEntity<?> findByIdRol(@RequestBody RolUsuarioPk usuarioPk) {
        RolUsuario rolUsuarioBd = this.rolUsuarioService.findById(usuarioPk);
        return new ResponseEntity<>(rolUsuarioBd, HttpStatus.OK);
    }

    //prueba para saber como se maneja un dto
    @PostMapping("/usuario-rol-dto")
    public ResponseEntity<?> findByIdRol(@RequestBody RolUsuarioPkDto usuarioPkDto) {
        if (usuarioPkDto.isActualiza()) {
            RolUsuarioPk rolUsuarioPk = new RolUsuarioPk(usuarioPkDto.getIdUsuario(), usuarioPkDto.getIdRol());
            RolUsuario rolUsuarioBd = this.rolUsuarioService.findById(rolUsuarioPk);
            return new ResponseEntity<>(rolUsuarioBd, HttpStatus.OK);
        }
        return new ResponseEntity<>("no se actualizo", HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se creo el Rol del usuario"),
            @ApiResponse(code = 400, message = "No se ha creado el Rol")
    })
    @ApiOperation(value = "Servicio para crear Rol de usuario")
    @GetMapping("/save/{idUsuario}/{idRol}")
    public ResponseEntity<?> crateRolUsuario(@Validated @PathVariable String idUsuario, @Validated @PathVariable Integer idRol) {
        RolUsuarioPk pk = new RolUsuarioPk(idUsuario, idRol);
        RolUsuario rolUsuarioBd = this.rolUsuarioService.findById(pk);
        if (rolUsuarioBd != null)
            return new ResponseEntity<>("El usuario ya contiene este rol", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.rolUsuarioService.saveRolUsusario(pk), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Se cambio el status de el Rol del usuario"),
            @ApiResponse(code = 400, message = "No se ha cambiado el status")
    })
    @ApiOperation(value = "Servicio para dar de baja Rol de usuario")
    @GetMapping("/cambiar-status-rol-usuario/{idUsuario}/{idRol}")
    public ResponseEntity<?> deleteRolUsuario(@Validated @PathVariable String idUsuario, @Validated @PathVariable Integer idRol){
        RolUsuarioPk pk = new RolUsuarioPk(idUsuario, idRol);
        RolUsuario rolUsuarioBd = this.rolUsuarioService.deleteRolUsuario(pk);
        if (rolUsuarioBd == null)
            return new ResponseEntity<>("El usuario y rol no existen", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok().body("Se cambio correctamente el status");
    }
}
