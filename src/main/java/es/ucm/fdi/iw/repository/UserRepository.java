package es.ucm.fdi.iw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.ucm.fdi.iw.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

/**
 * COMENTARIO EXPLICATIVO
 *
 * @Repository:
 * - Esta anotación indica que la interfaz es un componente de Spring que proporciona
 *   acceso a datos.
 *
 * JpaRepository<User, Long>:
 * - UserRepository extiende JpaRepository, que es una interfaz genérica proporcionada
 *   por Spring Data JPA. Esto permite realizar operaciones CRUD (Crear, Leer, Actualizar,
 *   Eliminar) sobre la entidad User sin necesidad de implementar estos métodos manualmente.
 * - User es la entidad que se gestiona, y Long es el tipo de dato de la clave primaria
 *   de la entidad User.
 *
 * Métodos personalizados:
 * - findByUsername(String username): Busca un usuario por su nombre de usuario.
 * - findByUsernameContaining(String partofname): Busca usuarios cuyo nombre de usuario
 *   contenga una cadena de texto específica.
 * - findByEmail(String email): Busca un usuario por su dirección de correo electrónico.
 * - existsByEmail(String email): Verifica si existe un usuario con una dirección de
 *   correo electrónico específica.
 *
 * Estos métodos son ejemplos de cómo se pueden definir consultas personalizadas en un
 * repositorio Spring Data JPA simplemente declarando sus firmas. Spring Data JPA
 * genera automáticamente las implementaciones de estos métodos en tiempo de ejecución.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findById(long id);
    User findByUsername(String username);
    
    List<User> findByUsernameNot(String username);
    List<User> findByUsernameContainingIgnoreCase(String username);
    List<User> findByUsernameContainingIgnoreCaseAndUsernameNot(String partialUsername, String username);

    List<User> findByCurrentSkillsSkillNameIgnoreCase(String skillname);
    List<User> findByDesiredSkillsSkillNameIgnoreCase(String skillname);

    User findByEmail(String email);
    boolean existsByEmail(String email);

    Page<User> findByUsernameNotAndIdNot(String username, Long excludeId, Pageable pageable);

}
