package ar.edu.iua.TruckTeck.auth.model;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa un usuario del sistema e implementa {@link UserDetails} para la integración con Spring Security.
 *
 * <p>
 * Esta entidad se encuentra mapeada a la tabla {@code users} en la base de datos. 
 * Cada usuario puede poseer múltiples roles definidos por la entidad {@link Role}, 
 * los cuales son utilizados para la autorización y el control de acceso.
 * </p>
 *
 * <p>
 * La clase también proporciona métodos para validar el estado de la cuenta, 
 * como si se encuentra expirada, bloqueada o deshabilitada.
 * </p>
 *
 * <p><b>Autor:</b> Equipo IW3 – Universidad Argentina</p>
 * <p><b>Versión:</b> 1.0.0</p>
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class User implements UserDetails {

	/** Indica si la cuenta no ha expirado. Por defecto, true. */
	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonExpired = true;

    /** Indica si la cuenta no está bloqueada. Por defecto, true. */
	@Column(columnDefinition = "tinyint default 0")
	private boolean accountNonLocked = true;

    /** Indica si las credenciales no han expirado. Por defecto, true. */
	@Column(columnDefinition = "tinyint default 0")
	private boolean credentialsNonExpired = true;

    /** Indica si la cuenta está habilitada. Por defecto, false. */
	@Column(columnDefinition = "tinyint default 0")
	private boolean enabled;

    /** Constantes de validación utilizadas en el método {@link #validate()}. */
	public static final String VALIDATION_OK = "OK";
	public static final String VALIDATION_ACCOUNT_EXPIRED = "ACCOUNT_EXPIRED";
	public static final String VALIDATION_CREDENTIALS_EXPIRED = "CREDENTIALS_EXPIRED";
	public static final String VALIDATION_LOCKED = "LOCKED";
	public static final String VALIDATION_DISABLED = "DISABLED";

	/**
     * Valida el estado de la cuenta del usuario.
     *
     * @return Una de las constantes de validación indicando el estado actual:
     *         {@link #VALIDATION_OK}, {@link #VALIDATION_ACCOUNT_EXPIRED},
     *         {@link #VALIDATION_CREDENTIALS_EXPIRED}, {@link #VALIDATION_LOCKED},
     *         o {@link #VALIDATION_DISABLED}.
     */
	public String validate() {
		if (!accountNonExpired)
			return VALIDATION_ACCOUNT_EXPIRED;
		if (!credentialsNonExpired)
			return VALIDATION_CREDENTIALS_EXPIRED;
		if (!accountNonLocked)
			return VALIDATION_LOCKED;
		if (!enabled)
			return VALIDATION_DISABLED;
		return VALIDATION_OK;
	}

    /** Correo electrónico del usuario (único y obligatorio). */
	@Column(length = 255, nullable = false, unique = true)
	private String email;

    /** Clave primaria del usuario. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	@Column(length = 100, unique = true)

    /** Nombre de usuario (único). */
	private String username;

    /** Contraseña del usuario (en formato hash). */
	private String password;


    /** Conjunto de roles asignados al usuario para fines de autorización. */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "userroles", joinColumns = {
			@JoinColumn(name = "idUser", referencedColumnName = "idUser") }, inverseJoinColumns = {
					@JoinColumn(name = "idRole", referencedColumnName = "id") })
	private Set<Role> roles;

	/**
     * Verifica si el usuario posee un rol específico.
     *
     * @param role Rol a verificar.
     * @return {@code true} si el usuario tiene el rol, de lo contrario {@code false}.
     */
	@Transient
	public boolean isInRole(Role role) {
		return isInRole(role.getName());
	}

	/**
     * Verifica si el usuario posee un rol según su nombre.
     *
     * @param role Nombre del rol.
     * @return {@code true} si el usuario tiene el rol, de lo contrario {@code false}.
     */
	@Transient
	public boolean isInRole(String role) {
		for (Role r : getRoles())
			if (r.getName().equals(role))
				return true;
		return false;
	}

	/**
     * Devuelve las autoridades concedidas al usuario para Spring Security.
     *
     * @return Colección de {@link GrantedAuthority} que representa los roles del usuario.
     */
	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		return authorities;
	}

	/**
     * Devuelve una lista con los nombres de los roles asignados al usuario.
     *
     * @return Lista de nombres de roles.
     */
	@Transient
	public List<String> getAuthoritiesStr() {
		List<String> authorities = getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
		return authorities;
	}

}
