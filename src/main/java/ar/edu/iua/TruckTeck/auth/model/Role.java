package ar.edu.iua.TruckTeck.auth.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un rol de seguridad asignado a los usuarios del sistema.
 *
 * <p>
 * Esta entidad está mapeada a la tabla {@code roles} en la base de datos e implementa
 * {@link Serializable}, lo que permite que las instancias de la clase sean serializables.
 * Los roles se utilizan para definir permisos y controlar el acceso de las entidades {@link User}.
 * </p>
 *
 * <p>
 * Ejemplos comunes de roles incluyen: "ADMIN", "USER", "MANAGER", entre otros.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements Serializable {
    /** Identificador de serialización para garantizar compatibilidad de versiones. */
	private static final long serialVersionUID = -845420067971973620L;

	/**
     * Descripción del rol en formato legible.
     * <p>No puede ser nula y tiene un máximo de 100 caracteres.</p>
     */
	@Column(nullable = false, length = 100)
	private String description;

	/**
     * Identificador principal del rol.
     * <p>Generado automáticamente mediante la estrategia de identidad.</p>
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
     * Nombre único del rol, utilizado en verificaciones de seguridad y autorización.
     * <p>No puede ser nulo y debe ser único.</p>
     */
	@Column(unique = true, nullable = false)
	private String name;
}
