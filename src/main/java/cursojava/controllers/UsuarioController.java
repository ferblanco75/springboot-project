package cursojava.controllers;

import cursojava.dao.UsuarioDao;
import cursojava.models.Usuario;
import cursojava.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;
@RequestMapping(value= "usuario/{id}")
    public Usuario getUsuario (@PathVariable Long id){

        Usuario user = new Usuario();
        user.setId(12L);
        user.setNombre("Juan");
        user.setApellido("perez");
        user.setEmail("juan@mail.com");
        user.setTelefono(1141201);
        user.setPassword("lala");
        return user;
    }
    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token) {
        if (!validarToken(token)) { return null; }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void registrarUsuario(@RequestBody Usuario usuario){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,usuario.getPassword());
        //con esta linea de seteo guarda la contraseña hasheada
        usuario.setPassword(hash);
        //llama a usuarioDaoIMpl que hace la query a la bdd
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@PathVariable Long id){
        usuarioDao.eliminar(id);
    }


}
