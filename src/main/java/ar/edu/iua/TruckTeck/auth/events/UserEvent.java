package ar.edu.iua.TruckTeck.auth.events;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.Setter;

/**
 * Evento de aplicación que encapsula acciones relacionadas con usuarios.
 *
 * <p>
 * Esta clase extiende {@link ApplicationEvent} y permite transportar información
 * relevante sobre actividades vinculadas a usuarios dentro del sistema. Cada evento
 * contiene un objeto fuente, datos adicionales opcionales y un tipo que determina
 * la naturaleza del evento.
 * </p>
 *
 * <p>
 * Los eventos de este tipo son utilizados para disparar acciones específicas en 
 * distintos componentes, como logging, auditoría o envío de notificaciones.
 * </p>
 *
 * <p><b>Tipos de eventos disponibles:</b></p>
 * <ul>
 *     <li>{@code LOGIN}: indica que un usuario ha iniciado sesión.</li>
 *     <li>{@code SEND_EMAIL_WITH_PASSWORD_RESTORED}: notifica el envío de un correo con una contraseña restaurada.</li>
 *     <li>{@code SEND_EMAIL_WITH_USERNAME}: notifica el envío de un correo informando el nombre de usuario.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Getter
@Setter
public class UserEvent extends ApplicationEvent {
    /**
     * Enumeración que define los distintos tipos de eventos relacionados con usuarios.
     */
	public enum TypeEvent {
		/** Evento de inicio de sesión del usuario. */
		LOGIN,

		/** Evento de envío de correo con la contraseña restaurada. */
		SEND_EMAIL_WITH_PASSWORD_RESTORED,
		
		/** Evento de envío de correo con el nombre de usuario. */
		SEND_EMAIL_WITH_USERNAME
	}
	/** Tipo de evento asociado a esta instancia. */
	private TypeEvent typeEvent;

	/** Información adicional que puede acompañar al evento. */
	private Object extraData;

	/**
     * Crea una nueva instancia de {@code UserEvent}.
     *
     * @param source     Objeto que generó el evento. No debe ser {@code null}.
     * @param extraData  Datos adicionales relevantes para el evento. Puede ser {@code null}.
     * @param typeEvent  Tipo de evento que describe la acción realizada.
     */
	public UserEvent(Object source, Object extraData, TypeEvent typeEvent) {
		super(source);
		this.typeEvent = typeEvent;
		this.extraData=extraData;
	}
}
