package ar.edu.iua.TruckTeck.auth.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ar.edu.iua.TruckTeck.auth.model.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Listener encargado de manejar los distintos eventos relacionados con usuarios
 * emitidos dentro del sistema.
 *
 * <p>
 * Esta clase implementa {@link ApplicationListener} escuchando eventos de tipo
 * {@link UserEvent}. En función del tipo de evento recibido, se ejecutan acciones
 * específicas tales como el registro de inicios de sesión o la notificación de envío 
 * de correos electrónicos relacionados con recuperación de contraseña o recordatorios
 * de nombre de usuario.
 * </p>
 *
 * <p>
 * Los métodos manejadores utilizan el sistema de logging para registrar la actividad,
 * permitiendo su monitoreo y auditoría.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Slf4j
@Component
public class UserEventListener implements ApplicationListener<UserEvent> {

	/**
     * Maneja el evento recibido y delega la acción correspondiente según su tipo.
     *
     * @param event Evento de usuario disparado por el sistema.
     */
	@Override
	public void onApplicationEvent(UserEvent event) {
		if (event.getTypeEvent().equals(UserEvent.TypeEvent.LOGIN)) {
			handleLogin(event);
		}
		if (event.getTypeEvent().equals(UserEvent.TypeEvent.SEND_EMAIL_WITH_PASSWORD_RESTORED)) {
			handleSendMailWithPasswordRestored(event);
		}
		if (event.getTypeEvent().equals(UserEvent.TypeEvent.SEND_EMAIL_WITH_USERNAME)) {
			handleSendMailWithUsername(event);
		}
	}

	/**
     * Maneja el evento de inicio de sesión de un usuario.
     *
     * <p>
     * Registra información relevante como el nombre del usuario y el host desde el cual
     * se realizó la petición.
     * </p>
     *
     * @param event Evento de tipo LOGIN.
     */
	private void handleLogin(UserEvent event) {
		User user = (User) event.getSource();
		HttpServletRequest request=(HttpServletRequest) event.getExtraData();
		log.debug("Evento LOGIN user: '{}', host={}", user.getUsername(), request.getRemoteHost());
	}


	/**
     * Maneja el evento de envío de correo por restauración de contraseña.
     *
     * @param event Evento de tipo SEND_EMAIL_WITH_PASSWORD_RESTORED.
     */
	private void handleSendMailWithPasswordRestored(UserEvent event) {
		User user = (User) event.getSource();
		log.debug("Evento SEND_EMAIL_WITH_PASSWORD_RESTORED user: {}", user.getUsername());
	}

	/**
     * Maneja el evento de envío de correo con el nombre de usuario.
     *
     * @param event Evento de tipo SEND_EMAIL_WITH_USERNAME.
     */
	private void handleSendMailWithUsername(UserEvent event) {
		User user = (User) event.getSource();
		log.debug("Evento SEND_EMAIL_WITH_USERNAME user: {}", user.getUsername());
	}
}
