package ar.edu.iua.TruckTeck.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.edu.iua.TruckTeck.auth.model.User;

/**
 * Clase base para controladores REST del sistema.
 *
 * <p>
 * Esta clase proporciona funcionalidades comunes para los controladores que requieren
 * acceder a la información del usuario autenticado. Permite obtener la instancia
 * del usuario actualmente logueado mediante el contexto de seguridad de Spring.
 * </p>
 *
 * <p>
 * Los controladores que hereden de esta clase podrán utilizar los métodos provistos
 * para simplificar tareas relacionadas con la autenticación.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
public class BaseRestController {
	/**
     * Obtiene el usuario actualmente autenticado en el sistema.
     *
     * <p>
     * El método accede al {@link SecurityContextHolder} para recuperar la autenticación
     * vigente y extrae el objeto {@link User} establecido como principal.
     * </p>
     *
     * @return La instancia de {@link User} correspondiente al usuario autenticado.
     * @throws ClassCastException Si el principal no es una instancia de {@link User}.
     */
	protected User getUserLogged() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		return user;
	}

}
