package ar.edu.iua.TruckTeck.auth.model.business.exception;

import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * Excepción utilizada para indicar que una contraseña proporcionada no cumple
 * con los criterios esperados o es inválida durante procesos de autenticación
 * o validación.
 *
 * <p>
 * Esta excepción personalizada extiende {@link Exception} y se emplea 
 * principalmente en componentes de autenticación o gestión de contraseñas 
 * para señalar problemas relacionados con la validez o el procesamiento de la contraseña.
 * </p>
 *
 * <p>Algunos escenarios comunes que pueden generar esta excepción incluyen:</p>
 * <ul>
 *   <li>Contraseña incorrecta durante el inicio de sesión.</li>
 *   <li>Contraseña que no cumple reglas de validación (por ejemplo, complejidad o longitud insuficiente).</li>
 *   <li>Errores inesperados durante el proceso de hash o codificación de contraseñas.</li>
 * </ul>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@NoArgsConstructor
public class BadPasswordException extends Exception {

    /** Identificador de serialización para garantizar compatibilidad de versiones. */
	private static final long serialVersionUID = -8582277206660722157L;

    /**
     * Construye una nueva {@code BadPasswordException} con un mensaje detallado
     * y una causa asociada.
     *
     * @param message Mensaje descriptivo que brinda contexto adicional sobre el error.
     * @param ex      Causa original de la excepción (puede ser {@code null}).
     */
	@Builder
	public BadPasswordException(String message, Throwable ex) {
		super(message, ex);
	}

    /**
     * Construye una nueva {@code BadPasswordException} con el mensaje detallado especificado.
     *
     * @param message Mensaje que explica el motivo de la excepción.
     */
	@Builder
	public BadPasswordException(String message) {
		super(message);
	}

    /**
     * Construye una nueva {@code BadPasswordException} utilizando el mensaje de otra
     * {@link Throwable} como detalle y asociándola como causa.
     *
     * @param ex Excepción original que provocó este error.
     */
	@Builder
	public BadPasswordException(Throwable ex) {
		super(ex.getMessage(), ex);
	}
}
