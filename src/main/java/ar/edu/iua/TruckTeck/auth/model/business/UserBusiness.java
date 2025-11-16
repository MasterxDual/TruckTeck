package ar.edu.iua.TruckTeck.auth.model.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.iua.TruckTeck.model.business.exceptions.BusinessException;
import ar.edu.iua.TruckTeck.model.business.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import ar.edu.iua.TruckTeck.auth.model.User;
import ar.edu.iua.TruckTeck.auth.model.business.exception.BadPasswordException;
import ar.edu.iua.TruckTeck.auth.model.persistence.UserRepository;

/**
 * Implementación del servicio {@link IUserBusiness} encargada de gestionar
 * las operaciones relacionadas con usuarios.
 *
 * <p>
 * Esta clase centraliza la lógica de negocio vinculada a la administración de usuarios,
 * incluyendo carga de información, modificación de contraseñas, habilitación o deshabilitación
 * de cuentas y obtención de listados completos. 
 * </p>
 *
 * <p>
 * Para las operaciones de persistencia utiliza {@link UserRepository}, mientras que
 * {@link PasswordEncoder} se emplea para el manejo seguro de contraseñas.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Service
@Slf4j
public class UserBusiness implements IUserBusiness {
    /** Repositorio para operaciones CRUD sobre entidades {@link User}. */
	@Autowired
	private UserRepository userDAO;


	/**
     * Carga un usuario a partir de su nombre de usuario o correo electrónico.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico.
     * @return El {@link User} correspondiente.
     * @throws NotFoundException Si no se encuentra un usuario con los datos provistos.
     * @throws BusinessException Si ocurre un error inesperado durante la consulta.
     */
	@Override
	public User load(String usernameOrEmail) throws NotFoundException, BusinessException {
		Optional<User> ou;
		try {
			ou = userDAO.findOneByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
		if (ou.isEmpty()) {
			throw NotFoundException.builder().message("No se encuentra el usuari@ email o nombre =" + usernameOrEmail)
					.build();
		}
		return ou.get();
	}

	/**
     * Cambia la contraseña de un usuario luego de validar la contraseña anterior.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico.
     * @param oldPassword     Contraseña actual.
     * @param newPassword     Nueva contraseña.
     * @param pEncoder        Codificador de contraseñas.
     * @throws BadPasswordException Si la contraseña actual no coincide con la almacenada.
     * @throws NotFoundException    Si el usuario no existe.
     * @throws BusinessException    Si ocurre un error durante la actualización.
     */
	@Override
	public void changePassword(String usernameOrEmail, String oldPassword, String newPassword, PasswordEncoder pEncoder)
			throws BadPasswordException, NotFoundException, BusinessException {
		User user = load(usernameOrEmail);
		if (!pEncoder.matches(oldPassword, user.getPassword())) {
			throw BadPasswordException.builder().build();
		}
		user.setPassword(pEncoder.encode(newPassword));
		try {
			userDAO.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	/**
     * Deshabilita la cuenta de un usuario.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico.
     * @throws NotFoundException Si el usuario no existe.
     * @throws BusinessException Si ocurre un error durante la persistencia.
     */
	@Override
	public void disable(String usernameOrEmail) throws NotFoundException, BusinessException {
		setDisable(usernameOrEmail, false);
	}

	/**
     * Habilita la cuenta de un usuario previamente deshabilitado.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico.
     * @throws NotFoundException Si el usuario no existe.
     * @throws BusinessException Si ocurre un error durante la persistencia.
     */
	@Override
	public void enable(String usernameOrEmail) throws NotFoundException, BusinessException {
		setDisable(usernameOrEmail, true);
	}

	/**
     * Establece el estado de habilitación de un usuario.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico.
     * @param enable          {@code true} para habilitar; {@code false} para deshabilitar.
     * @throws NotFoundException Si el usuario no existe.
     * @throws BusinessException Si ocurre un error durante la persistencia.
     */
	private void setDisable(String usernameOrEmail, boolean enable) throws NotFoundException, BusinessException {
		User user = load(usernameOrEmail);
		user.setEnabled(enable);
		try {
			userDAO.save(user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

	/**
     * Recupera la lista completa de usuarios registrados en el sistema.
     *
     * @return Lista de entidades {@link User}.
     * @throws BusinessException Si ocurre un error al consultar los datos.
     */
	@Override
	public List<User> list() throws BusinessException {
		try {
			return userDAO.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw BusinessException.builder().ex(e).build();
		}
	}

}
