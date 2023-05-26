package cursojava.dao;

import cursojava.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImpl implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Usuario> getUsuarios() {
        //aca hacemos la consulta a la BDD
        //la clase usuario debe indicar en que tabla hacer el select
        String query = "FROM Usuario";
        List <Usuario> resultado = entityManager.createQuery(query).getResultList();
        return resultado;
    }
    @Override
    public void registrar(Usuario usuario){
        //String query = "INSERT INTO Usuario";
        entityManager.merge(usuario);

    }

    @Override
    public void eliminar(Long id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }

    @Override
    public Usuario obtenerUsuarioYvalidarEmailPassword(Usuario usuario) {
            String query = "FROM Usuario WHERE email = :email";
            List<Usuario> lista = entityManager.createQuery(query)
                    .setParameter("email", usuario.getEmail())
                    .getResultList();

            if (lista.isEmpty()) {
                return null;
            }

            String passwordHashed = lista.get(0).getPassword();

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if (argon2.verify(passwordHashed, usuario.getPassword())) {
                return lista.get(0);
            }
            return null;
        }

}
