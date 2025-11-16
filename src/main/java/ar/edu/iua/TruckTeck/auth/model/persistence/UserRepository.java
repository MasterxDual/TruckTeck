package ar.edu.iua.TruckTeck.auth.model.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.TruckTeck.auth.model.User;

/**
 * Interfaz de repositorio para realizar operaciones CRUD sobre entidades {@link User}.
 *
 * <p>
 * Esta interfaz extiende {@link JpaRepository}, proporcionando métodos estándar para
 * crear, leer, actualizar y eliminar usuarios en la base de datos.
 * </p>
 *
 * <p>
 * También incluye un método personalizado para obtener un usuario a partir de su nombre
 * de usuario o correo electrónico, funcionalidad comúnmente utilizada durante procesos
 * de autenticación y validación.
 * </p>
 * 
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
	/**
     * Busca un {@link User} cuyo nombre de usuario o correo electrónico coincida
     * con los valores proporcionados.
     *
     * @param username Nombre de usuario a buscar.
     * @param email    Correo electrónico a buscar.
     * @return Un {@link Optional} que contiene el usuario si existe, o vacío si no se encuentra
     *         una coincidencia con el nombre de usuario o correo electrónico indicados.
     */
	public Optional<User> findOneByUsernameOrEmail(String username, String email);
}
