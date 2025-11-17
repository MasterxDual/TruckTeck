package ar.edu.iua.TruckTeck.auth.model.business;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import ar.edu.iua.TruckTeck.auth.model.User;
import ar.edu.iua.TruckTeck.auth.model.business.exception.BadPasswordException;
import ar.edu.iua.TruckTeck.model.business.exceptions.BusinessException;
import ar.edu.iua.TruckTeck.model.business.exceptions.NotFoundException;

/**
 * Interfaz que define las operaciones de negocio para la gestión de entidades {@link User}.
 *
 * <p>
 * Esta interfaz abstrae las funcionalidades centrales vinculadas a la administración
 * de usuarios, tales como la carga de información, habilitación o deshabilitación
 * de cuentas, modificación de contraseñas y obtención de listados completos.
 * Las implementaciones deben aplicar las validaciones necesarias y hacer cumplir
 * las reglas de negocio correspondientes.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
public interface IUserBusiness {
	
    /**
     * Carga un usuario a partir del nombre de usuario o correo electrónico proporcionado.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico a buscar.
     * @return El {@link User} correspondiente al identificador recibido.
     * @throws NotFoundException  Si no se encuentra un usuario con los datos indicados.
     * @throws BusinessException  Si ocurre un error de negocio durante la operación.
     */
	public User load(String usernameOrEmail) throws NotFoundException, BusinessException;

	/**
     * Cambia la contraseña de un usuario.
     *
     * <p>
     * El método valida la contraseña actual y aplica la nueva utilizando el
     * {@link PasswordEncoder} proporcionado.
     * </p>
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico del usuario.
     * @param oldPassword     Contraseña actual.
     * @param newPassword     Nueva contraseña a establecer.
     * @param pEncoder        Codificador utilizado para hashear la nueva contraseña.
     * @throws BadPasswordException Si la nueva contraseña no cumple los requisitos de seguridad.
     * @throws NotFoundException    Si el usuario no existe.
     * @throws BusinessException    Si ocurre un error de negocio durante el proceso.
     */
	public void changePassword(String usernameOrEmail, String oldPassword, String newPassword, PasswordEncoder pEncoder)
			throws BadPasswordException, NotFoundException, BusinessException;

	/**
     * Deshabilita la cuenta de un usuario.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico del usuario a deshabilitar.
     * @throws NotFoundException Si el usuario no existe.
     * @throws BusinessException Si se produce un error de negocio o de acceso a la base de datos.
     */			
	public void disable(String usernameOrEmail) throws NotFoundException, BusinessException;

	/**
     * Habilita la cuenta previamente deshabilitada de un usuario.
     *
     * @param usernameOrEmail Nombre de usuario o correo electrónico del usuario a habilitar.
     * @throws NotFoundException Si el usuario no existe.
     * @throws BusinessException Si ocurre un error durante la operación de habilitación.
     */
	public void enable(String usernameOrEmail) throws NotFoundException, BusinessException;
	
	/**
     * Obtiene un listado de todos los usuarios registrados.
     *
     * @return Una {@link List} que contiene todas las entidades {@link User}.
     * @throws BusinessException Si ocurre algún error durante la consulta.
     */
	public List<User> list() throws BusinessException;

}
