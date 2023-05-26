package cursojava.dao;

import cursojava.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();
    void registrar(Usuario usuario);
    void eliminar(Long id);

    Usuario obtenerUsuarioYvalidarEmailPassword(Usuario usuario);
}
